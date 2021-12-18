package com.example.continuewatch_2

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

 class For_Exec: Application() {
     val executor : ExecutorService = Executors.newSingleThreadExecutor()
}