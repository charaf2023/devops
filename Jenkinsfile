
pipeline{
    agent any
    tools{
        maven 'maven-3.9.4'
    }
    stages{
        stage("building the artifact"){
            steps{
                script{
                    echo "stage of building the artifact"
                    sh "mvn build"
                }
            }
        }
        stage("building docker image"){
            steps{
                script{
                    echo "stage of building docker image"
                    withCredentials([usernamePassword(credentialsId:'docker-hub-credentials	',passwordVariable:'PASS',usernameVariable:'USER')]){
                        sh "docker build -t charaf2023/java-maven-app:1.1 ."
                        sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh "docker push charaf2023/java-maven-app:1.1"
                    }

                }
            }
        }
        stage("deploying"){
            steps{
                script{
                    echo "deploying"
                }
            }
        }

    }


}
