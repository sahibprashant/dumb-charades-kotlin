package com.development.dumbcharades.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(s : Int) : ViewModel(){

    private var _score = MutableLiveData<Int>()
    val score : LiveData<Int>
        get() = _score

    private var _restartGame = MutableLiveData<Boolean>()
    val restartGame : LiveData<Boolean>
        get() = _restartGame

    init {
        _score.value = s
        _restartGame.value = false
    }

    fun onRestartGame(){
        _restartGame.value = true
    }

}