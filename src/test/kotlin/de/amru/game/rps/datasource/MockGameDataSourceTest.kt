package de.amru.game.rps.datasource

import de.amru.game.rps.service.GameService
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MockGameDataSourceTest() {
    private val dataSource: GameDataSource = mockk(relaxed = true)
    private val service = GameService(dataSource)
//    private val mockGameDataSource = MockGameDataSource(service)

    @Test
    fun `given data source should not null`() {

        val game = service.startGame("ROCK")
        assertThat(game).isNotNull
    }
}
