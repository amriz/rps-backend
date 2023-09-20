package de.amru.game.rps.service

import de.amru.game.rps.model.Game
import de.amru.game.rps.model.Pick
import org.springframework.stereotype.Service
import java.util.*

@Service
class GameService {
    var playerPick: String = ""
    var computer: Pick? = null
    var player: Pick? = null
    var game: Game? = null
    var winner: String = ""

    private final val values: List<Pick> = Collections.unmodifiableList(Pick.values().toList())
    val random = Random().nextInt(values.size)

    fun runGame(playerPick: String) {
        player = Pick.valueOf(playerPick)
        computer = randomChoice(values, random)

        this.playerPick = playerPick
        this.game = Game(player!!, computer!!, winner)
        this.game!!.play()
        this.winner= game!!.winner
    }

    fun getGameChoices(): Game? {
        return game
    }

    private fun randomChoice(VALUES: List<Pick>, RANDOM: Int): Pick {
        return VALUES.get(RANDOM)
    }
}