package de.amru.game.rps.model

data class Game(
    val player: Pick,
    val computer: Pick,
    var winner: String,
)