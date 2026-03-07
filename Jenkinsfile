pipeline {
    agent {
    label 'aws-agent'
    }
    tools {
        maven 'maven3'
        jdk 'JDK17'
    }
    stages {
        stage('Compile') {
            steps {
            script {
                sh 'echo "Compiling the application..."'
                sh 'mvn clean compile' 
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
            stage('Build') {
                steps {
                script {
                    sh 'echo "Building the application..."'
                    sh 'mvn package'
                }
                }
            }
            stage('Build Docker Image') {
               when {
                  expression { env.GIT_BRANCH == 'origin/main' }
               }
                steps {
                    script {
                        sh 'echo "Building Docker image..."'
                        sh 'docker build -t my-app -f Dockerfile'
                    }
                }
            }
            stage('Push Docker Image') {
                when {
                  expression { env.GIT_BRANCH == 'origin/main' }
               }
                steps {
                    script {
                        withDockerRegistry(credentialsId: 'dockerhub', url: 'https://index.docker.io/v1/') {
                            sh ' docker tag my-app a7medsayed/simple-java-app:${BUILD_NUMBER}'
                            sh ' docker push a7medsayed/simple-java-app:${BUILD_NUMBER}'

                         }
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