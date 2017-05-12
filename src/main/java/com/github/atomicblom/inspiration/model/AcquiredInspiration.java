package com.github.atomicblom.inspiration.model;

import com.github.atomicblom.inspiration.model.inspiration.Inspiration;
import com.github.atomicblom.inspiration.util.Reference;

public class AcquiredInspiration implements IAcquiredInspiration {
    private final Inspiration inspiration;
    private double amount;
    private String[] translation = null;

    public AcquiredInspiration(Inspiration inspiration) {
        this.inspiration = inspiration;
    }

    public void increment(double amount) {
        this.amount += amount;
        if (amount > Reference.Limits.Maximum) {
            this.amount = Reference.Limits.Maximum;
        }
    }

    public void decrement(double amount) {
        this.amount -= Math.max(amount, 0);
        if (amount < 0) {
            this.amount = 0;
        }
    }

    public Inspiration getInspiration() {
        return inspiration;
    }

    @Override
    public void setTranslation(String translation)
    {
        this.translation = translation.toLowerCase().split(" ");
    }

    @Override
    public boolean canBeUsedFor(String[] part) {
        if (translation == null) {
            //Don't match if we don't have a translation yet.
            return false;
        }

        int matchedCount = 0;
        for (final String aTranslation : translation)
        {
            for (final String s : part)
            {
                if (aTranslation.equals(s))
                {
                    matchedCount++;
                    break;
                }
            }
        }

        return matchedCount == translation.length;
    }

    public double getAmount() {
        return amount;
    }

    public String getTranslation() {
        return String.join(" ", translation);
    }

    @Override
    public int compareTo(IAcquiredInspiration other)
    {
        return Double.compare(amount, other.getAmount());
    }
}
