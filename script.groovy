def appVersion() {
    echo 'updating version...'
    sh 'mvn build-helper:parse-version versions:set \
       -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
       versions:commit'
    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
    def version = matcher[0][1]
    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
}

def buildJar() {
    echo 'building the application'
    sh 'mvn clean package'
}

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'rik215', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t rik215/bootcamp-test:${IMAGE_NAME} ."
        sh 'echo $PASS | docker login -u $USER --password-stdin'
        sh "docker push rik215/bootcamp-test:${IMAGE_NAME}"
                    }
}

def deployImage() {
    echo "deploying the application..."
    echo "testing webhooks....."
}

def commitVersion() {
    withCredentials([usernamePassword(credentialsId: 'jenkins-pat-2', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'git config --global user.email "rikg215@gmail.com"'
        sh 'git config --global user.name "rikg215"'

        sh 'git status'
        sh 'git branch'
        sh 'git config --list'

        sh "git remote set-url origin https://${USER}:${PASS}@github.com/rikg215/jenkins_test.git"
        sh 'git add ,'
        sh 'git commit -m "ci: version bump'
        sh 'git push origin HEAD:feature/Dockerfile'
    }
}

return this