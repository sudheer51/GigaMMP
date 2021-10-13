pipeline {
    agent any

    stages {
        stage('HealthCheck') {
            steps {
                echo 'Hello World'
                bat 'ping 96.84.175.78 -n 5'
            }
        }
        stage('Execute Tests'){
            steps 
            {
                
                git credentialsId: 'GithubCreds', url: 'https://github.com/sudheer51/GigaMMP.git'
                dir('mmp') 
                {
                            bat 'mvn clean test'
                }
            }
        }
    }
}
