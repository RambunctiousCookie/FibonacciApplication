**INSTRUCTIONS ON HOW TO BUILD AND RUN THE CONTAINER**

**BOTH DOCKERFILES CAN BE FOUND IN /opc**

These instructions assume that:

	- The user is deploying to an Oracle Cloud Compute instance.
	- User connects to said Oracle Cloud Compute instance using SSH.
	- Said Cloud Compute instance is running some Linux Fedora distro using `yum` command syntax.
	- Proper Ingress rules have been setup for public access to ports 8000 and 3000 of the compute instance.
	- Prerequisites are fulfilled.
	- This repository has been cloned to disk.
	
Prerequisites:

	- Maven
	- Node.js
	- Docker
		$	sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
		$	sudo yum install docker-ce docker-ce-cli containerd.io
		$	sudo systemctl start docker
	- Docker-Compose
		$	sudo curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
	- Very Secure FTP Daemon
		$	sudo yum install vsftpd -y
		$	sudo systemctl restart vsftpd
	
REST API Server (Spring-Boot)

	STEP 1: Build the Artifact (.jar)
		- mvn clean install / reload all maven packages
		- mvn package / packages -> run maven build
		- Retrieve /oracle_server/target/OracleSubmission-0.0.1-SNAPSHOT.jar

	STEP 2: 
		- Move to /opc/OracleAPI
		- /opc/OracleAPI already contains the correct Dockerfile

Client (ReactJs)

	STEP 1: Retrieve the Necessary Resources From /oracle_client/
		- /public/
		- /src/
		- package.json
		- package-lock.json
		
	STEP 2: 
		- Move to /opc/OracleClient
		- /opc/OracleAPI already contains the correct Dockerfile
		
Configure your Cloud Compute Instance

	STEP 1: Very Secure FTP Daemon
		- Move the contents /opc into /home/opc of your Oracle Cloud Compute instance.
		
	STEP 2: Change ENV variables
		- In /opc/docker-compose.yml there is a setting called `REACT_APP_API_URL`.
		- Change this to the public url of your Cloud Compute Instance <your-domain>, appended by `:8000`.
			(eg. REACT_APP_API_URL = http://138.2.74.181:8000)
			
	STEP 3: Docker-Compose
		$	cd /home/opc
		
		- For regular launch:
		$	docker-compose up
		
		- For regular detached head mode:
		$	docker-compose up -d
		
		- Congratulations! The application is now deployed and running.
			
Testing

	- React.js URL:
		http://<your-domain>:3000
		(eg. My deployment is at: http://138.2.74.181:3000)

	ENDPOINTS:
	
	- POST mapping with @RequestBody to support access by React.js frontend (Axios)
		http://<your-domain>:8000/api/fibonacci/post
		(eg. My deployment is at: http://138.2.74.181:8000/api/fibonacci/post)

	- GET mapping with @RequestBody to satisfy Challenge 1 requirements
		http://<your-domain>:8000/api/fibonacci
		(eg. My deployment is at: http://138.2.74.181:8000/api/fibonacci)

		
	Testing can be done with any tool of your choice (React.js, Postman, Fiddler, etc.)
	
	You may view or stop any of the active containers at any time with the following commands:
		$	docker-compose ps
		$	docker-compose down

