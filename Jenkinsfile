pipeline {
    agent { label 'persistent-node' }
    environment {
        GRADLE = "./gradlew --no-daemon"
        SQ_USER = credentials('sonarqube-login')
    }

    stages {
        stage('Commit') {
            steps {
                sh "${GRADLE} clean build -Pbuild_id=${BUILD_ID}"
            }
        }
        stage('Sonarqube') {
            steps {
                withSonarQubeEnv('LNX Sonarqube') {
                    sh "${GRADLE} --info sonarqube -Dsonar.login=${SQ_USER} -Pbuild_id=${BUILD_ID} -Dsonar.host.url=${SONAR_HOST_URL}"
                }
            }
        }
//        stage('Quality Gate') {
//            steps {
//                withSonarQubeEnv('LNX Sonarqube') {
//                    waitForQualityGate abortPipeline: true
//                }
//            }
//        }
    }
}