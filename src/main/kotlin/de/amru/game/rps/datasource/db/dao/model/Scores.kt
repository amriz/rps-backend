package de.amru.game.rps.datasource.db.dao.model

import jakarta.persistence.*

@Entity
@Table(name = "SCORES")
data class Scores(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(nullable = true)
    val name: String,
    val score: Int
)
