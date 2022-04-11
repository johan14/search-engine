# Search-Engine

This project is a simple search-engine implemented in Java language using OpenSearch as data storage and full-text search storage engine. 

### Content list

1. [ Description ](#desc)
2. [ Motivation ](#motivation)
3. [ Setting up and running the project ](#setup)
4. [ Application Design ](#design)
5. [ Scaling the application ](#scaleup)
6. [ Assumptions ](#assumptions)
7. [ Appendix ](#appendix)

<a name="desc"></a>
## 1. Description

The purpose of this application is to provide the user with indexing and querying functionalities in the form of Documents (ID (String), tokens (one or more tokens). The user interacts with the application through a command-line interface asking for the command ( query or index ). 
- The query command has a structure of "query SEARCH-EXPRESSION" where SEARCH-EXPRESSION is a boolean-like expression using tokens as operators (TOKEN1 & TOKEN2). 
- The index command has a structure of "index DOC-ID TOKEN1 TOKEN2 ..." where DOC-ID is the document unique ID followed by a list of tokens to be indexed.   

<a name="motivation"></a>
## 2. Motivation

The motivation for this project is related to provide the above functionalities, as well as displaying best coding practices and design practices. 

<a name="setup"></a>
## 3. Setting up and running the project

Start the OpenSearch instance using a docker image with the command:

```
docker run -p 9200:9200 -p 9600:9600 -e "discovery.type=single-node" -e "plugins.security.disabled=true" opensearchproject/opensearch:latest
```
After starting OpenSearch, run the Java application by running Main.java on the project. 

This application can also be ran using this **Docker image**: https://hub.docker.com/repository/docker/johan1404/search-engine

<a name="design"></a>
## 4. Application Design

This application is designed to be **scalable**, easily enhanced with new features since its components are **loosely coupled**, by using a dependency injection framework (**Guice**), **SOLID principles** and common creational **design patterns**.

Other features include an **external properties file**, in order to decouple hard coded properties on the source files. Also in this project in order to test the functionality, **JUnit** is used. For **internationalization** purpouses, **resource bundles** are used supporting two languages: english and albanian.

The architecture of this project is as follows:

![image](https://user-images.githubusercontent.com/28931298/161531680-8b15d892-c7bc-4976-9b66-869d94d67b9e.png)


#### The UML diagram is specified below:

![Package engine](https://user-images.githubusercontent.com/28931298/161523614-33bcfb67-c03f-46d4-b4f1-676c718c2683.png)


#### with the main functionalities provided by the Command domain: 

![commands](https://user-images.githubusercontent.com/28931298/161523805-3cd8da5e-d699-4336-8137-7211a5f2b840.png)



<a name="scaleup"></a>
## 5. Scaling the application

In order to scale up this application, this application architecture needs to change from monolith (command line interaction) into a client/server architecture (communication with REST API, SOAP or other communication protocols). In order to distribute the request evenly, an load-balancer needs to be put in between client and server. 

![image](https://user-images.githubusercontent.com/28931298/161528488-c182eafc-687e-4b90-ab92-f6202e63e7f7.png)


<a name="assumptions"></a>
## 6. Assumptions

The assumptions given in this project are: 
- OpenSearch instances are up and running
- Querying speed is more important that indexing speed


<a name="appendix"></a>
## 7. Appendix

Below are some screenshots regarding every scenario:

### Correct commands behaviour
![image](https://user-images.githubusercontent.com/28931298/161531406-ac150cda-2171-4c69-bea5-c77ac4aa1b66.png)

### Incorrect commands entered
![image](https://user-images.githubusercontent.com/28931298/161531537-9760ab74-fd4a-4198-8a4e-e945fbc34ab0.png)


