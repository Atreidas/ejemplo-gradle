def call(){
  
    stage('downloadNexus') {
        steps {
            sh 'curl -X GET -u admin:Nadesico@01 http://localhost:9009/repository/maven-releases/com/devopsusach2020/DevOpsUsach2020/0.0.1/DevOpsUsach2020-0.0.1.jar -O'
        }
    }
    stage('Run') {
        steps {
            withEnv(['JENKINS_NODE_COOKIE=dontkillme']){
                sh """
                nohup java -jar DevOpsUsach2020-0.0.1.jar &
                """
            }
        }
    }
    stage('Test'){
        steps{
            echo 'Esperando a que inicie el servidor'
            sleep(time: 30, unit: "SECONDS")
            script {
                final String url = "http://localhost:8081/rest/mscovid/test?msg=testing"
                final String response = sh(script: "curl -X GET $url", returnStdout: true).trim()
                
                echo response
            }
        }
    }
    stage('uploadNexus') {
        steps {
            nexusPublisher nexusInstanceId: 'nexus', 
            nexusRepositoryId: 'feature-nexus-repo', 
            packages: [[$class: 'MavenPackage', 
            mavenAssetList: [[
                classifier: '', 
                extension: 'jar', 
                filePath: 'DevOpsUsach2020-0.0.1.jar'
            ]], 
            mavenCoordinate: [
                artifactId: 'DevOpsUsach2020', 
                groupId: 'com.devopsusach2020', 
                packaging: 'jar', 
                version: '1.0.0'
            ]]]
        }
    }

}

return this;