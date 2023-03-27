package com.example.myapplication.presentation.film.list

import android.icu.text.CaseMap
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.interfaces.usecases.GetAllComicsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.myapplication.domain.models.ComicResponseModel
import java.lang.Exception
import javax.inject.Inject

data class ComicsListResponseModel(
    val id: String,
    val title: String,
)

fun ComicResponseModel.toComicsListResponseModel(): ComicsListResponseModel {
    return ComicsListResponseModel(
        id = id.toString(),
        title = title,
    )
}

@HiltViewModel
class ComicsListViewModel @Inject constructor(
    private val getAllComicsUseCase: GetAllComicsUseCase
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
            val list = getAllComicsUseCase.execute()
            _comics.addAll(list.map { it.toComicsListResponseModel() })
        } catch (err: Exception) {
            _errorMessage.value = "Error Fetching Comics"
        }
    }
}