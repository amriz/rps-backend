package de.amru.game.rps.service

import io.mockk.mockk
import org.junit.jupiter.api.Test
import de.amru.game.rps.datasource.GameDataSource
import de.amru.game.rps.model.Score
import de.amru.game.rps.service.GameService.Companion.NOBODY_WIN
import de.amru.game.rps.service.GameService.Companion.PC_WIN
import de.amru.game.rps.service.GameService.Companion.PLAYER_WIN
import org.junit.jupiter.api.Assertions

internal class GameServiceTest  {
    private val dataSource: GameDataSource = mockk(relaxed = true)
    private val service = GameService(dataSource)

    @Test
    fun `run gamer test`(){
        val playerChoice = "ROCK"
        val game = service.sendOption(playerChoice)

        when (game.computer.name){
            "ROCK" -> Assertions.assertEquals(NOBODY_WIN, service.game.winner)
            "PAPER" -> Assertions.assertEquals(PLAYER_WIN, service.game.winner)
            else -> Assertions.assertEquals(PC_WIN, service.game.winner)
        }
    }

    @Test
    fun `run score test`(){
        val playerChoice = "ROCK"
        val game = service.sendOption(playerChoice)

        when (game.winner){
            "Computer" -> Assertions.assertEquals(Score(1,0), service.getScoring())
            "You" -> Assertions.assertEquals(Score(0,1), service.getScoring())
            else -> Assertions.assertEquals(Score(0,0), service.getScoring())
        }
    }
}
