package com.demo.platziconf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.platziconf.model.Speaker
import com.demo.platziconf.network.Callback
import com.demo.platziconf.network.FirestoreService
import java.lang.Exception

class SpeakersViewModel : ViewModel() {
    val firestoreService = FirestoreService()
    var listSpeakers: MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getSpeakerFromFirebase()
    }

    fun getSpeakerFromFirebase(){
        firestoreService.getSpeakers(object: Callback<List<Speaker>> {
            override fun onSucces(result: List<Speaker>?) {
                listSpeakers.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    fun processFinished() {
        isLoading.value=true
    }
}