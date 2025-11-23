pipeline {
    agent any

    tools {
        maven 'Maven' // must match the name in Global Tool Configuration
    }

    parameters {
        booleanParam(name: 'executeBuild', defaultValue: true, description: 'Run the Build stage?')
        booleanParam(name: 'executeTests', defaultValue: true, description: 'Run the Test stage?')
    }

    environment {
        VERSION = '1.0.0'
        APP_NAME = 'Sec_Vuln_App'
    }

    stages {
        stage('Build') {
            when { expression { params.executeBuild } }
            steps {
                echo "Building ${env.APP_NAME} version ${env.VERSION}..."
                bat 'mvn -version'
                bat 'mvn clean package'
            }
        }

        stage('Test') {
            when { expression { params.executeTests } }
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
        always { echo 'Pipeline Completed' }
    }
}
