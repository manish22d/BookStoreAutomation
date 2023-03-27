package com.scb.stepdef.functional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scb.builder.Request;
import com.scb.pojo.Book;
import com.scb.services.BookServices;
import com.scb.utils.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class BookDetailsSteps {
    @Autowired
    ScenarioContext context;

    @Autowired
    BookServices bookServices;

    Response response;
    Request request;
    String book_id;
    Book book;

    @Given("user wants to perform operation on {string}")
    public void userWantsToUpdateDetailsOf(String book_id) {
        this.book_id = book_id;
    }

    @Given("user have correct book details")
    public void userWantToCreateBookRequest(List<Map<String, String>> bookData) {
        book = new ObjectMapper().convertValue(bookData.get(0), Book.class);

    }

    @Given("user have valid request")
    public void userHaveValidRequest() {

    }

    @When("user want to get book list")
    public void userTriggersGetRequest() {
        response = bookServices.getBookList();
    }

    @When("user triggers get request individual book")
    public void userTriggersGetRequestIndividualBook() {
        response = bookServices.getBookById(Integer.parseInt(book_id));
    }

    @When("user triggers delete request individual book")
    public void userTriggersDeleteRequestIndividualBook() {
        response = bookServices.deleteBook(Integer.parseInt(book_id));
    }


    @And("validate book does not exists in book list")
    public void validateBookDoesNotExistsInBookList() {
        List<Book> books = Arrays.asList(bookServices.getBookList().as(Book[].class));
        assertThat(String.format("book id %s was found in book list even after delete", book_id),
                !books.stream().map(Book::getId).toList().contains(Integer.parseInt(book_id)));
    }


    @When("user send request to create book")
    public void userSendRequestToCreateBook() {
        response = bookServices.createBook(book);

    }


    @When("user send request to update book")
    public void userSendRequestToUpdateBook() {
        response = bookServices.updateBook(Integer.parseInt(book_id), book);
    }

    @And("validate correct book details is retrieved")
    public void validateCorrectBookDetailsIsRetrieved() {
        Book book = response.as(Book.class);
        assertThat(book.getId(), is(equalTo(book_id)));
    }

    @And("book details are updated correctly")
    public void bookDetailsAreUpdatedCorrectly() {
        response = bookServices.getBookById(response.as(Book.class).getId());
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        book.assertEquals(response.as(Book.class));
    }


    @Then("transaction should be consistent on request is sent {int} times")
    public void transactionShouldBeConsistentOnRequestIsSentTimes(int count) {
        List<Book> book_Ids = Arrays.asList(response.as(Book[].class));
        for (int i = 0; i < count; i++) {
            List<Book> books = Arrays.asList(bookServices.getBookList().as(Book[].class));
            assertThat(String.format("Observed non consistent response on iteration %d", i), CollectionUtils.isEqualCollection(books, book_Ids));
        }

    }

    @And("verify user is able get details of new book")
    public void verifyUserIsAbleGetDetailsOfNewBook() {
        response = bookServices.getBookById(response.as(Book.class).getId());
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        book.assertEquals(response.as(Book.class));
    }

    @And("verify name updated correctly")
    public void verifyNameUpdatedCorrectly() {
        String response_name = response.as(Book.class).getName();
        assertThat(String.format("book in request: '%s', book in response: '%s'", book.getName(), response_name),
                book.getName(), is(equalTo(response_name)));
    }

    @And("verify author updated correctly")
    public void verifyAuthorUpdatedCorrectly() {
        String response_author = response.as(Book.class).getAuthor();
        assertThat(String.format("book in request: '%s', book in response: '%s'", book.getAuthor(), response_author),
                book.getAuthor(), is(equalTo(response_author)));
    }

    @And("verify publication updated correctly")
    public void verifyPublicationUpdatedCorrectly() {
        String response_publication = response.as(Book.class).getPublication();
        assertThat(String.format("book in request: '%s', book in response: '%s'", book.getPublication(), response_publication),
                book.getPublication(), is(equalTo(response_publication)));
    }

    @And("verify category updated correctly")
    public void verifyCategoryUpdatedCorrectly() {
        String response_category = response.as(Book.class).getCategory();
        assertThat(String.format("book in request: '%s', book in response: '%s'", book.getCategory(), response_category),
                book.getCategory(), is(equalTo(response_category)));
    }

    @And("verify pages updated correctly")
    public void verifyPagesUpdatedCorrectly() {
        long response_page = response.as(Book.class).getPages();
        assertThat(String.format("book in request: '%s', book in response: '%s'", book.getPages(), response_page),
                book.getName(), is(equalTo(response_page)));
    }

    @And("verify price updated correctly")
    public void priceAuthorUpdatedCorrectly() {
        double response_price = response.as(Book.class).getPrice();
        assertThat(String.format("book in request: '%s', book in response: '%s'", book.getPrice(), response_price),
                book.getPrice(), is(equalTo(response_price)));
    }
}
