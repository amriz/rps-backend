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

    fun sendOption(playerPick: String) : Game = dataSource.sendSelection(playerPick)

    fun resetScore(score: Score) {
        dataSource.resetScoring(score)
    }

    fun getResult(): Game {
        return dataSource.getWinner()
    }

    fun getScoring(): Score {
        return dataSource.getScoring()
    }
}
