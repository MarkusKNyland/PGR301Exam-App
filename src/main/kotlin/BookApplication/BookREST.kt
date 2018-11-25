package BookApplication

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Api(value = "/books", description = "Handling of creating and retrieving book entries")
@RequestMapping(path = ["/books"])
@RestController
@Validated
class BookRest(
        val repository: BookRepository
) {
    @ApiOperation("Get all the books")
    @GetMapping
    fun get(
            @ApiParam("The books genre")
            @RequestParam("genre", required = false)
            genre: String?): ResponseEntity<List<BookDTO>> {

        val list = if (genre.isNullOrBlank()) {
            repository.findAll()
        } else {
            repository.findAllByGenre(genre)
        }

        return ResponseEntity.ok(EntityToDTOConverter.transform(list))
    }

    @ApiOperation("Create a game")
    @PostMapping
    fun createBook(
            @ApiParam("Specify title,, author, releaseDate and genre. Do NOT specify id")
            @RequestBody
            dto: BookDTO): ResponseEntity<Long> {

        if (!dto.id.isNullOrBlank() || dto.title.isNullOrBlank()
                || dto.author.isNullOrBlank() || dto.releaseDate.isNullOrBlank()) {
            return ResponseEntity.status(400).build()
        }

        val entity = BookEntity(null, dto.title!!, dto.author!!, dto.releaseDate!!, dto.genre)
        repository.save(entity)

        return ResponseEntity.status(201).build()

    }

    @ApiOperation("Retrieve specific book by title")
    @GetMapping(path = ["/{title}"])
    fun getBook(
            @ApiParam("Title of a book")
            @PathVariable("title")
            pathTitle: String): ResponseEntity<BookDTO> {

        val book = repository.findBookEntityByTitle(pathTitle).orElse(null)
                ?: return ResponseEntity.status(404).build()

        return ResponseEntity.ok(EntityToDTOConverter.transform(book))
    }

    @ApiOperation("Delete a book with a specific id")
    @DeleteMapping(path = ["/{id}"])
    fun deleteBook(
            @ApiParam("Id of book to delete")
            @PathVariable("id")
            pathId: String): ResponseEntity<BookDTO> {

        val id: Long
        try {
            id = pathId.toLong()
        } catch (e: Exception) {
            return ResponseEntity.status(400).build()
        }

        if (!repository.existsById(id)) {
            return ResponseEntity.status(404).build()
        }

        repository.deleteById(id)

        return ResponseEntity.status(204).build()
    }
}