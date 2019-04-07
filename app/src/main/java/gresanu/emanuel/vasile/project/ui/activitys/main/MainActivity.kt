package gresanu.emanuel.vasile.project.ui.activitys.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import gresanu.emanuel.vasile.project.R
import gresanu.emanuel.vasile.project.databinding.ActivityMainBinding
import gresanu.emanuel.vasile.project.extensions.getViewModel
import gresanu.emanuel.vasile.project.ui.activitys.main.viewmodeler.MainActivityViewModel

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var currentView: ActivityMainBinding

    private val viewModel by lazy {
        getViewModel<MainActivityViewModel>(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentView = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if(viewModel.isNewlyCreated && null!=savedInstanceState) {
            viewModel.onRestoreState(savedInstanceState)
        }
        viewModel.isNewlyCreated = false
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState.takeIf { null != it }?.let {
            viewModel.onSaveInstance(it)
        }
    }
}
