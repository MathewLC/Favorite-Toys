package com.example.favoritetoys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var mToysListTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mToysListTextView = findViewById(R.id.tv_toy_names)
        val toyNames = ToyBox.getToyNames()

        toyNames.forEach { toyName ->
            mToysListTextView.append("$toyName \n\n\n")
        }
    }
}