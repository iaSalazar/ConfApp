package com.demo.platziconf.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.demo.platziconf.R
import com.demo.platziconf.model.Speaker
import com.demo.platziconf.view.adapter.SpeakerAdapter
import com.demo.platziconf.view.adapter.SpeakerListener
import com.demo.platziconf.viewmodel.SpeakersViewModel
import kotlinx.android.synthetic.main.fragment_speakers.*

/**
 * A simple [Fragment] subclass.
 */
class SpeakersFragment : Fragment(),SpeakerListener {

    private lateinit var speakerAdapter: SpeakerAdapter
    private lateinit var viewModel: SpeakersViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SpeakersViewModel::class.java)
        viewModel.refresh()

        speakerAdapter = SpeakerAdapter(this)

        rvSpeakers.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = speakerAdapter
        }

        observeViewModel()
    }


    fun observeViewModel(){
        viewModel.listSpeakers.observe(this, Observer<List<Speaker>> { speakers ->
            speakers.let {

                speakerAdapter.updateData(speakers)
            }


        })
        viewModel.isLoading.observe(this,Observer<Boolean> {
            if(it!=null)
                rlBase.visibility = View.INVISIBLE
        })
    }

    override fun onSpeakerClicked(speaker: Speaker, position: Int) {

        val bundle = bundleOf("speaker" to speaker)
        findNavController().navigate(R.id.speakersDeatailFragmentDialog, bundle)
    }
}
