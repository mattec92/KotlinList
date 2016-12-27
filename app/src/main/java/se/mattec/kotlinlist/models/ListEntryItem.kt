package se.mattec.kotlinlist.models

import io.realm.RealmObject
import java.io.Serializable

open class ListEntryItem(open var description: String? = null,
                         open var isChecked: Boolean = false) : Serializable, RealmObject() {

}