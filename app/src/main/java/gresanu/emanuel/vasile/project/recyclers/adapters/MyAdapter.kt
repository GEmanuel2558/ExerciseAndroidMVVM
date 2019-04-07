package gresanu.emanuel.vasile.project.recyclers.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import gresanu.emanuel.vasile.project.R
import gresanu.emanuel.vasile.project.databinding.RecyclerItemBinding
import gresanu.emanuel.vasile.project.recyclers.models.Message
import gresanu.emanuel.vasile.project.ui.activitys.main.viewmodeler.RecyclerItemViewModel

class MyAdapter(val listOfItems: MutableList<Message>): RecyclerView.Adapter<MyAdapter.BindingHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): BindingHolder {
        val currentView = DataBindingUtil.inflate<RecyclerItemBinding>(LayoutInflater.from(parent.context), R.layout.recycler_item, parent, false)
        return BindingHolder(currentView.root)
    }

    override fun onBindViewHolder(container: BindingHolder, position: Int) {
        val currentItem = listOfItems[position]
        container.bindHolder?.apply {
            viewModel = RecyclerItemViewModel()
            viewModel!!.title.value = currentItem.title
            viewModel!!.message.value = currentItem.message
        }
    }

    override fun getItemCount() = listOfItems.size

    inner class BindingHolder(view: View): RecyclerView.ViewHolder(view) {
        val bindHolder: RecyclerItemBinding? = DataBindingUtil.bind(view)
    }
}