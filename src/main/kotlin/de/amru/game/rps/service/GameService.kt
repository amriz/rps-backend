package de.amru.game.rps.service

import de.amru.game.rps.datasource.GameDataSource
import de.amru.game.rps.model.Game
import de.amru.game.rps.model.Pick
import de.amru.game.rps.model.Score
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.util.*

@Service
class GameService(@Qualifier("mock") private val dataSource: GameDataSource) {
    var game = Game(Pick.ROCK, Pick.SCISSOR, "Computer")
    var score = Score(0,0)
    val options: List<Pick> = Collections.unmodifiableList(Pick.values().toList())

    fun sendOption(player: String) : Game = startGame(player)

    fun startGame(playerPick: String): Game {
        val player = Pick.valueOf(playerPick)
        val computer = options.get( Random().nextInt(options.size))
        val winner = if (player == computer) {
            setScoring(score)
            NOBODY_WIN
        } else if (firstBeatSecond(computer, player)) {
            score.computer++
            setScoring(score)
            PC_WIN
        } else {
            score.player++
            setScoring(score)
            PLAYER_WIN
        }

        return dataSource.saveResult(player, computer, winner) // Game(player, computer, winner)
    }

    fun firstBeatSecond(a: Pick?, b: Pick?): Boolean {
        return  a == Pick.ROCK      && b == Pick.SCISSOR ||
                a == Pick.PAPER     && b == Pick.ROCK    ||
                a == Pick.SCISSOR   && b == Pick.PAPER   ||
                a == Pick.WELL      && b == Pick.ROCK    ||
                a == Pick.WELL      && b == Pick.SCISSOR ||
                a == Pick.PAPER     && b == Pick.WELL
    }

    fun getResult(): Game {
        return dataSource.getWinner()
    }

    fun getScoring(): Score {
        return dataSource.getScorings()
    }

    fun setScoring(score: Score) {
        return dataSource.saveScores(score)
    }

    fun resetScore(score: Score) {
        this.score=Score(0,0)
        setScoring(score)
    }

    companion object {
        const val PLAYER_WIN = "You"
        const val PC_WIN = "Computer"
        const val NOBODY_WIN = "Nobody"
    }
}
