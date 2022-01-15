package com.example.favoritetoys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView

class LifecycleActivity : AppCompatActivity() {


    /*
     * This TextView will contain a running log of every lifecycle callback method called from this
     * Activity. This TextView can be reset to its default state by clicking the Button labeled
     * "Reset Log"
     */
    private lateinit var mLifecycleDisplay: TextView

    /**
     * Called when the activity is first created. This is where you should do all of your normal
     * static set up: create views, bind data to lists, etc.
     *
     * Always followed by onStart().
     *
     * @param savedInstanceState The Activity's previously frozen state, if there was one.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        mLifecycleDisplay = findViewById(R.id.tv_lifecycle_events_display)

        savedInstanceState?.let {
            if (savedInstanceState.containsKey(LIFECYCLE_CALLBACKS_TEXT_KEY)) {
                mLifecycleDisplay.text = savedInstanceState.getString(LIFECYCLE_CALLBACKS_TEXT_KEY)
            }
        }

        logAndAppend(ON_CREATE)
    }

    override fun onStart() {
        super.onStart()
        logAndAppend(ON_START)
    }

    override fun onResume() {
        super.onResume()
        logAndAppend(ON_RESUME)
    }

    override fun onPause() {
        super.onPause()
        logAndAppend(ON_PAUSE)
    }

    override fun onStop() {
        super.onStop()
        logAndAppend(ON_STOP)
    }

    override fun onRestart() {
        super.onRestart()
        logAndAppend(ON_RESTART)
    }

    override fun onDestroy() {
        super.onDestroy()
        logAndAppend(ON_DESTROY)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        logAndAppend(ON_SAVE_INSTANCE_STATE)
        outState.putString(LIFECYCLE_CALLBACKS_TEXT_KEY, mLifecycleDisplay.text.toString())
    }

    /**
     * Logs to the console and appends the lifecycle method name to the TextView so that you can
     * view the series of method callbacks that are called both from the app and from within
     * Android Studio's Logcat.
     *
     * @param lifecycleEvent The name of the event to be logged.
     */
    fun logAndAppend(lifecycleEvent: String) {
        Log.d(TAG, "Lifecycle Event: " + lifecycleEvent);

        mLifecycleDisplay.append(lifecycleEvent + "\n");
    }

    /**
     * This method resets the contents of the TextView to its default text of "Lifecycle callbacks"
     *
     * @param view The View that was clicked. In this case, it is the Button from our layout.
     */
    fun resetLifecycleDisplay(view: String) {
        mLifecycleDisplay.setText("Lifecycle callbacks:\n");
    }

    companion object {
        /*
        * This tag will be used for logging. It is best practice to use the class's name using
        * getSimpleName as that will greatly help to identify the location from which your logs are
        * being posted.
        */
        val TAG = LifecycleActivity::class.simpleName

        private val LIFECYCLE_CALLBACKS_TEXT_KEY = "LIFECYCLE_CALLBACKS_TEXT_KEY"

        /* Constant values for the names of each respective lifecycle callback */
        private val ON_CREATE: String = "onCreate"
        private val ON_START: String = "onStart"
        private val ON_RESUME: String = "onResume"
        private val ON_PAUSE: String = "onPause"
        private val ON_STOP: String = "onStop"
        private val ON_RESTART: String = "onRestart"
        private val ON_DESTROY: String = "onDestroy"
        private val ON_SAVE_INSTANCE_STATE: String = "onSaveInstanceState"
    }
}