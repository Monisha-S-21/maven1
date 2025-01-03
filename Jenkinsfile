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
        maven 'sonarmaven' // Define Maven tool name from Jenkins tool configuration
    }

    dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.9.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.9.0'
}
            
    environment {
        SONARQUBE = 'SonarQube Analysis' // Set your SonarQube server name from Jenkins configuration
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-17'
        PATH = "${JAVA_HOME}\\bin;${env.PATH}"
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build and Test') {
            steps {
                script {
                    // Run the Maven build with test execution
                    bat '''
                    mvn clean verify sonar:sonar \
                  -Dsonar.projectKey=maven1 \
                  -Dsonar.projectName='maven1' \
                  -Dsonar.host.url=http://localhost:9000 \
                  -Dsonar.token=sqp_0e25c2f83ba63c760fd57e1caab96649160b04ed
                    '''
                }
            }
        }
        stage('Test Reports') {
    steps {
        // Publish test results using JUnit
        junit '**/target/surefire-reports/*.xml' // Correct path to test report files
    }
}

        stage('SonarQube Analysis') {
            steps {
                script {
                    // Trigger SonarQube analysis using the SonarQube Scanner plugin
                    bat 'mvn sonar:sonar -Dsonar.host.url=http://your-sonarqube-server:9000 -Dsonar.login=sqa_9619c288c8a09e5c2349a8a492590339a3f61184'
                }
            }
        }
    }
    post {
        success {
            // Actions after a successful build
            echo 'Build and tests were successful!'
        }
        failure {
            // Actions on build failure
            echo 'Build or tests failed.'
        }
    }
}
