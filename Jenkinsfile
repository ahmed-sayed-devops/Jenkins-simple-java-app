pipeline {
    agent {
    label 'aws-agent'
    }
    tools {
        maven 'maven3'
        jdk 'JDK17'
    }
    stages {
        stage('Build') {
            steps {
            script {
                sh 'echo "Building the application..."'
                sh 'mvn clean package' 
              }
            }
          }
          stage('Test') {
            steps {
            script {
                sh 'echo "Running tests..."'
                sh 'mvn test'
            }
            }
          }
  }
}