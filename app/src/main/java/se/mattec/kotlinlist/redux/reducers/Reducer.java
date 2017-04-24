package se.mattec.kotlinlist.redux.reducers;

import se.mattec.kotlinlist.redux.State;
import se.mattec.kotlinlist.redux.actions.ActionType;
import trikita.jedux.Action;
import trikita.jedux.Store;

public class Reducer
        implements Store.Reducer<Action<ActionType, ?>, State>
{

    @Override
    public State reduce(Action<ActionType, ?> action, State state)
    {
        switch (action.type)
        {
            case PRE_POPULATE_TODOS:
                return new PrePopulateTodosReducer().reduce(action, state);
            case ADD_TODO:
                return new AddTodoReducer().reduce(action, state);
            case UPDATE_TODO:
                return new UpdateTodoReducer().reduce(action, state);
            case REMOVE_TODO:
                return new RemoveTodoReducer().reduce(action, state);
        }
        return state;
    }

}
