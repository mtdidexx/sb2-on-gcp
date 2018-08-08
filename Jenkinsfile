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
                // LNX Sonarqube is a Sonarqube Server set up in the Jenkins > Manage Jenkins > Configure System section
                // That config needs a URL and an auth token to get the status of the analysis on SQ
                withSonarQubeEnv('LNX Sonarqube') {
                    sh "${GRADLE} --info sonarqube -Dsonar.login=${SQ_USER} -Pbuild_id=${BUILD_ID} -Dsonar.host.url=${SONAR_HOST_URL}"
                }
            }
        }
        stage('Quality Gate') {
            steps {
                echo "This is the quality gate"
                waitForQualityGate abortPipeline: true
            }
        }
    }
}