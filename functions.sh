colorEcho () {
    echo -e "${1}${2}${COLOR_OFF}"
    return 0;
}

findSamplesInCurrentFolder () {
    SAMPLES=$(find ./* -name "${SAMPLE_FILE_NAME}" -exec dirname {} \; | sed "s|^\./||")
    return 0;
}

checkFolder () {
    ((VERBOSE)) && echo -n "Checking for project folder ${1} : "
    if [[ -d "${1}" ]]; then
        ((VERBOSE)) && colorEcho ${COLOR_GREEN}  "OK"
    else 
        if [[ -e "${1}" ]]; then
            ((VERBOSE)) && colorEcho ${COLOR_RED}  "NOT A FOLDER"
            ((!VERBOSE)) && colorEcho ${COLOR_RED}  "${1} IS NOT A FOLDER "
            ERRORS=1
        else
            ((VERBOSE)) && colorEcho ${COLOR_RED}  "MISSING"
            ((!VERBOSE)) && colorEcho ${COLOR_RED}  "MISSING ${1}"
            if [[ $CREATE = 1 ]]; then
                mkdir -p ${1}
                colorEcho ${COLOR_YELLOW} "Created folder '${1}'!" 
            else
                ERRORS=1
            fi
        fi
    fi
    return 0;
} 



checkFile () {
    ((VERBOSE)) && echo -n "Checking for file '${1}' : "
    if [[ -f "${1}" ]]; then
        ((VERBOSE)) && colorEcho ${COLOR_GREEN} "OK"
    else 
        ((VERBOSE)) && colorEcho ${COLOR_RED} "MISSING"
        ((!VERBOSE)) && colorEcho ${COLOR_RED}  "MISSING ${1}"
        ERRORS=1
    fi
    return 0;
} 

checkLink () {
    ((VERBOSE)) && echo -n "Checking for link '${1}' : "
    if [[ -L "${1}" ]]; then
        ((VERBOSE)) && colorEcho ${COLOR_GREEN} "OK"
    else 
        if [[ -e "${1}" ]]; then
            ((VERBOSE)) && colorEcho ${COLOR_RED}  "NOT A SYMLINK"
            ((!VERBOSE)) && colorEcho ${COLOR_RED}  "${1} IS NOT A SYMLINK "
            ERRORS=1
        else
            ((VERBOSE)) && colorEcho ${COLOR_RED}  "MISSING"
            ((!VERBOSE)) && colorEcho ${COLOR_RED}  "MISSING ${1}"
            if [[ $CREATE = 1 ]]; then
                TMP_FOLDER=$(pwd)
                LINK_FOLDER=$(dirname ${1})
                LINK_NAME=$(basename ${1})
                RELATIVE_LINK_TARGET=$(realpath --relative-to=${LINK_FOLDER} ${2})
                cd ${LINK_FOLDER}
                ln -s ${RELATIVE_LINK_TARGET} ${LINK_NAME}
                cd ${TMP_FOLDER}
                colorEcho ${COLOR_YELLOW} "Created symlink '${LINK_NAME}' to '${RELATIVE_LINK_TARGET}'!" 
            else
                ERRORS=1
            fi
        fi
    fi
    return 0;
} 

checkSymlinks () {
    for SYMLINK in $(find ${1} -type l)
    do
        TMP_FOLDER=$(pwd)
        LINK_FOLDER=$(dirname ${SYMLINK})
        LINK_NAME=$(basename ${SYMLINK})
        # SYMLINK_TARGET=$(stat -f "%Y" ${SYMLINK})
        SYMLINK_TARGET=$(ls -l ${SYMLINK} | awk '{print $11}')

        ((VERBOSE)) && echo -n "Checking symlink '${SYMLINK}' : "
        if [[ ${SYMLINK_TARGET:0:1} == '/' ]]; then
            if [[ -e ${SYMLINK_TARGET} ]]; then
                ((VERBOSE)) && colorEcho ${COLOR_RED} "IT'S ABSOLUTE PATH!"
                RELATIVE_LINK_TARGET=$(realpath --relative-to=${LINK_FOLDER} ${SYMLINK_TARGET})
                cd ${LINK_FOLDER}
                rm ${LINK_NAME}
                ln -s ${RELATIVE_LINK_TARGET} ${LINK_NAME}
                cd ${TMP_FOLDER}
                colorEcho ${COLOR_YELLOW} "Replaced '${SYMLINK_TARGET}' with '${RELATIVE_LINK_TARGET}'!" 
            else
                ((VERBOSE)) && colorEcho ${COLOR_RED} "BROKEN"
                ((!VERBOSE)) && colorEcho ${COLOR_RED}  "BROKEN LINK ${SYMLINK} to ${SYMLINK_TARGET}"
                ERRORS=1
            fi
        else 
            if [[ -e ${LINK_FOLDER}/${SYMLINK_TARGET} ]]; then
                ((VERBOSE)) && colorEcho ${COLOR_GREEN} "OK"
            else
                ((VERBOSE)) && colorEcho ${COLOR_RED} "BROKEN"
                ((!VERBOSE)) && colorEcho ${COLOR_RED}  "BROKEN LINK ${SYMLINK} to ${SYMLINK_TARGET}"
                ERRORS=1
            fi
        fi
    done
    return 0;

}

moveSample () {

    OLD_PROJECT_PATH=${1}
    NEW_PROJECT_PATH=${2}

    if ! [[ -e ${OLD_PROJECT_PATH} ]]; then
        echo "Path ${OLD_PROJECT_PATH} does not exists!"
        return 1;
    fi;

    if ! [[ -e "${OLD_PROJECT_PATH}/${SAMPLE_FILE_NAME}" ]]; then
        echo "Path ${OLD_PROJECT_PATH} is not a sample folder!"
        return 1;
    fi;

    OLD_PROJECT_PATH=$(realpath ${OLD_PROJECT_PATH})

    if ! [[ $OLD_PROJECT_PATH/ = ${CODE_FOLDER}/* ]]; then
        echo "Path ${OLD_PROJECT_PATH} is not under '${CODE_FOLDER}' folder!"
        return 1;
    fi;

    OLD_PROJECT_PATH=$(realpath --relative-to=${CODE_FOLDER} ${OLD_PROJECT_PATH})

    if [[ -e "${NEW_PROJECT_PATH}" ]]; then
        echo "Path ${NEW_PROJECT_PATH} already exists!"
        return 1;
    fi;

    dir=${NEW_PROJECT_PATH}
    while [[ $dir && ! -d $dir ]] ; do
        dir=${dir%/*}
    done
    
    if [[ $dir ]]; then
        dir=$(realpath ${dir})
        if ! [[ $dir/ = ${CODE_FOLDER}/* ]]; then
            echo "Path ${NEW_PROJECT_PATH} is not under '${CODE_FOLDER}' folder!"
            return 1;
        fi;
    else
        echo "Path ${NEW_PROJECT_PATH} is invalid!"
        return 1;
    fi;

    NEW_PROJECT_PATH=$(realpath --relative-to=${CODE_FOLDER} ${NEW_PROJECT_PATH})

    mv ${CODE_FOLDER}/${OLD_PROJECT_PATH} ${CODE_FOLDER}/${NEW_PROJECT_PATH}

    mv ${STANDALONE_GRADLE}/${OLD_PROJECT_PATH} ${STANDALONE_GRADLE}/${NEW_PROJECT_PATH}
    mv ${STANDALONE_MAVEN}/${OLD_PROJECT_PATH} ${STANDALONE_MAVEN}/${NEW_PROJECT_PATH}
    mv ${WORKSPACE_GRADLE}/${OLD_PROJECT_PATH} ${WORKSPACE_GRADLE}/${NEW_PROJECT_PATH}
    mv ${WORKSPACE_MAVEN}/${OLD_PROJECT_PATH} ${WORKSPACE_MAVEN}/${NEW_PROJECT_PATH}

    colorEcho ${COLOR_RED} "WARNING: Maven workspace project moved. Please update parent POM(s) !!!"

    updateLink ${STANDALONE_GRADLE} ${OLD_PROJECT_PATH} ${NEW_PROJECT_PATH}
    updateLink ${STANDALONE_MAVEN} ${OLD_PROJECT_PATH} ${NEW_PROJECT_PATH}
    updateLink ${WORKSPACE_GRADLE} ${OLD_PROJECT_PATH} ${NEW_PROJECT_PATH}
    updateLink ${WORKSPACE_MAVEN} ${OLD_PROJECT_PATH} ${NEW_PROJECT_PATH}
}

updateLinks () {

    local OLD_PROJECT_PATH=$(realpath --relative-to=${CODE_FOLDER} ${1})
    local NEW_PROJECT_PATH=$(realpath --relative-to=${CODE_FOLDER} ${2})

    for FOLDER in ${STANDALONE_GRADLE} ${STANDALONE_MAVEN} ${WORKSPACE_GRADLE} ${WORKSPACE_MAVEN}
    do
        updateLink ${FOLDER} ${OLD_PROJECT_PATH} ${NEW_PROJECT_PATH}
    done

}


updateLink () {
    local BASE_PATH=${1};
    local OLD_PROJECT_PATH=${2};
    local NEW_PROJECT_PATH=${3}

    for SYMLINK in $(find $BASE_PATH/$NEW_PROJECT_PATH -type l)
    do
        TMP_FOLDER=$(pwd)
        LINK_FOLDER=$(dirname ${SYMLINK})
        LINK_NAME=$(basename ${SYMLINK})
        SYMLINK_TARGET=$(stat -f "%Y" ${SYMLINK})
        OLD_RELATIVE=$(realpath --relative-to=${LINK_FOLDER} ${CODE_FOLDER}/${OLD_PROJECT_PATH})
        NEW_RELATIVE=$(realpath --relative-to=${LINK_FOLDER} ${CODE_FOLDER}/${NEW_PROJECT_PATH})

        if [[ ${SYMLINK_TARGET} = ${OLD_RELATIVE}/* ]]; then
            cd ${LINK_FOLDER}
            NEW_TARGET=${NEW_RELATIVE}${SYMLINK_TARGET#"$OLD_RELATIVE"}
            if ! [[ -e ${NEW_TARGET} ]]; then
                colorEcho ${COLOR_RED} "${NEW_TARGET} does not exsts!"
            fi;
            ln -sf ${NEW_TARGET} ${LINK_NAME}
            cd ${TMP_FOLDER}
        fi;

    done
}

banner () {
    local COLOR=$1;
    local message="|   $2   |";
    local line=$(head -c ${#message} < /dev/zero | tr '\0' '-')
    echo 
    colorEcho ${COLOR} "${line}"
    colorEcho ${COLOR} "${message}"
    colorEcho ${COLOR} "${line}"
    echo 
}