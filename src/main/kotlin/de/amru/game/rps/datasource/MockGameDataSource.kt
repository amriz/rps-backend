package de.amru.game.rps.datasource

import de.amru.game.rps.model.Game
import de.amru.game.rps.model.Pick
import de.amru.game.rps.model.Score
import org.springframework.stereotype.Repository
import java.util.*

@Repository("mock")
class MockGameDataSource() : GameDataSource {

    var game = Game(Pick.ROCK, Pick.SCISSOR, "Computer")
    var score = Score(0,0)

    override fun saveResult(playerChoice: Pick, computerChoice: Pick, gameWinner: String): Game {
        if ( !Pick.values().any { it.name == playerChoice.name }) {
             throw NoSuchElementException("Could not find the selection ${playerChoice.name}")
        }

        this.game = Game(playerChoice, computerChoice, gameWinner)
        return this.game
    }

    override fun getWinner(): Game {
        return game
    }

    override fun getScorings(): Score {
        return this.score
    }

    override fun saveScores(score: Score) {
        this.score = Score(score.player, score.computer)
    }

    override fun resetScore(score: Score) {
        saveScores(this.score)
    }

}
