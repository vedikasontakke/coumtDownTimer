package com.example.countdowntimer

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.countdowntimer.R
import com.example.countdowntimer.SimpleCountDownTimerKotlin
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),
        SimpleCountDownTimerKotlin.OnCountDownListener {

    private val countDownTimer =
            SimpleCountDownTimerKotlin(
                    0,
                    10,
                    this
            )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resumeBtn.isEnabled = false

        startBtn.setOnClickListener {
            countDownTimer.start()

            startBtn.isEnabled = false
        }

        resumeBtn.setOnClickListener {
            countDownTimer.start(true)
            countDownTimer.runOnBackgroundThread()
        }

        pauseBtn.setOnClickListener {
            countDownTimer.pause()
            resumeBtn.isEnabled = true
            countDownTimer.setTimerPattern("s")
        }
    }

    override fun onCountDownActive(time: String) {

        runOnUiThread {

            tv.text = time

            Toast.makeText(
                    this,
                    "Seconds = " + countDownTimer.getSecondsTillCountDown() + " Minutes=" + countDownTimer.getMinutesTillCountDown(),
                    Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun onCountDownFinished() {
        runOnUiThread {
            tv.text = "Finished"
            startBtn.isEnabled = true
            resumeBtn.isEnabled = false
        }

    }
}
