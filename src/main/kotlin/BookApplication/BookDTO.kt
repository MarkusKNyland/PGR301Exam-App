package BookApplication

import io.swagger.annotations.ApiModelProperty

data class BookDTO (

        @ApiModelProperty("Id of a book")
        var id: String? = null,

        @ApiModelProperty("Title of the book")
        var title: String? = null,

        @ApiModelProperty("The author of the book")
        var author: String? = null,

        @ApiModelProperty("The date a book got released")
        var releaseDate: String? = null,

        @ApiModelProperty("The genre of the specified book")
        var genre: String? = null
)