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
						def ejecución = load 'gradle.groovy'
						break
						case 'maven':
						//ejecutar maven.groovy							
						def ejecución = load 'maven.groovy'
						break						
					}
				}
			}
		}
	}
}