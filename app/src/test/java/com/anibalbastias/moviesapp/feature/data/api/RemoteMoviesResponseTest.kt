package com.anibalbastias.moviesapp.feature.data.api

import com.anibalbastias.moviesapp.BuildConfig
import com.anibalbastias.moviesapp.feature.data.api.utils.MockWebServerTest
import com.anibalbastias.moviesapp.feature.data.remote.RemoteMoviesService
import com.squareup.moshi.Moshi
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RemoteMoviesResponseTest : MockWebServerTest() {

    private lateinit var service: RemoteMoviesService

    @Before
    override fun before() {
        super.before()

        val okHttpClient = OkHttpClient.Builder().connectionPool(ConnectionPool()).build()
        val moshi = MoshiConverterFactory.create(Moshi.Builder().build())

        service = Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(moshi)
            .client(okHttpClient)
            .build()
            .create(RemoteMoviesService::class.java)
    }

    @After
    override fun after() {
        super.after()
    }

    @Test
    fun testMockedResponse() {
        // given
        val mockResponse = MockResponse().setResponseCode(200)
        server.enqueue(mockResponse)

        // when
        val response = runBlocking {
            service.getMoviesByType()
        }

        // then
        response.body()?.results?.let { list ->
            assertEquals(list.size, 20)
        }
    }
}