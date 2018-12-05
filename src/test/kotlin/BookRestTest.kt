import BookApplication.BookDTO
import io.restassured.RestAssured
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Test
import org.springframework.http.MediaType

class BookRestTest : ApiTestBase() {

    private fun initWithSomeBooks() {
        createBook("Truthwitch", "Susan Dennard", "5.1.2016", "Fantasy")
        createBook("Nevermoor", "Jessica Townsend", "10.10.2017", "Fantasy")
        createBook("Den store hageboka", "Hag Hagesen", "3.7.2014", "Educational")
    }

    private fun createBook(title: String, author: String, releaseDate: String, genre: String?) {
        RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(BookDTO(null, title, author, releaseDate, genre))
                .post()
                .then()
                .statusCode(201)
    }

    @Test
    fun testGetWithGenreParameter(){
        initWithSomeBooks()

        RestAssured.given().param("genre", "Fantasy")
                .get()
                .then()
                .statusCode(200)
                .body("size()", equalTo(2))
    }

    @Test
    fun getAll(){
        initWithSomeBooks()

        RestAssured.given()
                .get()
                .then()
                .statusCode(200)
                .body("size()", equalTo(3))
    }

    @Test
    fun testDoubleDelete(){
        initWithSomeBooks()

        val title = "Truthwitch"

        //Ps with only one element size returns number of key/value pair in map.
        val bookId = RestAssured.given()
                .get("/$title")
                .then().statusCode(200)
                .body("size()", equalTo(5))
                .extract().path<Any>("id")

        RestAssured.delete("/$bookId").then().statusCode(204)

        RestAssured.delete("/$bookId").then().statusCode(404)
    }

}