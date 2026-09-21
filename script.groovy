def function(buildApp) {
    echo 'building the application'
}
return this

def function(testApp) {
    echo 'testing the application'
}
return this

def function(deployApp) {
    echo 'deploying the application'
    echo "deploying version ${params.VERSION}"
}
return this
