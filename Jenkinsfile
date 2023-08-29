#!/usr/bin/env groovy

@Library('jenkins-shared-library')
def gv
pipeline {
    agent any
    tools{
        maven 'maven-3.9.4'
    }
    environment{
        TOKEN=credentials('github-token')
    }
    stages {
        stage("incrementing the version") {
            steps {
                script {
                    echo 'incrementing app version'
                    sh 'mvn build-helper:parse-version versions:set -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit'
                    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    def version = matcher[0][1]
                    env.IMAGE_NAME = "charaf2023/java-maven-app:$version-$BUILD_NUMBER"
                }
            }
        }
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
                    buildImage ("${IMAGE_NAME}")
                }
            }
        }
        stage("login") {
            steps {
                script {
                    dockerLogin ()
                }
            }
        }
        stage("push docker image") {
            steps {
                script {
                    dockerPush ("${IMAGE_NAME}")
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
        stage("committing to REPO") {
            steps {
                script {
                    pushToGithub ("${env.TOKEN}")
                }
            }
        }
    }
}