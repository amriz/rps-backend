package de.amru.game.rps.controller

import de.amru.game.rps.model.Game
import de.amru.game.rps.service.GameService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:4200"])
@RestController
@RequestMapping("/api/games")
class GameController(private val service: GameService) {

    @GetMapping("/choice")
    fun getGameChoices(): Game? = service.getGameChoices()

    @PostMapping("/choice")
    fun runGame(@Valid @RequestBody playerPick: String) = service.runGame(playerPick)

}