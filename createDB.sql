CREATE DATABASE IF NOT EXISTS wiki;
USE wiki; 
CREATE TABLE IF NOT EXISTS 2020_01 (from_link string, to_link string, type string, clicks int) 
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t';
LOAD DATA LOCAL INPATH "root/input/clickstream-enwiki-2020-01.tsv" OVERWRITE INTO TABLE 2020_01;