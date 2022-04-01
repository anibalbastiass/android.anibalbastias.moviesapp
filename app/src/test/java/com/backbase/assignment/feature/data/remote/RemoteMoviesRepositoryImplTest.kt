package com.backbase.assignment.feature.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.backbase.assignment.TestCoroutineRule
import com.backbase.assignment.feature.data.local.dao.MoviesDao
import com.backbase.assignment.feature.data.remote.mapper.RemoteMovieItemMapper
import com.backbase.assignment.feature.data.remote.state.APIState
import com.backbase.assignment.feature.domain.model.DomainMovieItem
import junit.framework.Assert.assertNotNull
import junit.framework.TestCase
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
@RunWith(MockitoJUnitRunner.Silent::class)
class RemoteMoviesRepositoryImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    internal lateinit var mockService: RemoteMoviesService

    @Mock
    internal lateinit var mockDao: MoviesDao

    @Mock
    internal lateinit var mockMapper: RemoteMovieItemMapper

    private lateinit var cut: RemoteMoviesRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        cut = RemoteMoviesRepositoryImpl(mockService, mockDao, mockMapper)
    }

    @Test
    fun `given RemoteMoviesService when call getNowPlaying then return Success API State`() =
        testCoroutineRule.runBlockingTest {
            // given
            val success = APIState.Success(listOf<DomainMovieItem>())
            val flowResult = flowOf(success)

            // when
            whenever(mockService.getMoviesByType()).thenAnswer {
                flowResult
            }

            cut.getNowPlaying()

            // then
            assertNotNull(flowResult)
            flowResult.collect { state ->
                assertEquals(success, state)
            }
        }

    @Test
    fun `given RemoteCountriesService when call getCountries then return Error API State`() =
        testCoroutineRule.runBlockingTest {
            // given
            val errorMessage = "Error"
            val error = APIState.Error(errorMessage)
            val flowResult = flowOf(error)

            // when
            whenever(mockService.getMoviesByType()).thenAnswer {
                flowResult
            }
            cut.getNowPlaying()

            // then
            assertNotNull(flowResult)
            flowResult.collect { state ->
                assertEquals(error, state)
                TestCase.assertEquals(error.error, errorMessage)
            }
        }

    @Test
    fun `given RemoteCountriesService when call getCountries then return Empty API State`() =
        testCoroutineRule.runBlockingTest {
            // given
            val emptyMessage = "Empty"
            val error = APIState.Empty(emptyMessage)
            val flowResult = flowOf(error)

            // when
            whenever(mockService.getMoviesByType()).thenAnswer {
                flowResult
            }
            cut.getNowPlaying()

            // then
            assertNotNull(flowResult)
            flowResult.collect { state ->
                assertEquals(error, state)
                TestCase.assertEquals(error.error, emptyMessage)
            }
        }
}