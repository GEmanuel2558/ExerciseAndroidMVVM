package gresanu.emanuel.vasile.project.extensions

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.BindingAdapter
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    parentActivity.takeIf { null != it }?.let { text.observe(it, Observer { value -> view.text = value ?: "" }) }
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?: View.VISIBLE})
    }
}

@BindingAdapter("mutableReverseVisibility")
fun setReverseMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value ->
            view.visibility = if(View.VISIBLE == value) {
                View.GONE
            } else {
                View.VISIBLE
            }
        })
    }
}