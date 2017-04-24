package se.mattec.kotlinlist.redux.reducers

import se.mattec.kotlinlist.models.ListEntry
import se.mattec.kotlinlist.redux.ImmutableState
import se.mattec.kotlinlist.redux.State
import trikita.jedux.Action
import trikita.jedux.Store

class AddTodoReducer : Store.Reducer<Action<*, *>, State> {

    override fun reduce(action: Action<*, *>, state: State): State {
        val entryToAdd = action.value as ListEntry
        return ImmutableState.builder()
                .from(state)
                .addEntries(entryToAdd)
                .build()
    }

}

