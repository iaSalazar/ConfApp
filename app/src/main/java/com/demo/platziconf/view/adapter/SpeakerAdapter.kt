package com.demo.platziconf.view.adapter

import android.media.Image
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.demo.platziconf.R
import com.demo.platziconf.model.Speaker
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SpeakerAdapter(val speakerListener: SpeakerListener) : RecyclerView.Adapter<SpeakerAdapter.ViewHolder>(){

    var listSpeaker =  ArrayList<Speaker>()
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


        val tvSpeakerName = itemView.findViewById<TextView>(R.id.SpeakerName)
        val tvSpeakerWork = itemView.findViewById<TextView>(R.id.SpeakerWork)
        val tvSpeakerPic = itemView.findViewById<ImageView>(R.id.SpeakerPic)

    }

    fun updateData(data: List<Speaker>) {

        listSpeaker.clear()
        listSpeaker.addAll(data)
        notifyDataSetChanged()

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)  = SpeakerAdapter.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_speaker, parent, false))

    override fun getItemCount()= listSpeaker.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val speaker = listSpeaker[position] as Speaker

        Glide.with(holder.itemView.context)
            .load(speaker.image)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.tvSpeakerPic)

        holder.tvSpeakerName.text = speaker.name
        holder.tvSpeakerWork.text = speaker.workplace

        holder.itemView.setOnClickListener {
            speakerListener.onSpeakerClicked(speaker, position)
        }
    }


}