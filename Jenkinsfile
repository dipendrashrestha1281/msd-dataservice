node {
    stage ("Checkout DataService"){
        git url: 'https://github.com/dipendrashrestha1281/msd-dataservice.git'
    }
    
    stage ("Gradle Build - DataService") {
        sh 'gradle build'
    }
    
    
    	
    stage ("Containerize the app-docker build -DataService") {
    	sh 'docker build --rm -t dataapi:v1.0 .'
    }
    	
    stage ("Inspect the docker image -DataService") {
    	sh "docker images dataapi:v1.0"
    	sh "docker inspect dataapi:v1.0"
    }	
    
    stage ("Run docker conatiner instance - DataService") {
    	sh "docker run -d --rm --name api -p 8080:8080 dataapi:v1.0"
    }
    
    
    stage('User Acceptance Test - DataService') {
	
	  def response= input message: 'Is this build good to go?',
	   parameters: [choice(choices: 'Yes\nNo', 
	   description: '', name: 'Pass')]
	
	  if(response=="Yes") {
	    stage('Deploy to Kubernetes cluster - DataService') {
	      sh "kubectl create deployment project-data --image=dataapi:v1.0"
	      sh "kubectl expose deployment project-data --type=LoadBalancer --port=8000"
	    }
	  }
    }
    
    stage ("Production Deployment View") {
    	sh "kubectl get deployments"
    	sh "kubectl get pods"
    	sh "kubectl get services"
    }
}
