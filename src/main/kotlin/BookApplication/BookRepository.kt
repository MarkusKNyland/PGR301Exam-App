package BookApplication

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BookRepository : CrudRepository<BookEntity, Long>{
    fun findAllByGenre(genre: String): Iterable<BookEntity>
    fun findBookEntityByTitle(title: String): Optional<BookEntity>
}