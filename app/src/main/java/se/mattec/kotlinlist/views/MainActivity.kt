package se.mattec.kotlinlist.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import se.mattec.kotlinlist.Cache
import se.mattec.kotlinlist.R
import se.mattec.kotlinlist.adapters.ListAdapter

class MainActivity : AppCompatActivity() {

    var adapter: ListAdapter? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Cache.prePopulateEntries()
        setupList()
        setupFab()
    }

    private fun setupList() {
        adapter = ListAdapter(this, Cache.entries)
        adapter?.setHasStableIds(true)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setupFab() {
        addButton.setOnClickListener {
            startActivityForResult(EntryActivity().newIntent(this, null), EntryActivity.REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data != null) {
            val position = data.getIntExtra(EntryActivity.RESPONSE_EXTRA_POSITION, 0);
            when (resultCode) {
                EntryActivity.RESPONSE_CODE_ITEM_CHANGED -> recyclerView?.adapter?.notifyItemChanged(position)
                EntryActivity.RESPONSE_CODE_ITEM_INSERTED -> recyclerView?.adapter?.notifyItemInserted(position)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}
