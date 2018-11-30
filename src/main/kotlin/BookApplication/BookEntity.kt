package BookApplication

import javax.persistence.*
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
class BookEntity(

        @get:Id
        @get:GeneratedValue
        var id: Long? = null,

        @get:NotBlank
        @get:Size(min = 4, max = 128)
        @get:Column(unique = true)
        var title: String,

        @get:NotBlank
        @get:Size(min = 2, max = 128)
        var author: String,

        //TODO Test this meow!
        @get:NotBlank
        //Regex pattern for dates taken from top comment here:
        //https://stackoverflow.com/questions/15491894/regex-to-validate-date-format-dd-mm-yyyy?answertab=votes#tab-top
        @get:Pattern(regexp = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)" +
                "(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})\$|^(?:29(\\/|-|\\.)0?2\\3" +
                "(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))" +
                "\$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})\$")
        var releaseDate: String,

        @get:Size(max = 64)
        var genre: String?
)