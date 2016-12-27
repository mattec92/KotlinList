package se.mattec.kotlinlist.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable
import java.util.*

open class ListEntry(open var title: String? = null,
                     open var description: String? = null,
                     open var items: RealmList<ListEntryItem> = RealmList()) : Serializable, RealmObject() {

    @PrimaryKey
    open var id: String = UUID.randomUUID().toString()

    override fun equals(other: Any?): Boolean {
        return other is ListEntry && other.id == id
    }

    override fun hashCode(): Int {
        return id.hashCode();
    }

}
