# Labforward code challenge
This repository contains backend codebase for Labforward code challenge.
<br>Technology stack: Java11, SpringBoot 2.5.0, ElasticSearch 7.13.0

## Scope of the work
* Exposed REST endpoints for the following operations.
    * Create - `POST` `/api/v1/lab-notes`
        * Example payload: 
      ```
      {
      "data": "Word Word Word word"
      }
      ```
    * Get - `GET` `/api/v1/lab-notes`
    * Delete (To delete all records) - `DELETE` `/api/v1/lab-notes`
    * Search - `GET` `/api/v1/lab-notes/search?q=searchstring`

## Efforts spent on development
Total hours spent on development of the application is as follows.
* Back-end development: 3.5 hours
* Front-end development: 3 hours

## Further enhancements
The following enhancements shall be done to the codebase if allowed to work further.
* Pagination implementation to the Get/Search REST APIs
* Swagger UI implementation
* Code coverage
* Performance/Load testing and optimization of code.

## Labforward UI
A simple UI is implemented to navigate above REST APIs. 

<br>Technology Stack: Typescript, Angular 10, Angular Material Design.

<br>The UI artifacts generated and committed to `static` folder in `resources` directory of spring-boot application.
So, the UI shall be accessible from this Spring-Boot application with URL `http://localhost:8080/`.
The source code for UI is available in another repository at [labforward-task-ui](https://github.com/purushred/labforward-task-ui).
