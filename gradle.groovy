def call(){
    stage('Build & Test') {
        env.TAREA = 'Build & Test' 
        sh "gradle clean build" 
    }

    stage('Sonar'){
        env.TAREA = 'Sonar' 
        def scannerHome = tool 'sonar_scanner';
            withSonarQubeEnv(installationName: 'Sonar') {
            sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
        }
    }

    stage('Run'){
        env.TAREA = 'Run' 
        sh "nohup sdfsd gradle bootRun &"
        sleep 20
    }

    stage('Check app'){
        env.TAREA = 'Check app' 
        sh 'curl -X GET http://localhost:8081/rest/mscovid/test?msg=testing'
    }

    stage('Nexus'){
        env.TAREA = 'Nexus' 
        nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'test-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: '/build/libs/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]
    }
}

return this;