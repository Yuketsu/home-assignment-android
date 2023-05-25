package com.example.myapplication.presentation.film.list

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.interfaces.usecases.GetAllComicsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.myapplication.presentation.comics.list.models.ComicsListResponseModel
import com.example.myapplication.presentation.comics.list.mappers.ComicsListResponseModelMapper
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
            val list = getAllComicsUseCase.execute().filter { !it.description.isNullOrEmpty() }
            _comics.addAll(list.map { mapper.toComicsListResponseModel(responseModel = it) })
        } catch (err: Exception) {
            _errorMessage.value = "Error Fetching Comics"
        }
    }
}