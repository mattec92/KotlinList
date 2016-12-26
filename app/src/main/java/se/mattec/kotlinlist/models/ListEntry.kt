package se.mattec.kotlinlist.models

import java.util.*

class ListEntry(val title: String,
                val description: String? = null,
                val items: List<Item> = ArrayList()) {

    inner class Item {

        val description: String? = null

    }

}
