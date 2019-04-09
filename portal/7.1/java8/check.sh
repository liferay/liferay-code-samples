#!/bin/bash

source ../../../variables.sh
source ../../../functions.sh

VERBOSE=0
CREATE=0
ERRORS=0

for p in ${@}
do
case ${p} in
    -v)
    VERBOSE=1
    shift # past argument=value
    ;;
    -c)
    CREATE=1
    shift # past argument=value
    ;;
    *)
          # unknown option
    ;;
esac
done

echo "You are in $CURRENT_FOLDER"
echo "You running a script in $SCRIPT_FOLDER"
echo "The source codes are in $CODE_FOLDER"
echo "The standalone Gradle builds are in $STANDALONE_GRADLE"
echo "The standalone Maven builds are in $STANDALONE_MAVEN"
echo "The workspace Gradle builds are in $WORKSPACE_GRADLE"
echo "The workspace Maven builds are in $WORKSPACE_MAVEN"

echo 
echo "Checking folder structure consistency across build systems"
echo 


cd $CODE_FOLDER;
findSamplesInCurrentFolder;
for PROJECT_PATH in ${SAMPLES}
do
    echo "Found project in $PROJECT_PATH"

    checkFolder "$STANDALONE_GRADLE/$PROJECT_PATH"
    checkLink "$STANDALONE_GRADLE/$PROJECT_PATH/src" "${CODE_FOLDER}/$PROJECT_PATH/src"
    checkFile "$STANDALONE_GRADLE/$PROJECT_PATH/build.gradle"
    checkSymlinks "$STANDALONE_GRADLE/$PROJECT_PATH"

    checkFolder "$STANDALONE_MAVEN/$PROJECT_PATH"
    checkLink "$STANDALONE_MAVEN/$PROJECT_PATH/src" "${CODE_FOLDER}/$PROJECT_PATH/src"
    checkFile "$STANDALONE_MAVEN/$PROJECT_PATH/pom.xml"
    checkSymlinks "$STANDALONE_MAVEN/$PROJECT_PATH"

    checkFolder "$WORKSPACE_GRADLE/$PROJECT_PATH"
    checkLink "$WORKSPACE_GRADLE/$PROJECT_PATH/src" "${CODE_FOLDER}/$PROJECT_PATH/src"
    checkFile "$WORKSPACE_GRADLE/$PROJECT_PATH/build.gradle"
    checkSymlinks "$WORKSPACE_GRADLE/$PROJECT_PATH"

    checkFolder "$WORKSPACE_MAVEN/$PROJECT_PATH"
    checkLink "$WORKSPACE_MAVEN/$PROJECT_PATH/src" "${CODE_FOLDER}/$PROJECT_PATH/src"
    checkFile "$WORKSPACE_MAVEN/$PROJECT_PATH/pom.xml"
    checkSymlinks "$WORKSPACE_MAVEN/$PROJECT_PATH"
    # TODO: check if parent project has it in modules list

done;

cd $CURRENT_FOLDER;

if [[ ${ERRORS} != 0 ]]; then
  exit 1
fi
