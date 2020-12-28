pipeline {
	agent any 
	parameters {
		choice(name: 'herramienta', choices: ['gradle', 'maven'], description: 'Selecciona la herramienta de construcción')
	}
	stages {
		stage('Pipeline'){
			steps {
				script {
					switch(params.herramienta) {
						case 'gradle':
						//ejecutar gradle.groovy
						def ejecucion = load ' gradle.groovy'
						break
						case 'maven':
						//ejecutar maven.groovy							
						def ejecucion = load ' maven.groovy'
						break						
					}
				}
			}
		}
	}
}
