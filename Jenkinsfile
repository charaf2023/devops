#!/usr/bin/env groovy

@Library('jenkins-shared-library')
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
                    buildJar()
                }
            }
        }
        stage("building docker image") {
            steps {
                script {
                    buildImage 'charaf2023/java-maven-app:1.4'
                    dockerLogin ()
                    dockerPush 'charaf2023/java-maven-app:1.4'
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