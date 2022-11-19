package com.example.klock.alarms

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.klock.adapter.AlarmAdapter
import com.example.klock.data.MockAlarmData
import com.example.klock.databinding.FragmentAlarmsBinding
import com.example.klock.models.Alarm
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_CLOCK
import com.google.android.material.timepicker.TimeFormat.CLOCK_24H


class AlarmsFragment : Fragment() {

    private var _binding: FragmentAlarmsBinding? = null
    private val binding get() = _binding!!
    private lateinit var dataSet: MutableList<Alarm>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AlarmAdapter
    val timePicker: MaterialTimePicker =
        MaterialTimePicker.Builder()
            .setTimeFormat(CLOCK_24H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Set Alarm")
            .setInputMode(INPUT_MODE_CLOCK)
            .build()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAlarmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Get reference to Recycler View
        recyclerView = binding.alarmRecyclerView

        // Init dataSet
        dataSet = mutableListOf<Alarm>()

        // Load MockData
        dataSet = MockAlarmData().loadData()

        // Set Adapter
        adapter = AlarmAdapter(dataSet)
        recyclerView.adapter = adapter
        adapter.setOnRecyclerViewItemClickedListener(object : AlarmAdapter.OnRecyclerViewItemClickedListener {
            override fun onAlarmClicked(position: Int) {
                timePicker.show(parentFragmentManager,"TAG")
                timePicker.addOnPositiveButtonClickListener {
                    dataSet[position].hour = timePicker.hour
                    dataSet[position].minute = timePicker.minute
                    Toast.makeText(requireContext(), "Changed Alarm Time", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onSwitchClicked(position: Int) {
                Toast.makeText(context, "Switch at $position",Toast.LENGTH_SHORT).show()
            }

        })

        recyclerView.setHasFixedSize(true)
        Log.d("TAG", "onViewCreated: dataset$dataSet")


    }

}