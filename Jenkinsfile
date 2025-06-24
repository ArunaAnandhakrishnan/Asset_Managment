pipeline {
        agent any

        stages {
            stage('Build') {
                steps {
                    echo 'Build Success'
                }
            }
            stage('Test') {
                steps {
                    echo 'Test Success'
                }
            }
            stage('Prod') {
                steps {
                    echo 'Prod Success'
                }
            }
        }
        post {
            always {
                emailext body:'Build ', subject:'Summary', to:'arunaravi1228@gmail.com'
            }

        }
    }
