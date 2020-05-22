package com.development.dumbcharades.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words_collection")
data class Word(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Long = 0L,

    @ColumnInfo(name = "word")
    var word : String = ""

)