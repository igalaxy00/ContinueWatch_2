package com.example.continuewatch_2


import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity_Java_Threads : AppCompatActivity() {
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView
    private lateinit var bgThread: Thread


    override fun onStart() {//right
        super.onStart()
        Log.d("mainActivity", "OnStart: seconds = $secondsElapsed")
        bgThread = Thread {
            try {
                while (!Thread.currentThread().isInterrupted) {
                    Log.d("mainActivity", "${Thread.currentThread()} is iterating")
                    Thread.sleep(1000)
                    textSecondsElapsed.post {
                        textSecondsElapsed.text = getString(R.string.seconds, secondsElapsed++)
                    }
                }
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }
        }
        bgThread.start()
        super.onStart()
    }


    override fun onStop() {//right
        super.onStop()
        Log.d("mainActivity", "OnStop: seconds = $secondsElapsed")
        bgThread.interrupt()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run { putInt(SECONDS, secondsElapsed) }
        super.onSaveInstanceState(outState)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.run { secondsElapsed = getInt(SECONDS) }
    }


    companion object {
        const val SECONDS = "Seconds"
    }

}
