package com.development.dumbcharades.viewmodels

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel(){

    companion object{
        private const val DONE = 0L
        private const val ONE_SECOND = 1000L
        private const val COUNTDOWN_TIME = 60000L
    }

    private var _currentTime = MutableLiveData<Long>()
    private val currentTime : LiveData<Long>
        get() = _currentTime

    /**Transformation allows us to format LiveData object and returns new formatted LiveData object*/
    val leftTimeString = Transformations.map(currentTime){ time -> DateUtils.formatElapsedTime(time)}!!

    private val timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){

        override fun onFinish() {
            _currentTime.value = DONE
            onEndGame()
        }

        override fun onTick(millisUntilFinished: Long) {
            _currentTime.value = (millisUntilFinished/ ONE_SECOND)
        }

    }

    private var _score = MutableLiveData<Int>()
    val score : LiveData<Int>
        get() = _score

    private var _currentWord = MutableLiveData<String>()
    val currentWord : LiveData<String>
        get() = _currentWord

    private var _gameFinished = MutableLiveData<Boolean>()
    val gameFinished : LiveData<Boolean>
        get() = _gameFinished

    private var words  = mutableListOf(
        "Guitar",
        "Home",
        "Car",
        "Cup of Tea",
        "Ice Cream",
        "Moon",
        "Sun",
        "Sunday",
        "Evening",
        "Game of Thrones",
        "Media",
        "Bubbles",
        "Matrix",
        "Exit"
    )

    private var newWord = { words.shuffled()[0] }

    init {
        Log.d("Init", "Called")
        _score.value = 0
        _currentWord.value = newWord()
        _gameFinished.value = false
        timer.start()
    }

    fun onCorrect(){
        _score.value = (_score.value)?.plus(1)
        _currentWord.value = newWord()
    }

    fun onSkip(){
        _score.value = (_score.value)?.minus(1)
        _currentWord.value = newWord()
    }

    fun onEndGame(){
        _gameFinished.value = true
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

}