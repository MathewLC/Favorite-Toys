package com.example.favoritetoys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ChildActivity : AppCompatActivity() {
    private lateinit var _display_text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child)
        _display_text = findViewById(R.id.tv_display)
    }
}