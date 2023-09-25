package de.amru.game.rps.datasource

import de.amru.game.rps.model.Game
import de.amru.game.rps.model.Score

interface GameDataSource {
    fun sendSelection (selection: String) : Game
    fun getWinner() : Game
    fun getScoring() : Score
    fun resetScoring(score: Score)
}
