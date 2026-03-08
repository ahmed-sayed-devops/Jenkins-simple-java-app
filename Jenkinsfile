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
                sh 'mvn clean compile -DskipTests' 
              }
            }
          }
            stage('Build') {
                steps {
                script {
                    sh 'echo "Building the application..."'
                    sh 'mvn package -DskipTests'
                }
                }
            }
            stage('Build Docker Image') {
               when {
                  expression { !env.ghprbPullId }
               }
                steps {
                    script {
                        sh 'echo "Building Docker image..."'
                        sh 'docker build -t my-app .'
                    }
                }
            }
            stage('Push Docker Image') {
                when {
                  expression { !env.ghprbPullId }
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
            stage('Deploy to EKS') {
                when {
                  expression { !env.ghprbPullId }
               }
                steps {
                    script {
                        withAWS(credentials: 'aws-credentials', region: 'us-east-1') {
                            sh 'echo "Deploying to EKS..."'
                            sh 'aws eks update-kubeconfig --region us-east-1 --name my-eks-cluster'
                            sh 'kubectl apply -f deployment.yaml'
                            sh 'kubectl apply -f service.yaml'
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