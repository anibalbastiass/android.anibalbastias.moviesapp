package com.anibalbastias.moviesapp.feature.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.anibalbastias.moviesapp.TestCoroutineRule
import com.anibalbastias.moviesapp.feature.data.remote.RemoteMoviesRepositoryImpl
import com.anibalbastias.moviesapp.feature.data.remote.state.APIState
import com.anibalbastias.moviesapp.feature.fixture.MoviesFixture
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetMovieDetailUseCaseTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    internal lateinit var mockRemoteRepository: RemoteMoviesRepositoryImpl

    private lateinit var cut: GetMovieDetailUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        cut = GetMovieDetailUseCase(mockRemoteRepository)
    }

    @Test
    fun `given movieId true when execute then return Success API State`() =
        testCoroutineRule.runBlockingTest {
            // given
            val movieId = ""
            val result = MoviesFixture.createDomainMovieDetail()
            val success = APIState.Success(result)
            val flowResult = flowOf(success)

            // when
            whenever(mockRemoteRepository.getMovieById(movieId)).thenAnswer {
                flowResult
            }
            cut.execute(movieId)

            // then
            assertNotNull(flowResult)
            flowResult.collect { state ->
                assertEquals(success, state)
            }
        }

    @Test
    fun `given movieId true when execute then return Error API State`() =
        testCoroutineRule.runBlockingTest {
            // given
            val movieId = ""
            val error = APIState.Error("Error")
            val flowResult = flowOf(error)

            // when
            whenever(mockRemoteRepository.getMovieById(movieId)).thenAnswer {
                flowResult
            }
            cut.execute(movieId)

            // then
            assertNotNull(flowResult)
            flowResult.collect { state ->
                assertEquals(error, state)
            }
        }

    @Test
    fun `given movieId true when execute then return Empty API State`() =
        testCoroutineRule.runBlockingTest {
            // given
            val movieId = ""
            val empty = APIState.Empty("Empty")
            val flowResult = flowOf(empty)

            // when
            whenever(mockRemoteRepository.getMovieById(movieId)).thenAnswer {
                flowResult
            }
            cut.execute(movieId)

            // then
            assertNotNull(flowResult)
            flowResult.collect { state ->
                assertEquals(empty, state)
            }
        }
}