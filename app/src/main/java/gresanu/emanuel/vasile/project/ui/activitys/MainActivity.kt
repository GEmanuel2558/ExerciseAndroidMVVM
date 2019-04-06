package gresanu.emanuel.vasile.project.ui.activitys

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import gresanu.emanuel.vasile.project.R

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
