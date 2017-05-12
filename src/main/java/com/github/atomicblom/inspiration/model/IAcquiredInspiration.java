package com.github.atomicblom.inspiration.model;

import com.github.atomicblom.inspiration.model.inspiration.Inspiration;

/**
 * Created by codew on 11/05/2017.
 */
public interface IAcquiredInspiration extends Comparable<IAcquiredInspiration>{

    Inspiration getInspiration();

    void setTranslation(String translation);

    boolean canBeUsedFor(String[] parts);

    String getTranslation();

    double getAmount();
}
