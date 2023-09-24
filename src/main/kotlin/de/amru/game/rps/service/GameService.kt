package de.amru.game.rps.service

import de.amru.game.rps.model.Game
import de.amru.game.rps.model.Pick
import de.amru.game.rps.model.Score
import org.springframework.stereotype.Service
import java.util.*

@Service
class GameService {
    var computer: Pick? = null
    var player: Pick? = null
    var game: Game? = null
    var score = Score(0,0)

    private final val values: List<Pick> = Collections.unmodifiableList(Pick.values().toList())
    fun runGame(playerPick: String) {
        player = Pick.valueOf(playerPick)
        computer = randomChoice(values, Random().nextInt(values.size))
        game = Game(player!!, computer!!, play())
    }

    fun resetScore(score: Score) {
        this.score = Score(score.player, score.computer)
    }

    fun getResult(): Game? {
        return game
    }

    fun getScoring(): Score {
        return score
    }

    private fun randomChoice(values: List<Pick>, random: Int): Pick {
        return values.get(random)
    }

    fun play(): String {

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