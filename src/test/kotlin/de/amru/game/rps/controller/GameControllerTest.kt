package de.amru.game.rps.controller

import com.fasterxml.jackson.databind.ObjectMapper
import de.amru.game.rps.model.Game
import de.amru.game.rps.model.Pick
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

import org.springframework.http.*
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
internal class GameControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper,
) {
    val baseUrl = "/api/games"
    @Nested
    @DisplayName("POST /api/games/choice")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostChoice {
        var computer: Pick? = null
        var player: Pick? = null
        var game: Game? = null
        private final val values: List<Pick> = Collections.unmodifiableList(Pick.values().toList())
        @org.junit.jupiter.api.Test
        fun `should send the choice`() {
            // given
            val playerpick = "ROCK"
            player = Pick.valueOf(playerpick)
            computer = randomChoice(values, Random().nextInt(values.size))
            val newGame = Game(player!!, computer!!, "Computer")
//            val newGame = Game(player = values.get(0), computer = values.get(1), winner = "Computer")

            // when
            val performPost = mockMvc.post("$baseUrl/choice") {
//                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(playerpick)
            }

            // then
            performPost
                .andDo { print() }
                .andExpect {
                    content {
//                        contentType(MediaType.APPLICATION_JSON)
                        objectMapper.writeValueAsString(playerpick)
                    }
                }

            mockMvc.get("$baseUrl/result")
                .andExpect { content { objectMapper.writeValueAsString(newGame) } }
        }
        private fun randomChoice(values: List<Pick>, random: Int): Pick {
            return values.get(random)
        }
    }

/*    @LocalServerPort
    private val port = 9000

    var restTemplate = TestRestTemplate()

    var headers = HttpHeaders()

    @Test
    fun testPostPlayGame() {
        val entity = HttpEntity<String>("ROCK", headers)
        val response: ResponseEntity<String> = restTemplate.exchange<String>(
            "http://localhost:$port/api/games/choice",
            HttpMethod.POST, entity, String::class.java
        )
        assertEquals(HttpStatus.OK, response.statusCode)
    }*/
}