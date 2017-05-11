package com.github.atomicblom.inspiration.capability;

public class InspirationCapability implements IInspirationCapability
{
    private String[][] recentLines = new String[3][];


    @Override
    public void addChatMessage(String message)
    {
        recentLines[0] = recentLines[1];
        recentLines[1] = recentLines[2];
        recentLines[2] = message.split(" ");
    }

    public boolean isValidPoem() {
        return recentLines[0].length == 3 &&
                recentLines[1].length == 5 &&
                recentLines[2].length == 3;
    }

    @Override
    public String[] getPoemParts(int line)
    {
        return recentLines[line + 1];
    }
}
