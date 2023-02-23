#OpenJobs API

OpenJobs API is a RESTful web service developed using Spring Boot and Gradle, which provides endpoints to manage job offers, companies, candidates, and statistics related to job openings.

##Installation

To run the OpenJobs API, you will need to have the following software installed on your machine:

* Java 11 or higher
* Gradle

Once you have these dependencies installed, follow these steps:

1. Clone the repository to your local machine:
```
git clone https://github.com/andresmarnez/Openjobs.git
```

2. Navigate to the root directory of the project:
```
cd Openjobs
```

3. Build the project using Gradle:
```
gradle build
```

4. Run the project:
```
java -jar build/libs/openjobs-api-1.0.0.jar
```

The API will be available at http://localhost:8081.

##Usage

You can interact with the OpenJobs API using HTTP requests. The API supports JSON as the default format for both request and response bodies.

###Endpoints

The OpenJobs API provides the following endpoints:

####Candidates

* GET /candidates: Retrieves a list of all candidates.
* GET /candidates/{id}: Retrieves a candidate by ID.
* GET /candidates/open-for-work: Returns all candidates open to work.
* GET /candidates//interest: Returns all candidates intereted in a category.

####Offers

* GET /offers: Retrieves a list of all offers.
* GET /offers/{id}: Retrieves an offer by ID.
* GET /FILTER: Filtering the offers by pagination starting on page 1.
* GET /ACTIVE/SEARCH: Returns the offers that contain a certain word in the title.
* GET /ACTIVE/FILE-JSON: Stores all active job offers in a file and returns the path.
* POST /ADD/: Adds a new offer.
* PUT /UPDATE/{id}: Updates an existing offer in a Company.

####OpenJobs

* GET /COMPANIES: Get a list of the companies.
* GET /COMPANIES/{company_id}: Returns company's information.
* GET /COMPANIES/{company_id}/OFFERS:  Returns all the offers of a company.
* GET /COMPANIES/{company_id}/OFFERS/{num}: Returns the nth offer of a company starting by 1 and ordered by publishing date.
* DELETE /REMOVE/{id}: Deletes a Company by ID.

####Statistics

* GET /STATS/COUNTRY: Returns the number of companies per country.

##Controllers

The OpenJobs API has four controllers, one for each resource: CandidatesController, OfferController, OpenJobsController, and StatisticsController. Each controller defines the endpoints for its corresponding resource.

###WebController

The OpenJobs API also includes a WebController which uses Thymeleaf to serve a web interface for managing the API resources. The web interface is available at http://localhost:8081/web/