package com.anibalbastias.moviesapp.feature.data.api

import com.anibalbastias.moviesapp.feature.data.api.utils.AssetReader
import com.anibalbastias.moviesapp.feature.data.api.utils.BaseMoshiParsingTest
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteMovieDataJsonAdapter
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class LocalMoviesResponseTest : BaseMoshiParsingTest() {

    companion object {
        private const val RESOURCE_MOVIES_PAGE_200 = "mock_movies_200.json"
    }

    @Before
    override fun setup() {
        super.setup()
    }

    @Test
    fun testDeserialization() {
        // given
        val testPayload = AssetReader().loadResource(RESOURCE_MOVIES_PAGE_200)

        val adapter = RemoteMovieDataJsonAdapter(moshi)

        // when
        val parsedPayload = adapter.fromJson(testPayload)!!

        // then
        assertEquals(parsedPayload.results!!.size, 20)
        parsedPayload.results!!.first().run {
            assertEquals(originalTitle, "Turning Red")
            assertEquals(_releaseDate, "2022-03-01")
            assertEquals(title, "Turning Red")
            assertEquals(id, 508947L)
        }
    }
}