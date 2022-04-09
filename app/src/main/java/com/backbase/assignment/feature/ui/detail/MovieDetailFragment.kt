//package com.backbase.assignment.feature.ui.detail
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.appcompat.content.res.AppCompatResources
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import androidx.navigation.fragment.navArgs
//import coil.load
//import com.backbase.assignment.R
//import com.backbase.assignment.databinding.FragmentMovieDetailBinding
//import com.backbase.assignment.feature.data.remote.state.APIState
//import com.backbase.assignment.feature.presentation.viewmodel.MoviesViewModel
//import com.backbase.assignment.feature.presentation.model.UiMovieDetail
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class MovieDetailFragment : Fragment() {
//
//    private val args: MovieDetailFragmentArgs by navArgs()
//    private val viewModel: MoviesViewModel by viewModels()
//    private lateinit var binding: FragmentMovieDetailBinding
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View {
//        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        observeMovieDetail()
//
//        binding.ivBack.setOnClickListener {
//            requireActivity().onBackPressed()
//        }
//    }
//
//    private fun observeMovieDetail() {
//        viewModel.detailMovies.value?.let { dataState ->
//            renderState(dataState)
//        } ?: run {
//            viewModel.getMovieDetail(args.movieId)
//        }
//
//        viewModel.detailMovies.observe(viewLifecycleOwner) { dataState ->
//            renderState(dataState)
//        }
//    }
//
//    private fun renderState(dataState: APIState<UiMovieDetail>) {
//        when (dataState) {
//            is APIState.Empty -> hideProgress()
//            is APIState.Error -> hideProgress()
//            APIState.Loading -> {
//                binding.pbLoading.visibility = View.VISIBLE
//            }
//            is APIState.Success -> {
//                renderSuccess(dataState.data)
//            }
//        }
//    }
//
//    private fun renderSuccess(data: UiMovieDetail) {
//        binding.run {
//            pbLoading.visibility = View.GONE
//            poster.load(data.posterPath)
//            title.text = data.originalTitle
//            releaseDate.text = data.releaseDate
//            overviewValue.text = data.overview
//
//            data.genres.map { genre ->
//                fbGenres.addView(getGenreTextView(genre))
//            }
//        }
//    }
//
//    private fun getGenreTextView(genre: String): TextView {
//        val tv = TextView(requireContext())
//        tv.background =
//            AppCompatResources.getDrawable(requireContext(), R.drawable.genre_background)
//        tv.text = genre
//        return tv
//    }
//
//    private fun hideProgress() {
//        binding.pbLoading.visibility = View.GONE
//    }
//}