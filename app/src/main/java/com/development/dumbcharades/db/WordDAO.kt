package com.development.dumbcharades.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDAO {

    @Insert
    fun addWord(word : Word)

    @Query("SELECT COUNT(*) FROM words_collection")
    fun getCount() : LiveData<Int>

    @Query("SELECT * FROM words_collection ORDER BY id ASC")
    fun getAllWords() : LiveData<List<Word>>

    @Query("DELETE FROM words_collection")
    fun clearAll()

}