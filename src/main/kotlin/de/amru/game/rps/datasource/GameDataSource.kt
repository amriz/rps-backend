package de.amru.game.rps.datasource

import de.amru.game.rps.model.Game
import de.amru.game.rps.model.Pick
import de.amru.game.rps.model.Score

interface GameDataSource {
    fun saveResult (playerChoice: Pick, computerChoice: Pick, gameWinner: String) : Game
    fun getWinner() : Game
    fun getScorings() : Score
    fun saveScores(score: Score)
    fun resetScore(score: Score)
}
