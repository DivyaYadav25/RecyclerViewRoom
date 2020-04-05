package com.example.airit.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.airit.AppConstants

@Database(entities = arrayOf(ActorEntity::class),version = 4)
abstract class ActorRoomDB : RoomDatabase() {
    abstract fun actorDao() : ActorDao
    companion object{
        @Volatile
        private var INSTANCE: ActorRoomDB? = null
        fun getDatabase(context: Context): ActorRoomDB {
            return INSTANCE ?: synchronized(this) {
                // Create database here
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ActorRoomDB::class.java,
                    "${AppConstants.ACTOR_DATABASE}"
                )//.addCallback(WordDatabaseCallback(scope))
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}