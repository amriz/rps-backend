package de.amru.game.rps.model

data class Game(
    val player: Pick,
    val computer: Pick,
    var winner: String,
){
    companion object {
    const val PLAYER_WIN = "player"
    const val PC_WIN = "computer"
    }

    // check if selection of a beats selection of b
    private fun beat(a: Pick, b: Pick): Boolean {
        return a == Pick.ROCK && b == Pick.SCISSOR || a == Pick.PAPER && b == Pick.ROCK || a.equals(
            Pick.SCISSOR
        ) && b == Pick.PAPER
    }

    // determine the winner
    fun play() {
        if (this.player == this.computer) {
            this.winner = "no one"
        } else if (beat(this.computer, this.player)) {
            this.winner = PC_WIN
        } else {
            this.winner = PLAYER_WIN
        }
    }
}