# Operation CandyJar

A service that allows users to input any type of product identifier and get back the information that goes with that product identifier. 

README last updated: 8/02/2018

Spring Boot app that communicates with an [Angular Frontend: Augustus](https://bitbucket.dcsg.com/projects/FUL/repos/augustus/browse).

## Getting Started

* Clone the repository using `git clone [repo_url]`.
* Open Spring Tool Suite and import the project by going to `File>Import>Projects from Folder or Archive` and opening the directory the project is in.
* After the operation_candyjar project is in your project explorer, right click on the the project and click 'Configure>Add Gradle Nature`.
* Create a folder called "libs" in the root directory of your project.
* Download the odjb7.jar from https://www.oracle.com/technetwork/database/features/jdbc/jdbc-drivers-12c-download-1958347.html and place the jar in the "libs" folder.
* Right click on the project and click on `Gradle>Refresh Gradle Project`.
* You should be able to run the project now, but nothing will work until you populate the application.yaml file in `src/main/resources/` or create a new application.yaml file in the root directory of the project. This app should live on port 8012, so you should add `server.port=8012` to the properties file.
***

## Project Architecture

#### Current Versioning
* Java 8
* Spring Boot 2.0.1

#### Spring Layout
```
com.dcsg.fulfillment.candyjar
|- OperationCandyJarApplication.java
|- OperationCandyJarConfiguration.java
|- OperationCandyJarController.java
|- OperationCandyJarRepository.java
|- OperationCandyJarService.java
|- OperationCandyJarResult.java
|- RemoteCommandExecuter.java
|
|- resources
|   |-application.yaml
```
* __OperationCandyJarApplication__: Class with main method that actually runs (nothing too important in here)
* __OperationCandyJarConfiguration__: Reads config values from `application.yaml` and stores them as class fields.
* __OperationCandyJarController__: Has the API endpoints and receives client requests to delegate to `OperationCandyJarService`.
* __OperationCandyJarRepository__: Has methods for querying databases for product information based on an identifier.
* __OperationCandyJarService__: Contains the logic for connecting `OperationCandyJarRepository` with `OperationCandyJarController`.
* __OperationCandyJarResult__: Object to store results of `OperationCandyJarRepository`.
* __RemoteCommandExecuter__: Has methods to execute commands on a remote server.
* __application.yaml__: Contains configuration settings for database connection, UNIX Server login, and CORS Settings.
***

## Testing

This project uses JUnit5 to test the Spring Boot backend. 

## Authors

* **Mark Granatire**
* **Tim Hartman**
* **Timotheus Hinton**
