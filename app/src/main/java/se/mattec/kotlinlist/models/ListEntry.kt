package se.mattec.kotlinlist.models

import java.io.Serializable
import java.util.*

class ListEntry(var title: String,
                var description: String? = null,
                val items: List<Item> = ArrayList()) : Serializable {

    val id: String = UUID.randomUUID().toString()

    override fun equals(other: Any?): Boolean {
        return other is ListEntry && other.id == id
    }

    override fun hashCode(): Int {
        return id.hashCode();
    }

    inner class Item {

        val description: String? = null
        val isChecked: Boolean = false

    }

}
