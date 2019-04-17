package com.anwesh.uiprojects.linkedtwocenterlineview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.twocenterlineview.TwoCenterLineView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TwoCenterLineView.create(this)
    }
}
