pipeline {
    agent any

    parameters {
        booleanParam(name: 'executeTests', defaultValue: true, description: 'Run the Test stage?')
    }

    environment {
        VERSION = '1.0.0'
        APP_NAME = 'Sec_Vuln_App'
    }

    stages {
        stage('Build') {
            steps {
                echo "Building ${env.APP_NAME} version ${env.VERSION}..."
            }
        }

        stage('Test') {
            when {
                expression { params.executeTests == true }
            }
            steps {
                echo "Testing ${env.APP_NAME} version ${env.VERSION}..."
            }
        }

        stage('Deploy') {
            steps {
                echo "Deploying ${env.APP_NAME} version ${env.VERSION}..."
            }
        }
    }

    post {
        always {
            echo 'Pipeline Completed'
        }
    }
}
