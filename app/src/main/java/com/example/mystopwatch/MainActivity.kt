package com.example.mystopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import com.example.mystopwatch.R
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var sec = 0
    private var isRunning = false
    private var wasRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            sec = savedInstanceState.getInt("seconds")
            isRunning = savedInstanceState.getBoolean("running")
            wasRunning = savedInstanceState.getBoolean("wasRunning")
        }
        startTimer()
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("seconds", sec)
        savedInstanceState.putBoolean("running", isRunning)
        savedInstanceState.putBoolean("wasRunning", wasRunning)
    }

    override fun onPause() {
        super.onPause()
        wasRunning = isRunning
        isRunning = false
    }

    override fun onResume() {
        super.onResume()
        if (wasRunning) {
            isRunning = true
        }
    }

    fun Start(view: View) {
        isRunning = true
    }

    fun Stop(view: View) {
        isRunning = false
    }

    fun Reset(view: View) {
        isRunning = false
        sec = 0
    }

    private fun startTimer() {
        val timer = findViewById<TextView>(R.id.timer)
        val handler = Handler()

        handler.post(object : Runnable {
            override fun run() {
                val hoursVar = sec / 3600
                val minutesVar = sec % 3600 / 60
                val secsVar = sec % 60

                val timeValue = String.format(Locale.getDefault(), "%d:%02d:%02d", hoursVar, minutesVar, secsVar)

                timer.text = timeValue

                if (isRunning) {
                    sec++
                }

                handler.postDelayed(this, 1000)
            }
        })
    }
}

