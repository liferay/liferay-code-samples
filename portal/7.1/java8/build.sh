#!/bin/bash

# exit the script as soon as a build fails 
set -e

# check if samples are in all 4 build scenarios
. ./check.sh

cd $CODE_FOLDER

# build standalone projects

for PROJECT_PATH in ${SAMPLES}
do
    banner $COLOR_GREEN_BG "Building standalone $PROJECT_PATH with Gradle"
    cd $STANDALONE_GRADLE/$PROJECT_PATH
    rm -rf .gradle
    ./gradlew clean assemble

    banner $COLOR_BLUE_BG "Building standalone $PROJECT_PATH with Maven"
    cd $STANDALONE_MAVEN/$PROJECT_PATH
    mvn clean package
done;


# build workspaces

banner $COLOR_GREEN_BG "Building the whole Gradle workspace"
cd $WORKSPACE_GRADLE
rm -rf .gradle
./gradlew clean assemble

banner $COLOR_BLUE_BG "Building the whole Maven workspace"
echo 
cd $WORKSPACE_MAVEN
mvn clean package

pwd

echo "Script folder: ${SCRIPT_FOLDER}"

cd ${SCRIPT_FOLDER}

pwd

./gradlew check

cd $CURRENT_FOLDER