package com.development.dumbcharades.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.development.dumbcharades.db.WordDAO

@Suppress("UNCHECKED_CAST")
class HandleWordsViewModelFactory(private var wordsDAO: WordDAO, private val context: Context) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HandleWordsViewModel::class.java)){
            return HandleWordsViewModel(wordsDAO, context) as T
        }
        throw IllegalArgumentException("Model Class is not HandleWordsViewModel type")
    }

}
