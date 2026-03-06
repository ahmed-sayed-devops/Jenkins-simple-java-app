pipeline {
    agent {
    label 'aws-agent'
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