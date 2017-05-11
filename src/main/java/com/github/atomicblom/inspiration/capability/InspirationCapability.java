package com.github.atomicblom.inspiration.capability;

import com.github.atomicblom.inspiration.model.AcquiredInspiration;
import com.github.atomicblom.inspiration.model.IAcquiredInspiration;
import com.github.atomicblom.inspiration.model.Inspiration;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.function.Consumer;

public class InspirationCapability implements IInspirationCapability
{
    private final String[][] recentLines = new String[3][];

    private final Map<Inspiration, AcquiredInspiration> aquiredInspirations = Maps.newHashMap();

    @Override
    public void addChatMessage(String message)
    {
        recentLines[0] = recentLines[1];
        recentLines[1] = recentLines[2];
        recentLines[2] = message.toLowerCase().split(" ");
    }

    @SuppressWarnings("OverlyComplexBooleanExpression")
    public boolean isValidPoem() {
        return recentLines[0] != null && recentLines[0].length == 5 &&
                recentLines[1] != null && recentLines[1].length == 7 &&
                recentLines[2] != null && recentLines[2].length == 5;
    }

    @Override
    public String[] getPoemParts(int line)
    {
        return recentLines[line - 1];
    }

    @Override
    public ImmutableList<IAcquiredInspiration> getInspirations() {
        return ImmutableList.copyOf(aquiredInspirations.values());
    }

    @Override
    public void addInspiration(Inspiration inspiration, double amount, Consumer<Inspiration> onCreateAction) {
        final AcquiredInspiration acquiredInspiration = aquiredInspirations.computeIfAbsent(
                inspiration,
                theInspiration ->
                {
                    onCreateAction.accept(theInspiration);
                    return new AcquiredInspiration(theInspiration);
                }
        );
        acquiredInspiration.increment(amount);
    }
}
