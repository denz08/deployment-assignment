pipeline {
    agent any
    stages{
        stage('code checkout') {
            steps {
                git branch: 'Dev', url: 'https://github.com/denz08/deployment-assignment.git'
            }
        }
        stage('SonarQube') {
            steps {
                withSonarQubeEnv(installationName: 'Sonar') {
                    sh 'mvn clean verify sonar:sonar'
                    sleep 60;
                }
                timeout(time: 2, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage("build") {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('publish over ssh') {
            steps([$class: 'BapSshPromotionPublisherPlugin']) {
                sshPublisher(
                    continueOnError: false, failOnError: true,
                    publishers: [
                        sshPublisherDesc(
                            configName:"deployment-ssh",
                            verbose: true,
                            transfers:[
                                sshTransfer(
                                    sourceFiles:"**/*.jar",
                                    remoteDirectory:"",
                                    execCommand:"nohup java -jar target/*.jar &"
                                )
                            ]
                        )
                    ]
                )
            }
        }
    }
}