package com.development.dumbcharades.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException


@Suppress("UNCHECKED_CAST")
class ScoreViewModelFactory(private var score : Int) : ViewModelProvider.Factory{


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(ScoreViewModel::class.java)){
            return ScoreViewModel(score) as T
        }
        throw IllegalArgumentException("ModelClass is not of type ScoreViewModel")
    }

}