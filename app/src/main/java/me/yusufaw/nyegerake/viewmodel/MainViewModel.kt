package me.yusufaw.nyegerake.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.yusufaw.nyegerake.data.DataSource
import me.yusufaw.nyegerake.model.Phrase
import kotlin.random.Random

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val _uiState = MutableStateFlow(Phrase("Android", "Hehe"))
    val uiState: StateFlow<Phrase> = _uiState.asStateFlow()

    fun fetchData() {
        val result = DataSource().getPhrases(context)
        val randome = Random(System.currentTimeMillis()).nextInt(from = 0, until = result.size )
        _uiState.value = result[randome]
    }

    init {
        fetchData()
    }
}