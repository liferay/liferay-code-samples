pipeline {
  agent any
  stages {
    stage('Check env') {
      steps {
        isUnix()
        sh '''env
gradle -version
mvn --version'''
      }
    }
    stage('Build') {
      steps {
        sh '''cd portal/7.1/java8
./build.sh'''
      }
    }
    stage('Refresh') {
      steps {
        writeFile(file: '.refresh', text: 'refresh')
      }
    }
  }
  environment {
    JENKINS_NODE_COOKIE = 'dontKillMe'
  }
}