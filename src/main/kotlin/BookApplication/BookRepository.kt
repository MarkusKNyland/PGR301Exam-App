package BookApplication

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : CrudRepository<BookEntity, Long>{
    fun findAllByGenre(genre: String): Iterable<BookEntity>
}