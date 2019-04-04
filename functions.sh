colorEcho () {
    echo -e "${1}${2}${COLOR_OFF}"
}

checkFolder () {
    echo -n "Checking for project folder $1 : "
    if [ -d "$1" ]; then
        colorEcho ${COLOR_GREEN}  "OK"
    else 
        colorEcho ${COLOR_RED}  "MISSING"
    fi
} 

checkBuildFile () {
    echo -n "Checking for build file '$1' : "
    if [ -f "$1" ]; then
        colorEcho ${COLOR_GREEN} "OK"
    else 
        colorEcho ${COLOR_RED} "MISSING"
    fi
} 