def buildJar() {
    echo 'building the application'
    sh 'mvn package'
}

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'rik215', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t rik215/bootcamp-test:jma-2.0 .'
        sh 'echo $PASS | docker login -u $USER --password-stdin'
        sh 'docker push rik215/bootcamp-test:jma-2.0'
                    }
}

def deployImage() {
    echo "deploying the application..."
}

return this