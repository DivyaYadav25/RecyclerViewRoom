package com.example.airit.room

import androidx.lifecycle.LiveData

class ActorRepository (actorDao: ActorDao) {
    //connects to view model class
    val allActor : LiveData<MutableList<ActorEntity>> = actorDao.getAllActor()

}