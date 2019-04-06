package gresanu.emanuel.vasile.project.ui.activitys

import android.databinding.DataBindingUtil
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import gresanu.emanuel.vasile.project.R
import gresanu.emanuel.vasile.project.databinding.ActivityMainBinding

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var currentView: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentView = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}
