package se.mattec.kotlinlist.adapters

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row_list.view.*
import se.mattec.kotlinlist.R
import se.mattec.kotlinlist.models.ListEntry
import se.mattec.kotlinlist.views.EntryActivity

class ListAdapter(private val activity: Activity,
                  private val listEntries: List<ListEntry>) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        when (viewType) {
            VIEW_TYPE_ITEM -> return ViewHolder(LayoutInflater.from(activity).inflate(R.layout.row_list, parent, false))
            else -> return ViewHolder(View(activity))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.init(listEntries[position], position)
    }

    override fun getItemCount(): Int {
        return listEntries.size
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE_ITEM
    }

    override fun getItemId(position: Int): Long {
        return listEntries.get(position).hashCode().toLong()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun init(listEntry: ListEntry, position: Int) {
            itemView.entryTitle.text = listEntry.title

            if (!listEntry.description.isNullOrEmpty()) {
                itemView.entryDescription.text = listEntry.description
                itemView.entryDescription.visibility = View.VISIBLE
            } else {
                itemView.entryDescription.visibility = View.GONE
            }

            itemView.setOnClickListener {
                activity.startActivityForResult(EntryActivity().newIntent(activity, listEntry), EntryActivity.REQUEST_CODE)
            }
        }

    }

}
