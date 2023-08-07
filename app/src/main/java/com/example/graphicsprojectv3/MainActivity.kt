package com.example.graphicsprojectv3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.barcaprotrightview.BarCapRotRightView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        BarCapRotRightView.create(this)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}