package se.mattec.kotlinlist.redux.reducers;

import java.util.List;

import se.mattec.kotlinlist.models.ListEntry;
import se.mattec.kotlinlist.redux.ImmutableState;
import se.mattec.kotlinlist.redux.State;
import trikita.jedux.Action;
import trikita.jedux.Store;

public class UpdateTodoReducer
        implements Store.Reducer<Action, State>
{

    @Override
    public State reduce(Action action, State state)
    {
        UpdateTodoAction updateTodoAction = (UpdateTodoAction) action.value;

        List<ListEntry> entries = state.entries();

        entries.remove(updateTodoAction.index);
        entries.add(updateTodoAction.index, updateTodoAction.entry);

        return ImmutableState.builder()
                .from(state)
                .entries(entries)
                .build();
    }

    public class UpdateTodoAction
    {

        int index;
        ListEntry entry;

    }

}
