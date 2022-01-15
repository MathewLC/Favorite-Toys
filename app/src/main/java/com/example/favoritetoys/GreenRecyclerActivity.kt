package com.example.favoritetoys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GreenRecyclerActivity : AppCompatActivity(), GreenAdapter.ListItemClickListener  {

    private lateinit var mAdapter: GreenAdapter
    private lateinit var mNumbersList: RecyclerView

    private var mToast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_green_recycler)

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

    override fun onListItemClick(itemClicked: Int) {
        mToast?.cancel()
        val toastMessage = "Item #$itemClicked clicked."
        mToast = Toast.makeText(this,toastMessage, Toast.LENGTH_SHORT)
        mToast?.show()

        val destinationActivity = ChildActivity::class.java
        val intent = Intent(this,destinationActivity)
            .putExtra(ITEM_TEXT_EXTRA, toastMessage)
        startActivity(intent)

    }

    companion object {
        private const val NUM_LIST_ITEMS = 100
        const val ITEM_TEXT_EXTRA = "ITEM_TEXT_EXTRA"
    }
}