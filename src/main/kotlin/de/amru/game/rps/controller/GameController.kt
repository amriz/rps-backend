package de.amru.game.rps.controller

import de.amru.game.rps.model.Game
import de.amru.game.rps.model.Score
import de.amru.game.rps.service.GameService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:4200"])
@RestController
@RequestMapping("/api/games")
class GameController(private val service: GameService) {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleNotFound(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @PostMapping("/choice")
    fun runGame(@RequestBody playerPick: String) = service.sendOption(playerPick)

    @GetMapping("/result")
    fun getResults(): Game = service.getResult()

    @PostMapping("/reset")
    fun resetScores(@RequestBody score: Score) = service.resetScore(score)

    @GetMapping("/score")
    fun getScores(): Score = service.getScoring()
}
