package com.github.atomicblom.inspiration.model;

import net.minecraft.nbt.NBTTagCompound;

public abstract class Inspiration {
    public abstract String getTranslationKey();

    public abstract boolean isCompatibleWith(Inspiration inspiration);

    public abstract void writeToNBT(NBTTagCompound serializedInspiration);
}
