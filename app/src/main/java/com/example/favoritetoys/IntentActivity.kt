package com.example.favoritetoys

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ShareCompat

class IntentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)
    }

    /**
     * This method is called when the Open Website button is clicked. It will open the website
     * specified by the URL represented by the variable urlAsString using implicit Intents.
     *
     * @param v Button that was clicked.
     */
    fun onClickOpenWebpageButton(v: View) {

        val url = "https://www.udacity.com/course/new-android-fundamentals--ud851"
        openWebPage(url)
    }

    /**
     * This method is called when the Open Location in Map button is clicked. It will open the
     * a map to the location represented by the variable addressString using implicit Intents.
     *
     * @param v Button that was clicked.
     */
    fun onClickOpenAddressButton(v: View) {
        val address = "Piracicaba, SP - Brasil"
        val uri = Uri.Builder()
            .scheme("geo")
            .path("0,0")
            .appendQueryParameter("q", address)
            .build()

        showMap(uri)
    }

    /**
     * This method is called when the Share Text Content button is clicked. It will simply share
     * the text contained within the String textThatYouWantToShare.
     *
     * @param v Button that was clicked.
     */
    fun onClickShareTextButton(v: View) {
        val textToShare = "Random text to share"

        shareText(textToShare)
        Toast.makeText(this, "Sharing $textToShare", Toast.LENGTH_LONG).show();
    }

    /**
     * This is where you will create and fire off your own implicit Intent. Yours will be very
     * similar to what I've done above. You can view a list of implicit Intents on the Common
     * Intents page from the developer documentation.
     *
     * @see <http://developer.android.com/guide/components/intents-common.html/>
     *
     * @param v Button that was clicked.
     */
    fun createYourOwn(v: View) {
        Toast.makeText(
            this,
            "TODO: Create Your Own Implicit Intent",
            Toast.LENGTH_SHORT
        )
            .show();
    }

    private fun openWebPage(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
        Toast.makeText(this, "Opening $url", Toast.LENGTH_SHORT).show();
    }

    private fun showMap(uri: Uri) {
        val intent = Intent(Intent.ACTION_VIEW, uri)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun shareText(text: String) {
        val mimeType = "text/plain"
        val title = "Chosse the app to share"
        val intent = ShareCompat.IntentBuilder(this)
            .setType(mimeType)
            .setChooserTitle(title)
            .setText(text)
            .intent

        startActivity(intent)
    }

}