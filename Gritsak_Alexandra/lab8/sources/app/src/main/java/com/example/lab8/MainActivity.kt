package com.example.lab8

import android.gesture.Gesture
import android.gesture.GestureLibraries
import android.gesture.GestureLibrary
import android.gesture.GestureOverlayView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), GestureOverlayView.OnGesturePerformedListener {
    private var qLibrary: GestureLibrary? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gestureSetup()
    }
    private fun gestureSetup() {
        qLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures)

        if(qLibrary?.load() == false)
            finish()
        gOverlay.addOnGesturePerformedListener(this)
    }
    override fun onGesturePerformed(overlay: GestureOverlayView?, gesture: Gesture?) {
        val predictions = qLibrary?.recognize(gesture)
        predictions?.let {
            if(it.size > 0 && it[0].score > 1.0) {
                val action = it[0].name

                Toast.makeText(this, action, Toast.LENGTH_SHORT).show()
            }
        }
    }
}