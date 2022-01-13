package com.example.favoritetoys

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View.*
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.transition.Visibility
import com.example.favoritetoys.R.id.action_search
import com.example.favoritetoys.utilities.NetworkUtils
import java.io.IOException
import java.net.URL
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager


class MainActivity : AppCompatActivity(), GreenAdapter.ListItemClickListener {

    private lateinit var mSearchBoxEditText: EditText
    private lateinit var mUrlDisplayTextView: TextView
    private lateinit var mSearchResultsTextView: TextView

    private lateinit var mErrorMessageTextView: TextView
    private lateinit var mProgressBar: ProgressBar

    private lateinit var mAdapter: GreenAdapter
    private lateinit var mNumbersList: RecyclerView

    private var mToast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSearchBoxEditText = findViewById(R.id.et_search_box)
        mUrlDisplayTextView = findViewById(R.id.tv_url_display)
        //mSearchResultsTextView = findViewById(R.id.tv_github_search_results_json)

        mErrorMessageTextView = findViewById(R.id.tv_error_message_display)
        mProgressBar = findViewById(R.id.pb_loading_indicator)

        mNumbersList = findViewById(R.id.rv_numbers)

        val layoutManager = LinearLayoutManager(this)
        mNumbersList.layoutManager = layoutManager
        mNumbersList.setHasFixedSize(true)

        /*
         * The GreenAdapter is responsible for displaying each item in the list.
         */
        mAdapter = GreenAdapter(NUM_LIST_ITEMS,this)

        mNumbersList.adapter = mAdapter
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

    private fun showJsonDataView() {
       // mSearchResultsTextView.visibility = VISIBLE
        mErrorMessageTextView.visibility = INVISIBLE
    }

    private fun showErrorMessage() {
        //mSearchResultsTextView.visibility = INVISIBLE
        mErrorMessageTextView.visibility = VISIBLE
    }

    inner class GithubQueryTask : AsyncTask<URL, Void, String>() {

        override fun onPreExecute() {
            mProgressBar.visibility = VISIBLE
        }

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
            mProgressBar.visibility = GONE

            result?.let {
                if (result.isNotEmpty()) {
                    //TODO - Use the new recyclerview after its implemented
                    //mSearchResultsTextView.text = result
                    showJsonDataView()
                }
            }

            if (result.isNullOrBlank()) showErrorMessage()
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

    companion object{
        private const val NUM_LIST_ITEMS = 100
    }

    override fun onListItemClick(itemClicked: Int) {
        mToast?.cancel()
        val toastMessage = "Item #$itemClicked clicked."
        mToast = Toast.makeText(this,toastMessage,Toast.LENGTH_SHORT)
        mToast?.show()

    }


}


// Do steps 6 & 7 in ChildActivity.java
// TODO (6) Create a TextView field to display your message
// TODO (7) Get a reference to your TextView in Java