package com.example.favoritetoys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var mSearchBoxEditText: EditText
    private lateinit var mUrlDisplayTextView: TextView
    private lateinit var mSearchResultsTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSearchBoxEditText = findViewById(R.id.et_search_box)
        mUrlDisplayTextView = findViewById(R.id.tv_url_display)
        mSearchResultsTextView = findViewById(R.id.tv_github_search_results_json)
    }
}