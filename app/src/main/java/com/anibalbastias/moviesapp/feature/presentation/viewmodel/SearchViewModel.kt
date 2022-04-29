package com.anibalbastias.moviesapp.feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(): ViewModel() {

    val searchText: MutableStateFlow<String> = MutableStateFlow("")

    fun onSearchTextChanged(changedSearchText: String) {
        searchText.value = changedSearchText
        if (changedSearchText.isEmpty()) {
//            matchedUsers.value = arrayListOf()
            return
        }
//        val usersFromSearch = allUsers.filter { x ->
//            x.username.contains(changedSearchText, true) ||
//                    x.email.contains(changedSearchText, true) || x.name.contains(
//                changedSearchText,
//                true
//            )
//        }
//        matchedUsers.value = usersFromSearch
    }

    fun onClearClick() {
        searchText.value = ""
//        matchedUsers.value = arrayListOf()
    }
}