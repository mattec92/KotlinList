package se.mattec.kotlinlist.redux.reducers;

import java.util.List;

import se.mattec.kotlinlist.models.ListEntry;
import se.mattec.kotlinlist.redux.ImmutableState;
import se.mattec.kotlinlist.redux.State;
import trikita.jedux.Action;
import trikita.jedux.Store;

public class RemoveTodoReducer
        implements Store.Reducer<Action, State>
{

    @Override
    public State reduce(Action action, State state)
    {
        ListEntry entryToRemove = (ListEntry) action.value;
        List<ListEntry> entries = state.entries();
        entries.remove(entryToRemove);
        return ImmutableState.builder()
                .from(state)
                .entries(entries)
                .build();
    }

}
