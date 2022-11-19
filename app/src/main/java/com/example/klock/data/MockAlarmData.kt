package com.example.klock.data

import com.example.klock.models.Alarm

class MockAlarmData {
    fun loadData() : MutableList<Alarm> {
        return mutableListOf(
            Alarm(12,12, 1),
            Alarm(12,0, 0),
            Alarm(13, 20, 0),
            Alarm(14, 30, 1),
            Alarm(15, 0, 0)

        )
    }
}