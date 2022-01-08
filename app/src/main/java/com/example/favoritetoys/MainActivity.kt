package com.example.favoritetoys

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.favoritetoys.R.id.action_search
import com.example.favoritetoys.utilities.NetworkUtils
import java.io.IOException
import java.net.URL

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

    /**
     * This method retrieves the search text from the EditText, constructs the URL
     * (using {@link NetworkUtils}) for the github repository you'd like to find, displays
     * that URL in a TextView, and finally fires off an AsyncTask to perform the GET request using
     * our (not yet created) {@link GithubQueryTask}
     */
    private fun makeGithubSearchQuery() {
        val query = mSearchBoxEditText.text.toString()
        val url = NetworkUtils.buildUrl(query)
        mUrlDisplayTextView.text = url.toString()

        GithubQueryTask().execute(url)

    }

    inner class GithubQueryTask : AsyncTask<URL, Void, String>() {

        override fun doInBackground(vararg params: URL?): String? {
            val searchUrl = params[0]
            var gitHubSearchResults: String? = null
            try {
                gitHubSearchResults = searchUrl?.let { NetworkUtils.getResponseFromHttpUrl(it) }
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
            return gitHubSearchResults
        }

        override fun onPostExecute(result: String?) {
            result?.let {
                mSearchResultsTextView.text = if(result.isNotEmpty()) result else ""
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == action_search) {
            makeGithubSearchQuery()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}