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
                bat "docker build -t=auguor98/selenium ."
            }
        }

        stage('Push image'){
            steps{
                bat "docker push auguor98/selenium"
            }
            
        }
    }

    post{
        always{
            echo "doing clean up"
        }
    }


}