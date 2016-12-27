package se.mattec.kotlinlist.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_entry.*
import se.mattec.kotlinlist.Cache
import se.mattec.kotlinlist.R
import se.mattec.kotlinlist.models.ListEntry

class EntryActivity : AppCompatActivity() {

    companion object {

        private val EXTRA_LIST_ENTRY_ID = "EXTRA_LIST_ENTRY_ID"

        val REQUEST_CODE = 0
        val RESPONSE_CODE_ITEM_CHANGED = 0
        val RESPONSE_CODE_ITEM_INSERTED = 1

        val RESPONSE_EXTRA_POSITION = "EXTRA_POSITION"

    }

    private var listEntryId: String? = null
    private var listEntry: ListEntry? = null

    fun newIntent(context: Context, listEntry: ListEntry?): Intent {
        val intent: Intent = Intent(context, EntryActivity::class.java)
        intent.putExtra(EXTRA_LIST_ENTRY_ID, listEntry?.id)
        return intent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)
        getExtras()
        setupListEntry()
    }

    private fun getExtras() {
        listEntryId = intent?.getStringExtra(EXTRA_LIST_ENTRY_ID)
    }

    private fun setupListEntry() {
        if (listEntryId != null) {
            Realm.getDefaultInstance().executeTransaction {
                listEntry = Realm.getDefaultInstance().where(ListEntry::class.java).equalTo("id", listEntryId).findFirst();
            }
        }
        entryTitle.setText(listEntry?.title)
        entryDescription.setText(listEntry?.description)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_entry, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.done -> {
                onDoneClick()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onDoneClick() {
        if (validateEntry()) {
            if (listEntry == null) { //Add new item
                val newEntry = ListEntry(entryTitle.text.toString(), entryDescription.text.toString())
                Cache.entries.add(newEntry)

                Realm.getDefaultInstance().executeTransaction {
                    Realm.getDefaultInstance().insert(newEntry)
                }

                val intent = Intent();
                intent.putExtra(RESPONSE_EXTRA_POSITION, Cache.entries.size - 1)
                setResult(RESPONSE_CODE_ITEM_INSERTED, intent)
            } else { // Edit existing item
                val oldIndex = Cache.entries.indexOf(listEntry as ListEntry);
                Cache.entries.remove(listEntry as ListEntry);
                Cache.entries.add(oldIndex, listEntry as ListEntry)

                Realm.getDefaultInstance().executeTransaction {
                    listEntry?.title = entryTitle.text.toString()
                    listEntry?.description = entryDescription.text.toString()
                }

                val intent = Intent();
                intent.putExtra(RESPONSE_EXTRA_POSITION, oldIndex)
                setResult(RESPONSE_CODE_ITEM_CHANGED, intent)
            }
            finish()
        }
    }

    private fun validateEntry(): Boolean {
        if (entryTitle.text.toString().isEmpty()) {
            Toast.makeText(this, "Title may not be empty.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}
