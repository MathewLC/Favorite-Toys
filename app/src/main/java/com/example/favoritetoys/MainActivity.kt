package com.example.favoritetoys

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.*
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.transition.Visibility
import com.example.favoritetoys.utilities.NetworkUtils
import java.io.IOException
import java.net.URL
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.favoritetoys.R.id.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickOpenGitHubActivityButton(v: View) {
        val intent = Intent(this,GitHubSearchActivity::class.java)
        startActivity(intent)
    }

    fun onClickOpenRecyclerActivityButton(v: View) {
        val intent = Intent(this,GreenRecyclerActivity::class.java)
        startActivity(intent)
    }

    fun onClickOpenIntentActivityButton(v: View) {
        val intent = Intent(this,IntentActivity::class.java)
        startActivity(intent)
    }

    fun onClickOpenLifeCycleActivityButton(v: View) {
//        val intent = Intent(this,IntentActivity::class.java)
//        startActivity(intent)
    }


}