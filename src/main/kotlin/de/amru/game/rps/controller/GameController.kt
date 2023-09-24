package de.amru.game.rps.controller

import de.amru.game.rps.model.Game
import de.amru.game.rps.model.Score
import de.amru.game.rps.service.GameService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:4200"])
@RestController
@RequestMapping("/api/games")
class GameController(private val service: GameService) {
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping("/result")
    fun getResults(): Game? = service.getResult()

    @GetMapping("/score")
    fun getScore(): Score = service.getScoring()

    @PostMapping("/choice")
    fun runGame(@Valid @RequestBody playerPick: String) = service.runGame(playerPick)

    @PostMapping("/reset")
    fun resetScore(@Valid @RequestBody score: Score) = service.resetScore(score)

}