def gv
pipeline {
    agent any
    tools{
        maven 'maven-3.9.4'
    }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("building the artifact") {
            steps {
                script {
                    echo "building jar"
                    gv.buildJar()
                }
            }
        }
        stage("building docker image") {
            steps {
                script {
                    echo "building image"
                    gv.buildImage()
                }
            }
        }
        stage("deploying"){
            steps{
                script{
                    gv.deployApp()
                }
            }
        }
    }
}