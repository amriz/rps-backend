package de.amru.game.rps.controller

import com.fasterxml.jackson.databind.ObjectMapper
import de.amru.game.rps.model.Score
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
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
        @Test
        fun `should send the choice`() {
            // given
            val playerSelection = "ROCK"
            val resultObject ="Game(player=ROCK, computer=PAPER, winner=Computer)"

            val newScore = Score(1,0)

            // when
            val performPost = mockMvc.post("$baseUrl/choice") {
                contentType = MediaType.TEXT_PLAIN
                content = playerSelection
            }

            // then
            performPost
                .andDo { print() }
                .andExpect {
                    content {
                        objectMapper.writeValueAsString(resultObject)
                    }
                }

            mockMvc.get("$baseUrl/result")
                .andExpect { content { objectMapper.writeValueAsString(resultObject) } }

            mockMvc.get("$baseUrl/score")
                .andExpect { content { objectMapper.writeValueAsString(newScore) } }
        }

        @Test
        fun `should return BAD REQUEST if the selection does not exist`() {
            // given
            val invalidSelection = "ROCKS"

            // when
            val performPost = mockMvc.post("$baseUrl/choice") {
                contentType = MediaType.TEXT_PLAIN
                content = invalidSelection
            }

            // then
            performPost
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }

    }

    @Nested
    @DisplayName("POST /api/games/choice")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostScore {
        @Test
        fun `should reset the score`() {
            // given
            val resetScore = Score(0,0)

            // when
            val performPost = mockMvc.post("$baseUrl/reset") {
                content = resetScore
            }

            // then
            performPost
                .andExpect {
                    content {
                        objectMapper.writeValueAsString(resetScore)
                    }
                }

            mockMvc.get("$baseUrl/score")
                .andExpect { content { objectMapper.writeValueAsString(resetScore) } }
        }
    }
}
