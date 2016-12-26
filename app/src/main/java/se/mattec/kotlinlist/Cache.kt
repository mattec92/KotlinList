package se.mattec.kotlinlist

import se.mattec.kotlinlist.models.ListEntry
import java.util.*

object Cache {

    val entries: MutableList<ListEntry> = ArrayList()
    var prePopulated: Boolean = false;

    fun prePopulateEntries() {
        if (!prePopulated) {
            prePopulated = true

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
        }
    }

}
