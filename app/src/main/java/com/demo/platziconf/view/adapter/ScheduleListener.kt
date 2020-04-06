package com.demo.platziconf.view.adapter

import com.demo.platziconf.model.Conference


interface ScheduleListener {
    fun onConferenceClicked(conference: Conference, position: Int)
}