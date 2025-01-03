// pipeline {
//     agent any

//     tools {
//         maven 'sonarmaven'
//     }

//     environment {
//         SONAR_TOKEN = credentials('maven') 
//         JAVA_HOME = 'C:\\Program Files\\Java\\jdk-17'
//         PATH = "${JAVA_HOME}\\bin;${env.PATH}"
//     }

//     stages {
//         stage('Checkout') {
//             steps {
//                 checkout scm
//             }
//         }

//         stage('Dependency Update') {
//     steps {
//         bat 'mvn clean install -U'
//     }
// }
//         stage('Build') {
//             steps {
//                 bat 'mvn clean compile dependency:resolve'
//             }
//         }
        
//         stage('Test') {
//             steps {
//                 bat 'mvn test'
//             }
//         }  
       
      
//         stage('Package') {
//             steps {
//                 bat 'mvn package'
//             }
//         }

//         stage('Deploy') {
//             steps {
//                 echo 'Deploying the application...'
//             }
//         }

        
        
//       stage('SonarQube Analysis') {
//             steps {
//                 withSonarQubeEnv('sonarqube') {
//                     bat """
//                         mvn clean verify sonar:sonar \
//                         -Dsonar.projectKey=maven \
//                         -Dsonar.projectName='maven' \
//                         -Dsonar.host.url=http://localhost:9000 \
//                         -Dsonar.token=sqp_d81ac1dfd0023a1300c8465363e5179bab347d76
//                     """
//                 }
//             }
//         }
//     }
//   post {
//         success {
//             echo 'Pipeline completed successfully.'
//         }
//         failure {
//             echo 'Pipeline failed.'
//         }
//     }
// }


pipeline {
    agent any

    tools {
        maven 'sonarmaven' // Ensure this matches the Maven configuration in Jenkins
    }

    environment {
        // SONAR_TOKEN = credentials('Sonarqube-token') // Replace with your SonarQube token credentials ID
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-17'
        PATH = "${JAVA_HOME}\\bin;${env.PATH}"
    }

    stages {
        // Stage 1: Checkout
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        // Stage 2: Build and Test
        stage('Build and Test') {
            steps {
                bat 'mvn clean verify' // Cleans, builds, runs tests, and generates reports
            }
        }

        // Stage 3: SonarQube Analysis
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') { // Replace 'sonarqube' with your SonarQube server configuration name
                    bat """
                        mvn sonar:sonar ^
                        -Dsonar.projectKey=sonarmaven3 ^
                        -Dsonar.sources=src/main/java ^
                        -Dsonar.tests=src/test/java ^
                        -Dsonar.junit.reportPaths=target/surefire-reports ^
                        -Dsonar.jacoco.reportPaths=target/site/jacoco/jacoco.xml ^
                        -Dsonar.pmd.reportPaths=target/pmd-duplicates.xml ^
                        -Dsonar.host.url=http://localhost:9000 ^
                        -Dsonar.login=sqp_5096e5dd26ef937b0da0b366c14e05ecf59a855b
                    """
                }
            }
        }

        // Stage 4: Quality Gate Check
        stage('Quality Gate') {
            steps {
                timeout(time: 1, unit: 'MINUTES') { // Wait for 1 minute for SonarQube quality gate result
                    script {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "Pipeline failed due to quality gate failure: ${qg.status}"
                        }
                    }
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
