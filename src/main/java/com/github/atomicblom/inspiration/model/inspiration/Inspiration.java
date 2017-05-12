package com.github.atomicblom.inspiration.model.inspiration;

import com.github.atomicblom.inspiration.model.action.Action;
import net.minecraft.nbt.NBTTagCompound;

public abstract class Inspiration {
    public abstract String getTranslationKey();

    public abstract boolean isCompatibleWith(Inspiration inspiration);

    public abstract void writeToNBT(NBTTagCompound serializedInspiration);

    public abstract Action getDefaultAction();
}
