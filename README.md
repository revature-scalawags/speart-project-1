# Wikipedia Big Data Analysis

## Project Description
A Scala program that takes a uploaded file in a hive table and queries. Through a reactive CLI prompt the user can select 
 that queries Hadoop Hive. Hive will then do a map reduce through yarn on data in hdfs.


## Technologies Used

* Scala - version 2.12.3
* Java - version 11.0.9
* Hadoop - version 2.7.4
* Hive - version 3.2.1
* Docker-compose - version 1.27.4


## Features

* Read English Wikipedia Clickstream data from an HDFS cluster.
* Return a dataset different queries. Example: What is the most clicked Wikipedia page? What is the most clicked Wikipedia to Wikipedia page?
* Uses Hive by endpoint created by docker.

To-do list:
* Reduce the data through a map reduce before uploading the data in HDFS for faster quering. 


## Getting Started
   
```
git clone git@github.com:revature-scalawags/speart-project-1.git
```

**Ports that must be availiable:**

- 50075
- 50070
- 10000
- 9083
- 8080

_Common Trouble Shooting if ports are unavailable_
- net stop winnat
- docker ps #Then stop the processes that are overtaking the ports


## Usage

1. Set up the Hive single cluster and database

    **Bash**
    ```bash
    sh database.sh
    ```

    **Windows Command Propt**
    ```bash
    ./database.sh
    ```

2. Run

    ```bash
    sbt run
    ```




