package com.github.atomicblom.inspiration.model;

/**
 * Created by codew on 11/05/2017.
 */
public interface IAcquiredInspiration {

    Inspiration getInspiration();

    void setTranslation(String translation);

    boolean canBeUsedFor(String[] parts);
}
