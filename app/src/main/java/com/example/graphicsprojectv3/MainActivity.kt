package com.example.graphicsprojectv3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.llinerotleftview.LLineRotLeftView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        LLineRotLeftView.create(this)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}