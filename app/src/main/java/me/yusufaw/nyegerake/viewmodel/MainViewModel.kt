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
    private var phrases = listOf<Phrase>()

    fun fetchData() {
        val result = DataSource().getPhrases(context)
        phrases = result
        doRandom()
    }

    fun doRandom() {
        val randome = Random(System.currentTimeMillis()).nextInt(from = 0, until = phrases.size )
        if (phrases[randome].phrase == uiState.value.phrase) {
            doRandom()
        } else {
            _uiState.value = phrases[randome]
        }
    }

    init {
        fetchData()
    }
}