# BookStoreAutomation
This project intended to Automate Testing of given book store api. This is Java based Rest Assured project. It follows BDD framework.

## Running using maven command
* we can use below maven command to run test either via command prompt or pipeline.
    * ```mvn clean test -Dcucumber.options="--tags @test"```

## Observed issues in APIs
### Get Book list
* duplicate object is added in list when request is sent multiple times

### Post request
* api is not reading pages from user input, it is defaulted to 464
* few missing field is resulting to 500 error, it should be 400
*  .00001 is added in price when request is sent multiple times

### Put Request
* api is not reading pages from user input, it is defaulted to 464
* when user sending blank object, page is defaulted to 464 and rest attribute is updated to null. where as it should be 400 status code
* 500 error code when request is sent for non-existing Id

### delete
* 500 error code when request is sent for non-existing Id

### getBookById
* 500 error code when request is sent for non-existing Id where as 404 for id which is deleted


# Note : API stopped working on sunday, hence dont hv complete execution report.
