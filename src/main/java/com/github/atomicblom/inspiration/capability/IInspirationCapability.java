package com.github.atomicblom.inspiration.capability;

import com.github.atomicblom.inspiration.model.IAcquiredInspiration;
import com.github.atomicblom.inspiration.model.Inspiration;
import com.google.common.collect.ImmutableList;
import java.util.function.Consumer;

public interface IInspirationCapability
{
    void addChatMessage(String message);

    boolean isValidPoem();

    String[] getPoemParts(int i);

    ImmutableList<IAcquiredInspiration> getInspirations();

    void addInspiration(Inspiration inspiration, double amount, Consumer<Inspiration> onCreateAction);
}
