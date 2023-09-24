package de.amru.game.rps.service

import de.amru.game.rps.model.Score
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class GameServiceTest {

    private val service = GameService()

    @Test
    fun `run game test`(){
        val playerChoice = "ROCK"
        service.runGame(playerChoice)

        when (service.computer!!.name){
            "ROCK" -> {Assertions.assertEquals(NOBODY_WIN, service.play());Assertions.assertEquals(Score(0,0), service.getScoring())}
            "PAPER" -> {Assertions.assertEquals(PC_WIN, service.play());Assertions.assertEquals(Score(1,0), service.getScoring())}
            else -> {Assertions.assertEquals(PLAYER_WIN, service.play());Assertions.assertEquals(Score(0,1), service.getScoring())}
        }
    }

/*    @Test
    fun `run score test`(){
//        var scoring = Score(0,0)
        val playerChoice = "ROCK"
        service.runGame(playerChoice)

        val winner = service.play()
        scoring = service.getScoring()

        when (winner){
            "Computer" -> Assertions.assertEquals(Score(1,0), scoring)
            "You" -> Assertions.assertEquals(Score(0,1), scoring)
            else -> Assertions.assertEquals(Score(0,0), scoring)
        }
        scoring = Score(0,0)
    }*/

    companion object {
        const val PLAYER_WIN = "You"
        const val PC_WIN = "Computer"
        const val NOBODY_WIN = "Nobody"
    }
}