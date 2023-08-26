def gv
pipeline{
    agent any
    tools{
        maven 'maven-3.9.4'
    }
    stages{
        stage("inti"){
        steps{
                    script{
                        gv = load "script.groovy"
                    }

        }

        }
        stage("building the artifact"){
            steps{
                script{
                    gv.buildJar()
                }
            }
        }
        stage("building docker image"){
            steps{
                script{
                    gv.buildImage()
                    }

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
