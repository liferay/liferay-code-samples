colorEcho () {
    echo -e "${1}${2}${COLOR_OFF}"
}

checkFolder () {
    ((VERBOSE)) && echo -n "Checking for project folder ${1} : "
    if [ -d "${1}" ]; then
        ((VERBOSE)) && colorEcho ${COLOR_GREEN}  "OK"
    else 
        ((VERBOSE)) && colorEcho ${COLOR_RED}  "MISSING"
        ((!VERBOSE)) && colorEcho ${COLOR_RED}  "MISSING ${1}"
        ERRORS=1
    fi
} 

checkBuildFile () {
    ((VERBOSE)) && echo -n "Checking for build file '${1}' : "
    if [ -f "${1}" ]; then
        ((VERBOSE)) && colorEcho ${COLOR_GREEN} "OK"
    else 
        ((VERBOSE)) && colorEcho ${COLOR_RED} "MISSING"
        ((!VERBOSE)) && colorEcho ${COLOR_RED}  "MISSING ${1}"
        ERRORS=1
    fi
} 

checkLink () {
    ((VERBOSE)) && echo -n "Checking for link '${1}' : "
    if [ -L "${1}" ]; then
        ((VERBOSE)) && colorEcho ${COLOR_GREEN} "OK"
    else 
        ((VERBOSE)) && colorEcho ${COLOR_RED} "MISSING"
        ((!VERBOSE)) && colorEcho ${COLOR_RED}  "MISSING ${1}"
        ERRORS=1
    fi
} 

checkSymlinks () {
    for SYMLINK in $(find ${1} -type l)
    do
        ((VERBOSE)) && echo -n "Checking symlink '${SYMLINK}' : "
        SYMLINK_TARGET=$(stat -f "%Y" ${SYMLINK})
        if [[ ${SYMLINK_TARGET:0:1} == '/' ]]; then
            ((VERBOSE)) && colorEcho ${COLOR_RED} "IT'S ABSOLUTE PATH!" 
            LINK_FOLDER=$(dirname ${SYMLINK})
            LINK_NAME=$(basename ${SYMLINK})
            RELATIVE_LINK_TARGET=$(realpath --relative-to=${LINK_FOLDER} ${SYMLINK_TARGET})
            TMP_FOLDER=$(pwd)
            cd ${LINK_FOLDER}
            rm ${LINK_NAME}
            ln -s ${RELATIVE_LINK_TARGET} ${LINK_NAME}
            cd ${TMP_FOLDER}
            colorEcho ${COLOR_YELLOW} "Replaced '${SYMLINK_TARGET}' with '${RELATIVE_LINK_TARGET}'!" 
        else 
            ((VERBOSE)) && colorEcho ${COLOR_GREEN} "OK"
        fi
    done     
}