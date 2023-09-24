package de.amru.game.rps.service

import de.amru.game.rps.datasource.db.dao.model.Scores
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ScoreService(val repository: CrudRepository<Scores, Long>) {
    @Transactional
    fun saveScores(name: String, score: Int) {
        var scores = Scores(1, name, score)
        repository.save(scores)
    }

    fun getScores(): Scores {
        return repository.findAll().first()
    }
}