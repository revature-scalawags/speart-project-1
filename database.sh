# FILE_HADOOP=docker-hadoop
# if [[ -f "$FILE_HADOOP" ]]; then
#     echo "docker-hadoop already cloned"
# else
#     git clone https://github.com/big-data-europe/docker-hadoop.git
# fi

FILE_HIVE=docker-hive
if [[ -f "$FILE_HIVE" ]]; then
    echo "docker-hadoop already cloned"
else
    git clone https://github.com/big-data-europe/docker-hive.git
fi

# Variables
# DOCKER_NAMENODE=docker-hive_namenode_1

cd docker-hive
docker-compose down
docker-compose up -d
cd ..

# An attempt to make a directory via commandline (Don't advise)
docker exec docker-hive_namenode_1 bash -c "mkdir input"

# Goes through uploading all the files
#curl https://dumps.wikimedia.org/other/clickstream/2020-01/clickstream-enwiki-2020-01.tsv.gz --output clickstream-enwiki-2020-01.tsv.gz
#gunzip -f clickstream-enwiki-2020-01.tsv.gz
docker cp clickstream-enwiki-2020-01.tsv docker-hive_namenode_1:input/clickstream-enwiki-2020-01.tsv
# rm clickstream-enwiki-2020-01.tsv
docker exec docker-hive_namenode_1 bash -c "hdfs dfs -put input/clickstream-enwiki-2020-01.tsv input/"

# curl https://dumps.wikimedia.org/other/clickstream/2020-02/clickstream-enwiki-2020-02.tsv.gz --output clickstream-enwiki-2020-02.tsv.gz
# gunzip -f clickstream-enwiki-2020-02.tsv.gz
# docker cp clickstream-enwiki-2020-02.tsv docker-hive_namenode_1:input/clickstream-enwiki-2020-02.tsv
# rm clickstream-enwiki-2020-02.tsv
# docker exec docker-hive_namenode_1 bash -c "hdfs dfs -put input/clickstream-enwiki-2020-02.tsv input/"

# curl https://dumps.wikimedia.org/other/clickstream/2020-03/clickstream-enwiki-2020-03.tsv.gz --output clickstream-enwiki-2020-03.tsv.gz
# gunzip -f clickstream-enwiki-2020-03.tsv.gz
# docker cp clickstream-enwiki-2020-03.tsv docker-hive_namenode_1:input/clickstream-enwiki-2020-03.tsv
# rm clickstream-enwiki-2020-03.tsv
# docker exec docker-hive_namenode_1 bash -c "hdfs dfs -put input/clickstream-enwiki-2020-03.tsv input/"

# curl https://dumps.wikimedia.org/other/clickstream/2020-04/clickstream-enwiki-2020-04.tsv.gz --output clickstream-enwiki-2020-04.tsv.gz
# gunzip -f clickstream-enwiki-2020-04.tsv.gz
# docker cp clickstream-enwiki-2020-04.tsv docker-hive_namenode_1:input/clickstream-enwiki-2020-04.tsv
# rm clickstream-enwiki-2020-04.tsv
# docker exec docker-hive_namenode_1 bash -c "hdfs dfs -put input/clickstream-enwiki-2020-04.tsv input/"

# curl https://dumps.wikimedia.org/other/clickstream/2020-05/clickstream-enwiki-2020-05.tsv.gz --output clickstream-enwiki-2020-05.tsv.gz
# gunzip -f clickstream-enwiki-2020-05.tsv.gz
# docker cp clickstream-enwiki-2020-05.tsv docker-hive_namenode_1:input/clickstream-enwiki-2020-05.tsv
# rm clickstream-enwiki-2020-05.tsv
# docker exec docker-hive_namenode_1 bash -c "hdfs dfs -put input/clickstream-enwiki-2020-05.tsv input/"

# curl https://dumps.wikimedia.org/other/clickstream/2020-06/clickstream-enwiki-2020-06.tsv.gz --output clickstream-enwiki-2020-06.tsv.gz
# gunzip -f clickstream-enwiki-2020-06.tsv.gz
# docker cp clickstream-enwiki-2020-06.tsv docker-hive_namenode_1:input/clickstream-enwiki-2020-06.tsv
# rm clickstream-enwiki-2020-06.tsv
# docker exec docker-hive_namenode_1 bash -c "hdfs dfs -put input/clickstream-enwiki-2020-06.tsv input/"


# An attempt to move everything and make directories via commandline (Don't advise)
docker exec docker-hive_namenode_1 bash -c "hadoop fs -mkdir -p input"
docker exec docker-hive_namenode_1 bash -c "hdfs dfs -put input/* input"

docker exec docker-hive_hive-server_1 bash -c "mkdir sqlQueries"
docker cp createDB.sql docker-hive_hive-server_1:opt/sqlQueries/createDB.sql
docker exec docker-hive_hive-server_1 bash -c "hive -f sqlQueries/createDB.sql"

#docker exec -it docker-hive_hive-server_1 bash


# Don't execute this it is having a hard time with the "" and '' advise you going into hive to create tables
# docker exec docker-hive_hive-server_1 bash -c 'hive -e "
# create database wiki;
# use wiki; 
# create table 2020_01 (from_link string, to_link string, type string, clicks int) 
# row format delimited fields terminated by '\t';"'

# Within the docker container it can have hive execute these
# hive -f nameofSQLFILE
# hive -e "show databases;"

# Will have docker run these files in hive
# docker exec docker-hive_hive-server_1 bash -c 'hive -e "show databases;"'
# docker exec docker-hive_hive-server_1 bash -c 'hive -f filepath'

# IN HIVE - follow these commands
# echo "
# \n# docker exec -it docker-hive_hive-server_1 bash
# \n# hive
# \n# create database wiki;
# \n# use wiki; 
# \n# create table 2020_01 (from_link string, to_link string, type string, clicks int) 
# \n# row format delimited fields terminated by '\t';'
# \n# load data inpath 'input/clickstream-enwiki-2020-01.tsv' overwrite into table 2020_01;
# "
