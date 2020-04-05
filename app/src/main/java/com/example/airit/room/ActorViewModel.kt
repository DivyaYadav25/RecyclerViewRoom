package com.example.airit.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class ActorViewModel (application: Application): AndroidViewModel(application){
    private val actorRepository : ActorRepository
    val allActor : LiveData<MutableList<ActorEntity>>

    init {
        val notificationDao = ActorRoomDB.getDatabase(application).actorDao()
        actorRepository= ActorRepository(notificationDao)
        allActor = actorRepository.allActor
    }

}

