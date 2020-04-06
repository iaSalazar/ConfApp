package com.demo.platziconf.view.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.demo.platziconf.R
import com.demo.platziconf.model.Conference
import com.demo.platziconf.view.adapter.ScheduleAdapter
import com.demo.platziconf.view.adapter.ScheduleListener
import com.demo.platziconf.viewmodel.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*

/**
 * A simple [Fragment] subclass.
 */
class ScheduleFragment : Fragment(), ScheduleListener {

    private lateinit var scheduleAdapter: ScheduleAdapter
    private lateinit var viewModel: ScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ScheduleViewModel::class.java)
        viewModel.refresh()

        scheduleAdapter = ScheduleAdapter(this)

        rvSchedule.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL,false )
            adapter = scheduleAdapter
        }

        observeViewModel()
    }


    fun observeViewModel(){
        viewModel.listSchedule.observe(this, Observer<List<Conference>> { schedule ->
            scheduleAdapter.updateData(schedule)

        })
        viewModel.isLoading.observe(this,Observer<Boolean> {
            if(it!=null)
                rlBaseSchedule.visibility = View.INVISIBLE
        })
    }

    override fun onConferenceClicked(conference: Conference, position: Int) {
        val bundle = bundleOf("conference" to conference)
        findNavController().navigate(R.id.ScheduleDetailFragmentDialog, bundle)
    }
}
