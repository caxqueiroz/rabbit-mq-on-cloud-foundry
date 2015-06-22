# rabbit-mq-on-cloud-foundry
simple app to upload file content and send to rabbit mq on CF

to send files to the app:

curl --form "file=@yourfile.txt"  http://fileproc-rmq."your cf domain"/upload

where yourfile.txt is the file you want to send and "your cf domain" is the cf paas you are using. 


