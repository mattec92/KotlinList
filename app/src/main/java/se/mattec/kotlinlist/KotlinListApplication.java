package se.mattec.kotlinlist;

import android.app.Application;

import io.realm.Realm;
import se.mattec.kotlinlist.redux.ImmutableState;
import se.mattec.kotlinlist.redux.State;
import se.mattec.kotlinlist.redux.actions.ActionType;
import se.mattec.kotlinlist.redux.reducers.Reducer;
import trikita.jedux.Action;
import trikita.jedux.Store;

public class KotlinListApplication
        extends Application
{

    private static KotlinListApplication instance;
    private Store<Action<ActionType, ?>, State> store;

    @Override
    public void onCreate()
    {
        super.onCreate();
        Realm.init(this);
        instance = this;
        instance.store = new Store<>(new Reducer(), ImmutableState.builder().build());
    }

    public static State state()
    {
        return instance.store.getState();
    }

    public static State dispatch(Action action)
    {
        return instance.store.dispatch(action);
    }

}
