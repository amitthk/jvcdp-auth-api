import java.text.SimpleDateFormat

stage 'Build'
node {
currentBuild.result = "SUCCESS"
  try{
   def mvnHome;
   def project_id;
   def artifact_id;
   def aws_s3_bucket_name;
   def aws_s3_bucket_region;
   def timeStamp;
   def baseDir;
   
   stage('Initalize'){
   //Get these from parameters later
       mvnHome = tool 'Maven3.5.0'
	   project_id = 'authapi';
	   aws_s3_bucket_name = 'jvcdp-repo';
	   aws_s3_bucket_region = 'ap-southeast-1';
	   timeStamp = getTimeStamp();
       baseDir = pwd();
//	   artifact_id = version();
   }
   stage('Checkout') { // for display purposes
      // Get some code from a GitHub repository
      checkout scm;
   }
//    stage('Code Analysis'){
//        sh "'${mvnHome}/bin/mvn' clean jacoco:prepare-agent package jacoco:report"
//    }
   stage('Build') {
      // Run the maven build
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' clean package"
      } else {
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
      }
   }
  stage('Maven Release')
  {
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' clean deploy -Ps3 -s ${baseDir}/scripts/settings.xml"
      } else {
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean deploy -Ps3 -s scripts\settings.xml/)
      }
  }
   stage('Stash')
   {
      stash includes: 'target/*.war', name: 'target'
   }

    stage('Send Build to S3')
    {
    unstash 'target';
    withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'aws-deployuser', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']])  
	 {
        awsIdentity() //show us what aws identity is being used
        def targetLocation = project_id + '/builds/' + timeStamp;
        withAWS(region: aws_s3_bucket_region) {
        s3Upload(file: 'target', bucket: aws_s3_bucket_name, path: targetLocation)
        }
     }
    }

    stage ('Deploy SIT'){
        sh 'echo "Call branch specific playbook to deploy"';
    }
  } catch (err) {

        currentBuild.result = "FAILURE"

        throw err
    }
}

def getTimeStamp(){
	def dateFormat = new SimpleDateFormat("yyyyMMddHHmm")
	def date = new Date()
	return dateFormat.format(date);
}

def version() {
    def ver = readFile('pom.xml') =~ '<version>(.+)</version>'
    ver ? ver[0][1] : null
    def art = readFile('pom.xml') =~ '<artifactId>(.+)</artifactId>'
    art ? art[0][1] : null
    def pck = readFile('pom.xml') =~ '<packaging>(.+)</packaging>'
    pck ? pck[0][1] : null
	version = art+ver ? art + '-' + ver + '.' + pck : artifactId + '.war'
	return version;
}

def mvn(args) {
    _mvnHome = tool 'Maven3.5.0'
    sh "${_mvnHome}/bin/mvn ${args}"
}

def runTests(duration) {
    node {
        checkout scm
        runWithServer {url ->
            mvn "-o -f sometests test -Durl=${url} -Dduration=${duration}"
        }
    }
}

def deploy(id) {
    //unstash 'war'
    //sh "cp x.war /tmp/webapps/${id}/${version()}"
	echo 'call the playbook to deploy this war now.  sh ansible-playbook main.yaml --extra-vars "deploy_environment=test ansible_task=deploy-authapi deploy_host=localhost"';
	unstash 'target';
	sh 'make clean install';
}

def undeploy(id) {
    sh "rm /tmp/webapps/${id}.war"
}

def runWithServer(body) {
    def id = UUID.randomUUID().toString()
    deploy id
    try {
        body.call "${jettyUrl}${id}/"
    } finally {
        undeploy id
    }
}
