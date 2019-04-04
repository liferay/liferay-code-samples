#!/bin/bash

source ../../../variables.sh
source ../../../functions.sh


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

for f in $(find ./* -name ".project_folder") 
do
    PROJECT_PATH=$(dirname "$f" | sed "s|^\./||"); 
    echo "Found project in $PROJECT_PATH"

    checkFolder "$STANDALONE_GRADLE/$PROJECT_PATH"
    checkBuildFile "$STANDALONE_GRADLE/$PROJECT_PATH/build.gradle"
    checkSymlinks "$STANDALONE_GRADLE/$PROJECT_PATH"

    checkFolder "$STANDALONE_MAVEN/$PROJECT_PATH"
    checkBuildFile "$STANDALONE_MAVEN/$PROJECT_PATH/pom.xml"
    checkSymlinks "$STANDALONE_MAVEN/$PROJECT_PATH"

    checkFolder "$WORKSPACE_GRADLE/$PROJECT_PATH"
    checkBuildFile "$WORKSPACE_GRADLE/$PROJECT_PATH/build.gradle"
    checkSymlinks "$WORKSPACE_GRADLE/$PROJECT_PATH"

    checkFolder "$WORKSPACE_MAVEN/$PROJECT_PATH"
    checkBuildFile "$WORKSPACE_MAVEN/$PROJECT_PATH/pom.xml"
    checkSymlinks "$WORKSPACE_MAVEN/$PROJECT_PATH"

done;

cd $CURRENT_FOLDER;



