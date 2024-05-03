package com.gracodev.macromovies.viewmodels

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.gracodev.data.moviedata.Movie
import com.gracodev.domain.usecase.FetchMovieListUseCase
import com.gracodev.domain.usecase.FetchMoviePagingListUseCase
import com.gracodev.macromovies.states.UIStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MovieViewModel(
    private val fetchMovieListUseCase: FetchMovieListUseCase,
    private val fetchMoviePagingListUseCase: FetchMoviePagingListUseCase
) : BaseViewModel() {

    private val _loadingState = MutableStateFlow<Boolean>(false)
    val loadingState: StateFlow<Boolean> = _loadingState

    private val _uiState = MutableStateFlow<UIStates<List<Movie>>>(UIStates.Init)
    val uiState: StateFlow<UIStates<List<Movie>>> = _uiState.asStateFlow()

    private val _pagingSource = MutableStateFlow<PagingSource<Int, Movie>?>(null)
    val pagingData: StateFlow<PagingData<Movie>> = _pagingSource
        .flatMapLatest { pagingSource ->
            pagingSource?.let {
                Pager(config = PagingConfig(pageSize = 25)) {
                    it
                }.flow.cachedIn(viewModelScope)
            } ?: flow { emit(PagingData.empty()) }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), PagingData.empty())

    fun fetchMovieList() {
        viewModelScope.launch {
            try {
                _uiState.value = UIStates.Loading

                val result = fetchMovieListUseCase(1)
                _uiState.value = result.toUIStates()
            } catch (ex: Exception) {
                _uiState.value = UIStates.Error(ex.message ?: "Error Desconocido")
            }
        }
    }

    fun fetchPaginMovieList() {
        viewModelScope.launch {
            _loadingState.value = true
            _pagingSource.value = fetchMoviePagingListUseCase()
            _loadingState.value = false
        }
    }
}