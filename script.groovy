def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 

def buildImage() {
    echo "stage of building docker image"
    withCredentials([usernamePassword(credentialsId:'docker-hub-credentials	',passwordVariable:'PASS',usernameVariable:'USER')]){
        sh "docker build -t charaf2023/java-maven-app:1.1 ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push charaf2023/java-maven-app:1.1"
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this