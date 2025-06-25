pipeline {
    agent any

    parameters {
        choice(name: 'ENVIRONMENT', choices: ['dev', 'test', 'prod'], description: 'Choose the environment')
        booleanParam(name: 'RUN_TESTS', defaultValue: true, description: 'Run Test Stage?')
        booleanParam(name: 'DEPLOY_TO_PROD', defaultValue: true, description: 'Run Prod Stage?')
    }

    stages {
        stage('Build') {
            steps {
                echo "Build Success for ENV: ${params.ENVIRONMENT}"
            }
        }

        stage('Test') {
            when {
                expression { return params.RUN_TESTS }
            }
            steps {
                echo "Test Success"
            }
        }

        stage('Prod') {
            when {
                expression { return params.DEPLOY_TO_PROD && params.ENVIRONMENT == 'prod' }
            }
            steps {
                echo "Prod Deployment Success"
            }
        }
    }

    post {
        always {
            emailext(
                subject: "Jenkins Job '${env.JOB_NAME} [#${env.BUILD_NUMBER}]' - Build Summary",
                body: """
                    <p><b>Job:</b> ${env.JOB_NAME}</p>
                    <p><b>Build Number:</b> #${env.BUILD_NUMBER}</p>
                    <p><b>Environment:</b> ${params.ENVIRONMENT}</p>
                    <p><b>Tests Run:</b> ${params.RUN_TESTS}</p>
                    <p><b>Prod Deploy:</b> ${params.DEPLOY_TO_PROD}</p>
                    <p><b>Build URL:</b> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
                    <p><b>Result:</b> ${currentBuild.currentResult}</p>
                """,
                to: 'arunaravi1228@gmail.com',
                mimeType: 'text/html'
            )
        }
    }
}
