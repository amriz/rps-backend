package de.amru.game.rps.datasource

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MockGameDataSourceTest {
    private val mockGameDataSource = MockGameDataSource()

    @Test
    fun `given data source should not null`() {

        val game = mockGameDataSource.sendSelection("ROCK")
        assertThat(game).isNotNull
    }
}
