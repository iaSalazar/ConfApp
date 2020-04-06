package com.demo.platziconf.view.ui.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.demo.platziconf.R
import com.demo.platziconf.model.Speaker
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import kotlinx.android.synthetic.main.fragment_speakers_detail_dialog.*


/**
 * A simple [Fragment] subclass.
 */
class SpeakersDetailDialogFragment : DialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.fullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speakers_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tbSpeakerDetail.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.close)
        tbSpeakerDetail.setTitleTextColor(Color.WHITE)
        tbSpeakerDetail.setNavigationOnClickListener {
            dismiss()
        }

        val speaker = arguments?.getSerializable("speaker") as Speaker

        tbSpeakerDetail.title = speaker.name

        tvNameSpeaker.text = speaker.name
        tvJobTitle.text = speaker.jobtitle
        tvWorkSpeaker.text = speaker.workplace
        tvSpeakerBio.text = speaker.biography

        Glide.with(this)
            .load(speaker.image)
            .apply(RequestOptions.circleCropTransform())
            .into(ivPictureSpeaker)



        ivSpeakerTwitter.setOnClickListener {
            var intent: Intent

            try {
                context?.packageManager?.getPackageInfo("com.twitter.android", 0)
                intent = Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=${speaker.twitter}"))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            } catch (e: Exception) {
                intent = Intent(
                    Intent.ACTION_VIEW, Uri.parse("https://twitter.com/${speaker.twitter}")
                )
            }
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
    }




}
