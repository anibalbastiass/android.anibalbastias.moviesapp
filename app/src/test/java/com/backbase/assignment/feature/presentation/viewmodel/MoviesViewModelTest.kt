package com.backbase.assignment.feature.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.backbase.assignment.TestCoroutineRule
import com.backbase.assignment.feature.data.remote.state.APIState
import com.backbase.assignment.feature.domain.UiMovieDataState
import com.backbase.assignment.feature.domain.UiMovieDetailDataState
import com.backbase.assignment.feature.domain.usecase.GetMovieDetailUseCase
import com.backbase.assignment.feature.domain.usecase.GetNowPlayingMoviesUseCase
import com.backbase.assignment.feature.fixture.MoviesFixture
import com.backbase.assignment.feature.presentation.mapper.UiMovieMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
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
class MoviesViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var getNowPlayingMoviesUseCaseMock: GetNowPlayingMoviesUseCase

    @Mock
    lateinit var getMovieDetailUseCaseMock: GetMovieDetailUseCase

    @Mock
    lateinit var mapperMock: UiMovieMapper

    private lateinit var cut: MoviesViewModel

    @Mock
    lateinit var listObserver: Observer<UiMovieDataState>

    @Mock
    lateinit var detailObserver: Observer<UiMovieDetailDataState>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        cut = MoviesViewModel(getNowPlayingMoviesUseCaseMock, getMovieDetailUseCaseMock, mapperMock)
    }

    @Test
    fun `when call getNowPlayingMovies then returns success data`() =
        testCoroutineRule.runBlockingTest {
            // given
            val result = MoviesFixture.createDomainMoviesList()
            val success = with(mapperMock) { APIState.Success(result.map { it.fromDomainToUi() }) }

            // when
            cut.nowPlayingMovies.observeForever(listObserver)
            whenever(getNowPlayingMoviesUseCaseMock.execute()).thenAnswer {
                flowOf(success)
            }
            cut.getNowPlayingMovies()

            // then
            assertNotNull(cut.nowPlayingMovies.value)
            assertEquals(success, cut.nowPlayingMovies.value)
        }

    @Test
    fun `when call getNowPlayingMovies then returns error`() =
        testCoroutineRule.runBlockingTest {
            // given
            val error = APIState.Error("Error")

            // when
            cut.nowPlayingMovies.observeForever(listObserver)
            whenever(getNowPlayingMoviesUseCaseMock.execute()).thenAnswer {
                flowOf(error)
            }
            cut.getNowPlayingMovies()

            // then
            assertNotNull(cut.nowPlayingMovies.value)
            assertEquals(error, cut.nowPlayingMovies.value)
        }

    @Test
    fun `when call getNowPlayingMovies then returns empty`() =
        testCoroutineRule.runBlockingTest {
            // given
            val error = APIState.Empty("Error")

            // when
            cut.nowPlayingMovies.observeForever(listObserver)
            whenever(getNowPlayingMoviesUseCaseMock.execute()).thenAnswer {
                flowOf(error)
            }
            cut.getNowPlayingMovies()

            // then
            assertNotNull(cut.nowPlayingMovies.value)
            assertEquals(error, cut.nowPlayingMovies.value)
        }

    @Test
    fun `when call getMovieDetail then returns error`() =
        testCoroutineRule.runBlockingTest {
            // given
            val movieId = "123"
            val error = APIState.Error("Error")

            // when
            cut.detailMovies.observeForever(detailObserver)
            whenever(getMovieDetailUseCaseMock.execute(movieId)).thenAnswer {
                flowOf(error)
            }
            cut.getMovieDetail(movieId)

            // then
            assertNotNull(cut.detailMovies.value)
            assertEquals(error, cut.detailMovies.value)
        }

    @Test
    fun `when call getMovieDetail then returns empty`() =
        testCoroutineRule.runBlockingTest {
            // given
            val movieId = "123"
            val error = APIState.Empty("Error")

            // when
            cut.detailMovies.observeForever(detailObserver)
            whenever(getMovieDetailUseCaseMock.execute(movieId)).thenAnswer {
                flowOf(error)
            }
            cut.getMovieDetail(movieId)

            // then
            assertNotNull(cut.detailMovies.value)
            assertEquals(error, cut.detailMovies.value)
        }
}