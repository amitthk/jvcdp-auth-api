timeStamp:=$(shell date +%Y%m%d%H%M%S)
this_user:=$(shell $(USER))
app_context:="./docker"
aws_s3_bucket_name:=devopsmaster-repo
aws_s3_bucket_region:="us-east-1"
s3_location:=$(aws_s3_bucket_name)/auth-api/
s3_release_dir:=$(join $(s3_location),$(timeStamp)/)

.PHONY: clean build install publish

clean:
		echo "cleaning the dist directory"
		@ rm -rf $(app_context)/app
		@ cd ./docker && docker-compose stop user_api

build:
		@ mvn clean package

install:
		@ mkdir $(app_context)/app
		@ cp ./target/auth-api*.war  $(app_context)/app/auth-api.war
		@ echo this command runs under $(this_user) user
		@ cd ./docker && docker-compose up -d --build user_api


publish:
		echo publishing to S3 bucket: $(s3_release_dir)  . Make sure you have configured aws cli. Check Readme file.
		@ aws s3 cp ./target/auth-api*.war s3://$(s3_release_dir)

INFO := @bash -c '\
  printf $(YELLOW); \
  echo "=> $$1"; \
  printf $(NC)' SOME_VALUE
