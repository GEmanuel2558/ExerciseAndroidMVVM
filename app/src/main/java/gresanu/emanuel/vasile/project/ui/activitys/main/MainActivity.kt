package gresanu.emanuel.vasile.project.ui.activitys.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import dagger.android.DaggerApplication
import dagger.android.support.DaggerAppCompatActivity
import gresanu.emanuel.vasile.project.R
import gresanu.emanuel.vasile.project.databinding.ActivityMainBinding
import gresanu.emanuel.vasile.project.di.annotations.PerActivity
import gresanu.emanuel.vasile.project.extensions.withViewModel
import gresanu.emanuel.vasile.project.ui.activitys.main.viewmodeler.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    @PerActivity
    lateinit var recyclerViewLayoutManager: RecyclerView.LayoutManager

    private lateinit var currentView: ActivityMainBinding

    private val viewModel by lazy {
        withViewModel ({ MainActivityViewModel(application = application as DaggerApplication) }) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentView = DataBindingUtil.setContentView(this, R.layout.activity_main)
        currentView.viewModel = viewModel
        if(viewModel.isNewlyCreated && null!=savedInstanceState) {
            viewModel.onRestoreState(savedInstanceState)
        }
        viewModel.isNewlyCreated = false

        if(::recyclerViewLayoutManager.isInitialized) {
            recyclerView.layoutManager = recyclerViewLayoutManager
        }

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState.takeIf { null != it }?.let {
            viewModel.onSaveInstance(it)
        }
    }

}
