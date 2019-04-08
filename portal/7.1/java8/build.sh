
# exit the script as soon as a build fails 
set -e

source ./check.sh

cd $CODE_FOLDER;

# build standalone projects

for PROJECT_PATH in ${SAMPLES}
do
    echo 
    echo "=================================================="
    echo "==>> Building standalone $PROJECT_PATH with Gradle"
    echo "=================================================="
    echo 
    cd $STANDALONE_GRADLE/$PROJECT_PATH
    rm -rf .gradle
    ./gradlew clean assemble

    echo 
    echo "=================================================="
    echo "==>> Building standalone $PROJECT_PATH with Maven"
    echo "=================================================="
    echo 
    cd $STANDALONE_MAVEN/$PROJECT_PATH
    mvn clean package
done;


# build workspaces

echo 
echo "=================================================="
echo "==>> Building the whole Gradle workspace"
echo "=================================================="
echo 
cd $WORKSPACE_GRADLE
rm -rf .gradle
./gradlew clean assemble;

echo 
echo "=================================================="
echo "==>> Building the whole Maven workspace"
echo "=================================================="
echo 
cd $WORKSPACE_MAVEN
mvn clean package;


cd $CURRENT_FOLDER;