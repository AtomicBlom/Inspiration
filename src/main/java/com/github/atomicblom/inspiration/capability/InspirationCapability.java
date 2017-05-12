package com.github.atomicblom.inspiration.capability;

import com.github.atomicblom.inspiration.model.AcquiredInspiration;
import com.github.atomicblom.inspiration.model.IAcquiredInspiration;
import com.github.atomicblom.inspiration.model.inspiration.Inspiration;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.Consumer;

public class InspirationCapability implements IInspirationCapability
{
    private final String[][] recentLines = new String[3][];

    private final List<AcquiredInspiration> acquiredInspirations = Lists.newArrayList();

    @Override
    public void addPoemLine(String message)
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
    public ImmutableList<IAcquiredInspiration> getAcquiredInspirations() {
        return ImmutableList.copyOf(acquiredInspirations);
    }

    //Used specifically for serialization.
    public List<AcquiredInspiration> getAcquiredInspirationsList() {
        return acquiredInspirations;
    }

    @Override
    public void addInspiration(Inspiration inspiration, double amount, Consumer<Inspiration> onCreateAction) {

        AcquiredInspiration foundInspiration = null;
        for (AcquiredInspiration acquiredInspiration : acquiredInspirations) {
            if (acquiredInspiration.getInspiration().isCompatibleWith(inspiration)) {
                foundInspiration = acquiredInspiration;
                break;
            }
        }

        if (foundInspiration == null) {
            foundInspiration = new AcquiredInspiration(inspiration);
            acquiredInspirations.add(foundInspiration);
            onCreateAction.accept(inspiration);
        }

        foundInspiration.increment(amount);
    }

    String[] getPoemLines() {
        String[] poemLines = new String[recentLines.length];
        for (int i = 0; i < recentLines.length; i++) {
            if (recentLines[i] != null) {
                poemLines[i] = String.join(" ", recentLines[i]);
            }
        }
        return poemLines;
    }
}
