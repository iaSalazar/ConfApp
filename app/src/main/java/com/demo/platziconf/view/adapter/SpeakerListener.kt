package com.demo.platziconf.view.adapter

import com.demo.platziconf.model.Speaker

interface SpeakerListener {
    fun onSpeakerClicked(speaker: Speaker, position: Int)
}