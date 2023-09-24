package de.amru.game.rps.datasource.db.dao

import de.amru.game.rps.datasource.db.dao.model.Scores
import org.springframework.data.repository.CrudRepository

interface ScoreRepository : CrudRepository<Scores, Long> {
}