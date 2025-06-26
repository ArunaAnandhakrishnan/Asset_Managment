pipeline {
    agent any

    parameters {
        string(name: 'VERSION_TAG', defaultValue: '', description: 'Optional: Enter custom Docker image tag (e.g. v1.0.0), or leave empty to use build number')
    }

    environment {
        IMAGE_NAME = "arunaanandhakrishnan/assetmanagement"
        DOCKER_CREDENTIALS_ID = "docker_hub_Id"       // Your Docker Hub credentials ID in Jenkins
        GIT_CREDENTIALS_ID     = "github-pat"          // Your GitHub PAT credentials ID
        GIT_REPO_URL           = "https://github.com/ArunaAnandhakrishnan/Asset_Managment.git"
        GIT_BRANCH             = "main"
    }

    tools {
        maven "MAVEN_HOME" // Make sure Jenkins has this Maven tool configured
    }

    stages {
        stage('Set Image Tag') {
            steps {
                script {
                    def tag = params.VERSION_TAG?.trim()
                    if (!tag) {
                        tag = env.BUILD_NUMBER
                    }
                    // Replace invalid Docker tag characters like colon (:)
                    tag = tag.replaceAll("[:]", "-")
                    env.IMAGE_TAG = tag
                    echo "✅ Using image tag: ${env.IMAGE_TAG}"
                }
            }
        }

        stage('Checkout Code from GitHub') {
            steps {
                git branch: env.GIT_BRANCH,
                    url: env.GIT_REPO_URL,
                    credentialsId: env.GIT_CREDENTIALS_ID
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
            echo "✅ Docker image %IMAGE_NAME%:%IMAGE_TAG% pushed to Docker Hub successfully!"
        }
        failure {
            echo "❌ Build failed. Please check the console output for error details."
        }
    }
}
