package se.mattec.kotlinlist.redux;

import org.immutables.value.Value;

import java.util.List;

import se.mattec.kotlinlist.models.ListEntry;

@Value.Immutable
public abstract class State
{

    public abstract List<ListEntry> entries();
    public abstract boolean prePopulated();

}
