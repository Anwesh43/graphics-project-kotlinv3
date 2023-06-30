package com.example.graphicsprojectv3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.dropballlinedownview.DropBallLineDownView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        DropBallLineDownView.create(this)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}