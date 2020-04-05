package com.example.airit

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.airit.adapter.ActorAdapter
import com.example.airit.databinding.ActivityMainBinding
import com.example.airit.room.ActorEntity
import com.example.airit.room.ActorRoomDB
import com.example.airit.room.ActorViewModel
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityMainBinding
    val TAG= MainActivity::class.java.simpleName
    private lateinit var actorAdapter: ActorAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        initView()
    }

    private fun initView() {
         actorAdapter = ActorAdapter()
        mBinding.myAdapter = actorAdapter
        //fetches record from server
        GetDetails().execute()
        //observes changes in adapter
        val actorViewModel = ViewModelProviders.of(this).get(ActorViewModel::class.java)
        actorViewModel.allActor.observe(this, Observer { actor ->
            actor?.let { actorAdapter.setActor(it) }
        })
    }
    private inner class GetDetails : AsyncTask<Void, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()

        }

        override fun doInBackground(vararg arg0: Void?): String? {
            val sh = HttpHandler()
            // Making a request to url and getting response
            val jsonStr = sh.makeServiceCall(AppConstants.URL)
            try {
                val jArray = JSONArray(jsonStr.toString())
                // Extract data from json and store into ArrayList as class objects
                for (i in 0 until jArray.length()) {
                    val json_data = jArray.getJSONObject(i)
                    val actorEntity = ActorEntity()
                    actorEntity.name = json_data.getString("name")
                    actorEntity.phone = json_data.getString("phone")
                    actorEntity.image = json_data.getString("image")
                    //store data in local database when fetched from server
                    ActorRoomDB.getDatabase(this@MainActivity).actorDao().insert(actorEntity)

                }
            }
            catch (e: Exception){
                Log.d(TAG, "Exception " + e.toString())
            }
           return null
        }
    }
}
