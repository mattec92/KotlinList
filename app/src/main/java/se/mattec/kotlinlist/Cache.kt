package se.mattec.kotlinlist

import io.realm.Realm
import se.mattec.kotlinlist.models.ListEntry
import java.util.*

object Cache {

    val entries: MutableList<ListEntry> = ArrayList()
    var prePopulated: Boolean = false;

    fun prePopulateEntries() {
        if (!prePopulated) {
            prePopulated = true

            Realm.getDefaultInstance().executeTransaction {
                entries.addAll(Realm.getDefaultInstance().where(ListEntry::class.java).findAll());
            }

            if (entries.isEmpty()) {
                entries.add(ListEntry(title = "First", description = "First description"))
                entries.add(ListEntry(title = "Second"))
                entries.add(ListEntry(title = "Third", description = "Third description"))
                entries.add(ListEntry(title = "Fourth"))
                entries.add(ListEntry(title = "Fifth", description = "Fifth description"))
                entries.add(ListEntry(title = "Sixth"))
                entries.add(ListEntry(title = "Seventh", description = "Seventh description"))
                entries.add(ListEntry(title = "Eighth"))
                entries.add(ListEntry(title = "Ninth", description = "Ninth description"))
                entries.add(ListEntry(title = "Tenth"))

                Realm.getDefaultInstance().executeTransaction {
                    Realm.getDefaultInstance().insert(entries)
                }
            }
        }
    }

}
