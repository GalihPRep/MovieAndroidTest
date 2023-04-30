import com.example.nameful7.model.others.Movie
import com.example.nameful7.model.response.MovieListResponse
import org.junit.Assert.*
import org.junit.Test

class MovieListResponseTest {

    private val movie0 = Movie(
        overview = "Hanabi and Mugi are the perfect couple.",
        originalLanguage = "jp",
        originalTitle = "クズの本懐",
        video = false,
        title = "Scum's Wish",
        genreIds = listOf(1,2,4,8,16),
        posterPath = "https://website.net/images/60/poster/3600.jpg",
        backdropPath = "https://website.net/images/60/backdrop/3600.jpg",
        releaseDate = "2017-01-13",
        popularity = 5.0f,
        voteAverage = 5.0f,
        id = 60,
        adult = true,
        voteCount = 216000
    )
    private val movie1 = Movie(
        overview = "What can I do?",
        originalLanguage = "jp",
        originalTitle = "ラブコメ?",
        video = false,
        title = "Rabudame",
        genreIds = listOf(1,3,9,27,81),
        posterPath = "https://website.net/images/9/poster/81.jpg",
        backdropPath = "https://website.net/images/9/backdrop/81.jpg",
        releaseDate = "2017-01-13",
        popularity = 5.0f,
        voteAverage = 5.0f,
        id = 9,
        adult = false,
        voteCount = 729
    )
    private val movieListResponse = MovieListResponse(
        page = 1,
        results = mutableListOf(movie0,movie1),
        totalPages = 2,
        totalResults = 20
    )

    @Test
    fun `page should match expected value`() {
        assertEquals(1, movieListResponse.page)
    }
    @Test
    fun `results should match expected value`() {
        assertEquals(2, movieListResponse.results.size)
    }
    @Test
    fun `total pages should match expected value`() {
        assertEquals(2, movieListResponse.totalPages)
    }
    @Test
    fun `total results should match expected value`() {
        assertEquals(20, movieListResponse.totalResults)
    }




    @Test
    fun `page should not match unexpected value`() {
        assertNotEquals(2, movieListResponse.page)
    }
    @Test
    fun `results should not match unexpected value`() {
        assertNotEquals(1, movieListResponse.results.size)
    }
    @Test
    fun `total pages should not match unexpected value`() {
        assertNotEquals(1, movieListResponse.totalPages)
    }
    @Test
    fun `total results should not match unexpected value`() {
        assertNotEquals(2, movieListResponse.totalResults)
    }
}
