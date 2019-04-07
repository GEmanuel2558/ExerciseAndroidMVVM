package gresanu.emanuel.vasile.project.extensions

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.BindingAdapter
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    parentActivity.takeIf { null != it }?.let { text.observe(it, Observer { value -> view.text = value ?: "" }) }
}