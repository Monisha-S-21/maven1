pipeline {
    agent any

    tools {
        maven 'sonarmaven'
    }

    environment {
        SONAR_TOKEN = credentials('maven') 
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-17'
        PATH = "${JAVA_HOME}\\bin;${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }  
       
      
        stage('Package') {
            steps {
                bat 'mvn package'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying the application...'
            }
        }

        
        
      stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    bat """
                        mvn clean verify sonar:sonar \
                        -Dsonar.projectKey=maven \
                        -Dsonar.projectName='maven' \
                        -Dsonar.host.url=http://localhost:9000 \
                        -Dsonar.token=sqp_d81ac1dfd0023a1300c8465363e5179bab347d76
                    """
                }
            }
        }
    }
  post {
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
