package com.github.atomicblom.inspiration.capability;

import com.github.atomicblom.inspiration.model.IAcquiredInspiration;
import com.github.atomicblom.inspiration.model.Inspiration;
import com.google.common.collect.ImmutableList;

public interface IInspirationCapability
{
    void addChatMessage(String message);

    boolean isValidPoem();

    String[] getPoemParts(int i);

    ImmutableList<IAcquiredInspiration> getInspirations();

    void addInspiration(Inspiration inspiration, double amount);
}
