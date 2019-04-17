pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        isUnix()
        sh '''cd portal/7.1/java8
./build.sh'''
      }
    }
  }
}