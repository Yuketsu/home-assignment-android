package com.example.myapplication.presentation.film.list

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.interfaces.usecases.GetAllComicsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.myapplication.presentation.comics.list.models.ComicsListResponseModel
import com.example.myapplication.presentation.comics.list.mappers.ComicsListResponseModelMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ComicsListViewModel @Inject constructor(
    private val getAllComicsUseCase: GetAllComicsUseCase,
    private val mapper: ComicsListResponseModelMapper
) :
    ViewModel() {
    private val _errorMessage = mutableStateOf("")
    private val _comics = mutableStateListOf<ComicsListResponseModel>()

    val errorMessage: String
        get() = _errorMessage.value


    val comics: List<ComicsListResponseModel>
        get() = _comics.toList()

    suspend fun getComics() {
        try {
            _comics.clear()
            viewModelScope.launch {
                    getAllComicsUseCase.execute()
                        .flowOn(Dispatchers.IO)
                        .map { it.filter { comics -> !comics.description.isNullOrEmpty() } }
                        .collect { list ->
                            _comics.addAll(list.map { mapper.toComicsListResponseModel(responseModel = it) })
                    }
            }
        } catch (err: Exception) {
            _errorMessage.value = "Error Fetching Comics"
        }
    }
}