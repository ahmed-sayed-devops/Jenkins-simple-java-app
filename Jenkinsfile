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
  post {
    success {
        slackSend(
            channel: '#jenkins-ci',
            message: "Build Succeeded - ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)",
            teamDomain: 'ahmed-sayedworkspace',
            tokenCredentialId: 'slack-notification'
        )
    }
    failure {
        slackSend(
            channel: '#jenkins-ci',
            message: "Build Failed - ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)",
            teamDomain: 'ahmed-sayedworkspace',
            tokenCredentialId: 'slack-notification'
        )
    }
}

}