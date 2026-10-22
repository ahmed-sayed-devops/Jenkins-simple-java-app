
pipeline {
    agent any

    tools {
        maven 'maven3'
        jdk 'JDK17'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Source code is checked out from GitHub.'
            }
        }

        stage('Check Tools') {
            steps {
                sh 'java -version'
                sh 'mvn -version'
            }
        }

        stage('Compile') {
            steps {
                echo 'Compiling the Java application...'
                sh 'mvn clean compile -DskipTests'
            }
        }

        stage('Build') {
            steps {
                echo 'Building the Java application...'
                sh 'mvn package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                echo 'Running application tests...'
                sh 'mvn test'
            }
        }
    }

    post {
        success {
            echo 'Java CI/CD pipeline completed successfully!'
        }

        failure {
            echo 'Pipeline failed. Check the Console Output.'
        }

        always {
            echo 'Pipeline execution finished.'
        }
    }
}
