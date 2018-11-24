package BookApplication

class EntityToDTOConverter {

    companion object {
        fun transform(entity: BookEntity): BookDTO {

            return BookDTO(id = entity.id?.toString(),
                    title = entity.title,
                    author = entity.author,
                    releaseDate = entity.releaseDate,
                    genre = entity.genre
            )
        }

        fun transform(entities: Iterable<BookEntity>): List<BookDTO> {
            return entities.map { transform(it) }
        }

    }
}