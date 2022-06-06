#!/bin/bash
OID=$1
docker cp report.sql summary_db:/
docker exec summary_db sed -i "s/OID/$OID/g" report.sql
docker exec summary_db psql -U postgres -f report.sql
