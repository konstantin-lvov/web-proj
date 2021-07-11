#!/bin/bash

docker-compose up -d --force-recreate summary_db
docker exec -ti summary_db psql -U postgres -f create_tables.sql
docker-compose up -d --force-recreate apache
docker exec -ti apache sed -I "s#appBase=/"webapps/"#appBase=/"ROOT/"#" /usr/local/tomcat/conf/server.xml
docker cp apache:/usr/local/tomcat/conf/server.xml .
export SERVER_XML_PATH=find $(pwd) -iname "server.xml"
echo $SERVER_XML_PATH
sed -i "s#/#- [server-xml-path]#$SERVER_XML_PATH#"
docker-compose up -d --force-recreate apache

