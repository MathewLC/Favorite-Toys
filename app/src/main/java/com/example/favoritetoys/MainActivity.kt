package com.example.favoritetoys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.favoritetoys.R.id.action_search
import com.example.favoritetoys.utilities.NetworkUtils

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

    fun makeGithubSearchQuery(){
        val query = mSearchBoxEditText.text.toString()
        val url = NetworkUtils.buildUrl(query)
        mUrlDisplayTextView.text = url.toString()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == action_search) {
           makeGithubSearchQuery()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}