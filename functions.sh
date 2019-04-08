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
        ((VERBOSE)) && colorEcho ${COLOR_RED}  "MISSING"
        ((!VERBOSE)) && colorEcho ${COLOR_RED}  "MISSING ${1}"
        ERRORS=1
    fi
    return 0;
} 



checkBuildFile () {
    ((VERBOSE)) && echo -n "Checking for build file '${1}' : "
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
        ((VERBOSE)) && colorEcho ${COLOR_RED} "MISSING"
        ((!VERBOSE)) && colorEcho ${COLOR_RED}  "MISSING ${1}"
        ERRORS=1
    fi
    return 0;
} 

checkSymlinks () {
    for SYMLINK in $(find ${1} -type l)
    do
        TMP_FOLDER=$(pwd)
        LINK_FOLDER=$(dirname ${SYMLINK})
        LINK_NAME=$(basename ${SYMLINK})
        SYMLINK_TARGET=$(stat -f "%Y" ${SYMLINK})

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