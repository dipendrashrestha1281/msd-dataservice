node {
    stage ("Checkout DataService"){
         git branch: 'main', url: 'https://github.com/dipendrashrestha1281/msd-dataservice'
    }
    
    stage ("Gradle Build - DataService") {
        sh 'gradle clean build'
    }
    
    stage ("Gradle Bootjar-Package - AuthApi") {
        sh 'gradle bootjar'
    }
    	
    stage ("Containerize the app-docker build -DataService") {
    	sh 'docker build --rm -t test-data:v1.0 .'
    }
    	
    stage ("Inspect the docker image -DataService") {
    	sh "docker images test-api:v1.0"
    	sh "docker inspect test-api:v1.0"
    }	
    
    stage ("Run docker conatiner instance - DataService") {
    	sh "docker run -d --rm --name testapi -p 8080:8080 test-api:v1.0"
    }
    
    
    stage('User Acceptance Test - DataService') {
	
	  def response= input message: 'Is this build good to go?',
	   parameters: [choice(choices: 'Yes\nNo', 
	   description: '', name: 'Pass')]
	
	  if(response=="Yes") {
	    stage('Deploy to Kubernetes cluster - DataService') {
	      sh "kubectl create deployment test-project-data --image=dataapi:v1.0"
	      sh "kubectl expose deployment test-project-data --type=LoadBalancer --port=8000"
	    }
	  }
    }
    
    stage ("Production Deployment View") {
    	sh "kubectl get deployments"
    	sh "kubectl get pods"
    	sh "kubectl get services"
    }
}
