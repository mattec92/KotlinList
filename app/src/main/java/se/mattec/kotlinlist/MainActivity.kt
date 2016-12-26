package se.mattec.kotlinlist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import se.mattec.kotlinlist.models.ListEntry
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupList()
        setupFab()
    }

    private fun setupList() {
        val listEntries: MutableList<ListEntry> = ArrayList()
        listEntries.add(ListEntry(title = "First", description = "First description"))
        listEntries.add(ListEntry(title = "Second"))
        listEntries.add(ListEntry(title = "Third", description = "Third description"))
        listEntries.add(ListEntry(title = "Fourth"))
        listEntries.add(ListEntry(title = "Fifth", description = "Fifth description"))
        listEntries.add(ListEntry(title = "Sixth"))
        listEntries.add(ListEntry(title = "Seventh", description = "Seventh description"))
        listEntries.add(ListEntry(title = "Eighth"))
        listEntries.add(ListEntry(title = "Ninth", description = "Ninth description"))
        listEntries.add(ListEntry(title = "Tenth"))

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ListAdapter(this, listEntries)
    }

    private fun setupFab() {
        addButton.setOnClickListener {

        }
    }

}
