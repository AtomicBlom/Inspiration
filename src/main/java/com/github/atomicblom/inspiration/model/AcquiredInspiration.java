package com.github.atomicblom.inspiration.model;

import com.github.atomicblom.inspiration.util.Reference;

public class AcquiredInspiration implements IAcquiredInspiration {
    private Inspiration inspiration;
    private double amount;

    public AcquiredInspiration(Inspiration inspiration) {

        this.inspiration = inspiration;
    }

    public void increment(double amount) {
        this.amount += amount;
        if (amount > Reference.Limits.Maximum) {
            amount = Reference.Limits.Maximum;
        }
    }

    public void decrement(double amount) {
        this.amount -= Math.max(amount, 0);
        if (amount <= 0) {
            this.amount = 0;
        }
    }

    public Inspiration getInspiration() {
        return inspiration;
    }

    public double getAmount() {
        return amount;
    }
}
