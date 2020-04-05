package com.example.airit.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.airit.AppConstants

@Dao
interface ActorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(actor: ActorEntity)

    @Query("DELETE FROM ${AppConstants.ACTOR_TABLE} WHERE actor_id=:actor_id")
    fun deleteByActorId(actor_id: String)

    @Query("SELECT * from ${AppConstants.ACTOR_TABLE}")
    fun getAllActor() : LiveData<MutableList<ActorEntity>>

}