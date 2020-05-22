package com.development.dumbcharades.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordsRoomDatabase : RoomDatabase(){

    abstract val wordsDAO : WordDAO

    /**members will become static*/
    companion object{

        /**after making this volatile no caching will be done for this variable*/
        @Volatile
        var INSTANCE : WordsRoomDatabase ?= null


        /**using synchronized so that no two instance get created at same time*/
        fun getInstance(context: Context) : WordsRoomDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,
                        WordsRoomDatabase::class.java,
                        "words_db").fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}