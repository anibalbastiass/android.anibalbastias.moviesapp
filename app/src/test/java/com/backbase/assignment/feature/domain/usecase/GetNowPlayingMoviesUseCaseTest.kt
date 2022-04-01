package com.backbase.assignment.feature.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.backbase.assignment.TestCoroutineRule
import com.backbase.assignment.feature.data.remote.RemoteMoviesRepositoryImpl
import com.backbase.assignment.feature.data.remote.state.APIState
import com.backbase.assignment.feature.fixture.MoviesFixture
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
class GetNowPlayingMoviesUseCaseTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    internal lateinit var mockRemoteRepository: RemoteMoviesRepositoryImpl

    private lateinit var cut: GetNowPlayingMoviesUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        cut = GetNowPlayingMoviesUseCase(mockRemoteRepository)
    }

    @Test
    fun `when execute then return Success API State`() =
        testCoroutineRule.runBlockingTest {
            // given
            val result = MoviesFixture.createDomainMovieDetail()
            val success = APIState.Success(result)
            val flowResult = flowOf(success)

            // when
            whenever(mockRemoteRepository.getNowPlaying()).thenAnswer {
                flowResult
            }
            cut.execute()

            // then
            assertNotNull(flowResult)
            flowResult.collect { state ->
                assertEquals(success, state)
            }
        }

    @Test
    fun `when execute then return Error API State`() =
        testCoroutineRule.runBlockingTest {
            // given
            val error = APIState.Error("Error")
            val flowResult = flowOf(error)

            // when
            whenever(mockRemoteRepository.getNowPlaying()).thenAnswer {
                flowResult
            }
            cut.execute()

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
            val empty = APIState.Empty("Empty")
            val flowResult = flowOf(empty)

            // when
            whenever(mockRemoteRepository.getNowPlaying()).thenAnswer {
                flowResult
            }
            cut.execute()

            // then
            assertNotNull(flowResult)
            flowResult.collect { state ->
                assertEquals(empty, state)
            }
        }
}