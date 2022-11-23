package com.example.klock.time

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.klock.databinding.FragmentTimeBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textview.MaterialTextView
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat


class TimeFragment : Fragment() {

    private var _binding: FragmentTimeBinding? = null
    private val binding get() = _binding!!
    private lateinit var textViewTime: MaterialTextView
    private lateinit var textViewDate: MaterialTextView
    private lateinit var timePicker: MaterialTimePicker
    private val datePicker = MaterialDatePicker.Builder.datePicker().build()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textViewTime = binding.textViewTime
        textViewDate = binding.textViewDate
        setUpTimePicker()
        setUpDatePicker()


        textViewTime.setOnClickListener {
            timePicker.show(parentFragmentManager, javaClass.simpleName)
        }

        textViewDate.setOnClickListener{
            datePicker.show(parentFragmentManager, javaClass.simpleName)
        }

    }

    private fun setUpTimePicker() {
        timePicker  = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Set Alarm")
            .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
            .build()

        timePicker.addOnPositiveButtonClickListener {
            val hour: String = if(timePicker.hour < 10) "0${timePicker.hour}" else "${timePicker.hour}"
            val minute: String = if(timePicker.minute < 10) "0${timePicker.minute}" else "${timePicker.minute}"
            val timeString = "$hour:$minute"
            textViewTime.text = timeString
        }

        // TODO: Publish a message to message broker
    }

    private fun setUpDatePicker() {
        datePicker.addOnPositiveButtonClickListener {
            val dateFormatter = SimpleDateFormat("dd/MM/yyyy")
            val date = dateFormatter.format(it)
            val split = date.split("/")
            Toast.makeText(requireContext(), "${split[0]} ${split[1]} ${split[2]}", Toast.LENGTH_SHORT).show()

            textViewDate.text = date

            // TODO: Publish a message to message broker
        }
    }

}