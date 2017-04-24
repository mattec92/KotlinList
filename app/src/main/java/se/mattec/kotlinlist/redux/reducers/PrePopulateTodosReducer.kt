package se.mattec.kotlinlist.redux.reducers

import io.realm.Realm
import se.mattec.kotlinlist.Cache
import se.mattec.kotlinlist.models.ListEntry
import se.mattec.kotlinlist.redux.ImmutableState
import se.mattec.kotlinlist.redux.State
import trikita.jedux.Action
import trikita.jedux.Store
import java.util.*

class PrePopulateTodosReducer : Store.Reducer<Action<*, *>, State> {

    override fun reduce(action: Action<*, *>, state: State): State {
        if (!state.prePopulated()) {
            val entries = ArrayList<ListEntry>()

            Realm.getDefaultInstance().executeTransaction {
                entries.addAll(Realm.getDefaultInstance().where(ListEntry::class.java).findAll())
            }

            if (entries.isEmpty()) {
                Cache.entries.add(ListEntry(title = "First", description = "First description"))
                Cache.entries.add(ListEntry(title = "Second"))
                Cache.entries.add(ListEntry(title = "Third", description = "Third description"))
                Cache.entries.add(ListEntry(title = "Fourth"))
                Cache.entries.add(ListEntry(title = "Fifth", description = "Fifth description"))
                Cache.entries.add(ListEntry(title = "Sixth"))
                Cache.entries.add(ListEntry(title = "Seventh", description = "Seventh description"))
                Cache.entries.add(ListEntry(title = "Eighth"))
                Cache.entries.add(ListEntry(title = "Ninth", description = "Ninth description"))
                Cache.entries.add(ListEntry(title = "Tenth"))

                Realm.getDefaultInstance().executeTransaction {
                    Realm.getDefaultInstance().insert(Cache.entries)
                }
            }

            return ImmutableState.builder()
                    .from(state)
                    .prePopulated(true)
                    .entries(entries)
                    .build()
        }

        return state
    }

}
