# web-scraper

### How to run

1.Locate the pre-built jar file in the target directory

2.Using a terminal window run the following command

`Java -jar tech-exercise-1.0-jar-with-dependencies.jar {url to scrape}`

replace `{url to scrape}` with the url of the page you intend to scrape for products;

### Testing

The tests in the application consist of unit and 'integration' tests. To run these tests you will need to open the project in the IDE of your choice and run as you normally would.

### Dependencies

The dependencies required to run the application are :

1. Java 1.8

All other dependencies should be packaged into the application

### Next steps

Currently the application mostly works for its intended purpose but can be improved in the following ways:

* Altering the queries used to select elements to prevent "sponsored" items being picked up
* Behavioural testing in the form of Cucumber tests
* Add a dependency injection framework
* Extract the queries used to select elements to make the application easier to adapt should the html page change
