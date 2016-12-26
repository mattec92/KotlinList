package se.mattec.kotlinlist.models

import java.io.Serializable
import java.util.*

class ListEntry(val title: String,
                val description: String? = null,
                val items: List<Item> = ArrayList()) : Serializable {

    inner class Item {

        val description: String? = null

    }

}
