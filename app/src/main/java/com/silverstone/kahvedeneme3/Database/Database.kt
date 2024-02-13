package com.silverstone.kahvedeneme3.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Asamalar::class, ButunAraclar::class, ButunKahveler::class, Malzemeler::class, KahveAraclari::class],  version = 1)
abstract class CoffeeDatabase : RoomDatabase() {
    abstract fun asamalarDao(): AsamalarDao
    abstract fun butunAraclarDao(): ButunAraclarDao
    abstract fun butunKahvelerDao(): ButunKahvelerDao
    abstract fun malzemelerDao(): MalzemelerDao

    companion object{
        private var instance:CoffeeDatabase?=null
        fun getInstance(context: Context):CoffeeDatabase{
            if (instance==null){
                instance= Room.databaseBuilder(context,CoffeeDatabase::class.java,"kahveuygulamasi.sqlite")
                    .createFromAsset("kahveuygulamasi.sqlite").build()
            }
            return instance!!
        }
    }
}