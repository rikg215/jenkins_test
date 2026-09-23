def gv

pipeline {
    agent any
    tools {
        maven 'maven-3.9'
    }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("version") {
            steps {
                script {
                    gv.appVersion()
                }
            }
        }
        stage("build jar") {
            steps {
                script{
                    gv.buildJar()
                }
            }
        }
        stage("build image") {
            steps {
                script{
                    gv.buildImage()
                }
            }
        }
        stage("deploy") {
            steps {
                script{
                    gv.deployImage()
                }
            }
        }
        stage("commit version update") {
            steps {
                script {
                    gv.commitVersion()
                }
            }
        }
    }
}