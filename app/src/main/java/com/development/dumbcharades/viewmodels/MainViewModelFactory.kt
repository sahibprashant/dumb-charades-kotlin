package com.development.dumbcharades.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.development.dumbcharades.db.WordDAO
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private var wordDAO: WordDAO) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(wordDAO) as T
        }
        throw IllegalArgumentException("Model class is not of type T")
    }
}
