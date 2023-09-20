package de.amru.game.rps.service

import de.amru.game.rps.model.Game
import de.amru.game.rps.model.Pick
import org.springframework.stereotype.Service
import java.util.*

@Service
class GameService {
    var computer: Pick? = null
    var player: Pick? = null
    var game: Game? = null

    private final val values: List<Pick> = Collections.unmodifiableList(Pick.values().toList())
    fun runGame(playerPick: String) {
        player = Pick.valueOf(playerPick)
        computer = randomChoice(values, Random().nextInt(values.size))

        game = Game(player!!, computer!!, play())


    }

    fun getResult(): Game? {
        return game
    }

    private fun randomChoice(values: List<Pick>, random: Int): Pick {
        return values.get(random)
    }

    fun play(): String {
        if (player == computer) {
            return NOBODY_WIN
        } else if (beat(computer, player)) {
            return PC_WIN
        } else {
            return PLAYER_WIN
        }
    }

    private fun beat(a: Pick?, b: Pick?): Boolean {
        return a == Pick.ROCK && b == Pick.SCISSOR || a == Pick.PAPER && b == Pick.ROCK || a ==
            Pick.SCISSOR && b == Pick.PAPER
    }
    companion object {
        const val PLAYER_WIN = "you"
        const val PC_WIN = "computer"
        const val NOBODY_WIN = "nobody"
    }
}