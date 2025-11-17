pipeline{

    agent any

    stages{
        stage('Build Jar'){
            steps{
                bat "mvn clean package -DskipTests"
            }
        }

        stage('Build image'){
            steps{
                bat "docker build -t=auguor98/selenium:latest ."
            }
        }

        stage('Push image'){
            environment{
                DOCKER_HUB = credentials('Docker_Main')
            }
            steps{
                bat 'echo %DOCKER_HUB_PSW% | docker login -u %DOCKER_HUB_USR% --password-stdin'
                bat "docker push auguor98/selenium:latest"
                bat "docker tag auguor98/selenium:latest auguor98/selenium:${env.BUILD_NUMBER}"
                bat "docker push auguor98/selenium:${env.BUILD_NUMBER}"
            }
            
        }
    }

    post{
        always{
            bat "docker logout"
        }
    }


}