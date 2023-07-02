**Clone this repository to disk before conducting any of the steps below.**
**The Spring-Boot server is annotated with CrossOrigin(), thus eliminating CORS restrictions. Therefore only one compute instance is required to host both applications.**

//Prerequisites:

	1. Docker /*example installation script for fedora distro*/
		$	sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
		$	sudo yum install docker-ce docker-ce-cli containerd.io
		$	sudo systemctl start docker
	2. Maven
	3. Node.js
	4. Very Secure FTP Daemon /*used for moving files to compute instance; can be substituted for other methods of achieving the same result*/
		$	sudo yum install vsftpd -y
		$	sudo systemctl restart vsftpd
	5. Ensure that proper Ingress rules have been setup for public access to ports 8000 and 3000 of the compute instance.

REST API Server (Spring-Boot)

	STEP 1: Build the Artifact (.jar)
			- mvn clean install / reload all maven packages
			- mvn package / packages -> run maven build

	STEP 2: 
		- Retrieve /oracle_server/target/OracleSubmission-0.0.1-SNAPSHOT.jar
		- Retrieve /oracle_server/Dockerfile
		- Move to desired folder of your choice (eg. /home/opc/OracleApi)
		
	STEP 3:
		- cd to desired folder
		- Build the Docker Image
			$	sudo docker build -t oracleapi .
		- Run the Docker Image
			$	sudo docker run -p 8000:8000 oracleapi
			
				OR
			
			$	sudo docker run -d -p 8000:8000 oracleapi
				/*Detached mode*/

Client (ReactJs)

	STEP 1: Retrieve the Necessary Resources From /oracle_client/
		- /public/
		- /src/
		- Dockerfile
		- package.json
		- package-lock.json
		
	STEP 2: 
		- Move to desired folder of your choice (eg. /home/opc/OracleClient)
		- Alter the API endpoint called by Axios as per your deployment domain (/src/UserInputElements.js)
		
	STEP 3:
		- cd to desired folder
		- Build the Docker Image
			$	sudo docker build -t oracleclient .
		- Run the Docker Image
			$	sudo docker run -p 3000:3000 oracleclient
			
Testing

	React.js URL:
		http://<your-domain>:3000

	ENDPOINTS:
		http://<your-domain>:8000/api/fibonacci/post
			/*POST mapping with @RequestBody to support access by React.js frontend (Axios)*/
			
		http://<your-domain>:8000/api/fibonacci
			/*GET mapping with @RequestBody to satisfy Challenge 1 requirements*/
		
	Testing can be done with any tool of your choice (React.js, Postman, Fiddler, etc.)
	
	You may view, stop or resume any of the active containers at any time with the following commands:
		$	sudo docker ps
		$	sudo docker stop <container id/name>
		$	sudo docker restart <container id/name>
