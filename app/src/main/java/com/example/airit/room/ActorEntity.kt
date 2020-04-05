package com.example.airit.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.airit.AppConstants

@Entity(tableName = AppConstants.ACTOR_TABLE)
data class ActorEntity (

@PrimaryKey(autoGenerate = true) @ColumnInfo(name = AppConstants.ACTOR_ID)var id: Int =0,
    @ColumnInfo(name = AppConstants.ACTOR_NAME)var name: String? = null,
    @ColumnInfo(name=AppConstants.ACTOR_PHONE)var phone:String? = null,
    @ColumnInfo(name=AppConstants.ACTOR_IMAGE)var image:String?=null
)