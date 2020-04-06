package com.demo.platziconf.network

import com.demo.platziconf.model.Conference
import com.demo.platziconf.model.Speaker
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings


class FirestoreService {
    val firebaseFirestore = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    init {
        firebaseFirestore.firestoreSettings = settings

    }

    fun getSpeakers(callback: Callback<List<Speaker>>) {

        firebaseFirestore.collection("speakers")
            .orderBy("category")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result){
                    val list = result.toObjects(Speaker::class.java)
                    callback.onSucces(list)
                    break
                }
            }
    }
    fun getSchedule(callback: Callback<List<Conference>>) {
        firebaseFirestore.collection("conferences")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result){
                    val list = result.toObjects(Conference::class.java)
                    callback.onSucces(list)
                    break
                }
            }

    }
}