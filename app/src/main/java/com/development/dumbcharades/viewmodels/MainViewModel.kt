package com.development.dumbcharades.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.development.dumbcharades.db.WordDAO

class MainViewModel(wordDAO: WordDAO)  : ViewModel(){

    private val wordsDao = wordDAO
    val _wordsCount =  wordsDao.getCount()


    private var _showSnackBar = MutableLiveData<Boolean>()
    val showSnackBar : LiveData<Boolean>
        get() = _showSnackBar

    private var _readyToPlay = MutableLiveData<Boolean>()
    val readyToPlay : LiveData<Boolean>
        get() = _readyToPlay

    init {
        _readyToPlay.value = false
        _showSnackBar.value = false

    }

    fun onPlay(){
        if( _wordsCount.value == 0) _showSnackBar.value = true else _readyToPlay.value = true
    }

    fun hideSnackBar() {
        _showSnackBar.value = false
    }

    fun checkReadyToPlay(){
        _readyToPlay.value = false
    }
}