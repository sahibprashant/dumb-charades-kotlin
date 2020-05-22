package com.development.dumbcharades.viewmodels

import android.content.Context
import android.text.Spanned
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.development.dumbcharades.db.Word
import com.development.dumbcharades.db.WordDAO
import com.development.dumbcharades.formatWords
import kotlinx.coroutines.*

class HandleWordsViewModel(wordDAO: WordDAO, context: Context) : ViewModel() {

    private val wordsDao = wordDAO
    private val _wordsList = wordsDao.getAllWords()

    var wordsList: LiveData<Spanned> = Transformations.map(_wordsList){ list -> formatWords(list, context.resources)}

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun onAdd(word : String){
        uiScope.launch {
            val newWord = Word(word = word)
            insert(newWord)
        }
    }

    private suspend fun insert(newWord: Word) {
        withContext(Dispatchers.IO) {
            wordsDao.addWord(newWord)
        }
    }

    fun onClear(){
        uiScope.launch {
            clearData()
        }
    }

    private suspend fun clearData() {
        withContext(Dispatchers.IO){
            wordsDao.clearAll()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
