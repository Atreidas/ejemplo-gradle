pipeline {
	agent any 
	parameters {
		choice(name: 'herramienta', choices: ['gradle', 'maven'], description: 'Selecciona la herramienta de construcción')
	}
	stages {
		stage('Pipeline'){
			steps {
				script {
					env.TAREA = '' 
					env.NOMBRE_ALUMNO = "Eduardo Duhart"

					switch(params.herramienta) {
						case 'gradle':
						//ejecutar gradle.groovy
						def ejecucion = load 'gradle.groovy'
						break
						case 'maven':
						//ejecutar maven.groovy							
						def ejecucion = load 'maven.groovy'
						break						
					}
				}
			}
		}
	}
	post {
		success {
			slackSend color: 'good', message: "[${env.NOMBRE_ALUMNO}][${env.JOB_NAME}][${params.herramienta}] Ejecución exitosa.", tokenCredentialId: 'slackedgtests'
		}
		failure {
			slackSend color: 'danger', message: "[${env.NOMBRE_ALUMNO}][${env.JOB_NAME}][${params.herramienta}] Ejecución fallida en stage [${env.TAREA}}]", tokenCredentialId: 'slackedgtests'
		}
	}
}
