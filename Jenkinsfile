pipeline {
    agent any

    environment {
        IMAGE_NAME = "aruna.ravichandran@verinite.com/assetmanagement"
        IMAGE_TAG = "${BUILD_NUMBER}"
        DOCKER_CREDENTIALS_ID = "docker_hub_Id"   // Jenkins Docker Hub credential ID
        GIT_CREDENTIALS_ID = "github-pat"         // Jenkins Git credential ID (if private repo)
        GIT_REPO_URL = "https://github.com/ArunaAnandhakrishnan/Asset_Managment.git"
        GIT_BRANCH = "main"
    }

    tools {
        maven "MAVEN_HOME"
    }

    stages {
        stage('Checkout Code from Git') {
            steps {
                git branch: "${env.GIT_BRANCH}",
                    url: "${env.GIT_REPO_URL}",
                    credentialsId: "${env.GIT_CREDENTIALS_ID}"
            }
        }

        stage('Build with Maven') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat "docker build -t %IMAGE_NAME%:%IMAGE_TAG% ."
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: env.DOCKER_CREDENTIALS_ID, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat """
                        docker login -u %DOCKER_USER% -p %DOCKER_PASS%
                        docker push %IMAGE_NAME%:%IMAGE_TAG%
                        docker logout
                    """
                }
            }
        }
    }

    post {
        success {
            echo "✅ Docker image pushed to Docker Hub successfully!"
        }
        failure {
            echo "❌ Pipeline failed. Check logs above for details."
        }
    }
}
