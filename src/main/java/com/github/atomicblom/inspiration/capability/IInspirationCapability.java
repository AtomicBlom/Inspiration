package com.github.atomicblom.inspiration.capability;

public interface IInspirationCapability
{
    void addChatMessage(String message);

    boolean isValidPoem();

    String[] getPoemParts(int i);
}
