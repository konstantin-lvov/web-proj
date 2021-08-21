#!/bin/bash

export DOCKER_MY_NET=$(docker network ls | grep my-net | awk '{ print $2 }')
if [[ $DOCKER_MY_NET == 'my-net' ]]
then
	echo "NETWORK MY-NET EXIST"
else
	echo "NETWORK DOES NOT EXIST. CREATING NETWORK..."
	docker network create my-net
fi


rm docker-compose.yml
cp docker-compose.yml.bkp docker-compose.yml
docker-compose up -d --force-recreate summary_db
docker cp create_tables.sql summary_db:/
docker exec -ti summary_db psql -U postgres -f create_tables.sql
export LOCAL_PROJ_PATH=$(find $(pwd) -iname target)
export LOCAL_PROJ_PATH=$LOCAL_PROJ_PATH/proj
echo $LOCAL_PROJ_PATH
sed -i '' "s|#\[local-proj-path\]|- $LOCAL_PROJ_PATH|" docker-compose.yml
sed -i '' "s|#volumes|volumes|" docker-compose.yml
docker-compose up -d --force-recreate apache
export DATABASE_IP=$(docker exec summary_db hostname -i)
echo $DATABASE_IP
echo "$DATABASE_IP summary-db" > database_ip

docker cp database_ip apache:/
docker exec -u root apache /bin/sh -c "cat /database_ip >> /etc/hosts" 

echo "installing packages..."
#docker exec -u root apache /bin/sh -c "apt update > /dev/null 2>&1 && apt install net-tools telnet > /dev/null 2>&1"
#docker exec -u root summary_db /bin/sh -c "apt update > /dev/null 2>&1 && apt install net-tools telnet > /dev/null 2>&1"
