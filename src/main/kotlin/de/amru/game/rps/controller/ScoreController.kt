package de.amru.game.rps.controller

import de.amru.game.rps.datasource.db.dao.model.Scores
import de.amru.game.rps.service.ScoreService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:4200"])
@RestController
@RequestMapping("/api/games")
class ScoreController(val service: ScoreService) {
    /*@GetMapping("/scores")
    fun getScores(): Scores = service.getScores()*/

   /* @PostMapping("/record")
    fun saveScore(@Valid @RequestBody scores: Scores) = service.saveScores(scores.name, scores.score)*/
}