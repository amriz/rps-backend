package de.amru.game.rps.datasource

import de.amru.game.rps.model.Game
import de.amru.game.rps.model.Pick
import de.amru.game.rps.model.Score
import org.springframework.stereotype.Repository
import java.util.*

@Repository("mock")
class MockGameDataSource : GameDataSource {
    val options: List<Pick> = Collections.unmodifiableList(Pick.values().toList())
    var game = Game(Pick.ROCK, Pick.SCISSOR, "Computer")
    var score = Score(0,0)

    override fun sendSelection(selection: String): Game {
        if ( !Pick.values().any { it.name == selection }) {
             throw NoSuchElementException("Could not find the selection $selection")
        }
        return startGame(selection)
    }

    override fun getWinner(): Game {
        return game
    }

    override fun getScoring(): Score {
        return score
    }

    override fun resetScoring(score: Score) {
        this.score = Score(score.player, score.computer)
    }

    fun startGame(playerPick: String): Game {
        val player = Pick.valueOf(playerPick)
        val computer = options.get( Random().nextInt(options.size))
        val winner = play(player, computer)
        game = Game(player, computer, winner)
        return game
    }

    fun play(player:Pick, computer:Pick): String {
        return if (player == computer) {
            NOBODY_WIN
        } else if (firstBeatSecond(computer, player)) {
            this.score.computer++
            PC_WIN
        } else {
            this.score.player++
            PLAYER_WIN
        }
    }

    private fun firstBeatSecond(a: Pick?, b: Pick?): Boolean {
        return a == Pick.ROCK && b == Pick.SCISSOR || a == Pick.PAPER && b == Pick.ROCK || a ==
                Pick.SCISSOR && b == Pick.PAPER
    }

    companion object {
        const val PLAYER_WIN = "You"
        const val PC_WIN = "Computer"
        const val NOBODY_WIN = "Nobody"
    }
}
