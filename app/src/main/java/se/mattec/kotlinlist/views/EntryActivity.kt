package se.mattec.kotlinlist.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_entry.*
import se.mattec.kotlinlist.R
import se.mattec.kotlinlist.models.ListEntry

class EntryActivity : AppCompatActivity() {

    private val EXTRA_LIST_ENTRY = "EXTRA_LIST_ENTRY";

    private var listEntry: ListEntry? = null

    fun newIntent(context: Context, listEntry: ListEntry?): Intent {
        val intent: Intent = Intent(context, EntryActivity::class.java)
        intent.putExtra(EXTRA_LIST_ENTRY, listEntry)
        return intent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)
        getExtras()
        setupListEntry()
    }

    private fun getExtras() {
        listEntry = intent?.getSerializableExtra(EXTRA_LIST_ENTRY) as ListEntry?
    }

    private fun setupListEntry() {
        entryTitle.setText(listEntry?.title)
        entryDescription.setText(listEntry?.description)
    }

}
