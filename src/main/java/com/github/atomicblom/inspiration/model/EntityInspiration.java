package com.github.atomicblom.inspiration.model;

import com.github.atomicblom.inspiration.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public class EntityInspiration extends Inspiration {
    private final Entity entity;

    public EntityInspiration(Entity entity) {
        this.entity = entity;
    }

    @Override
    public String getTranslationKey() {
        return entity.getName();
    }

    @Override
    public boolean isCompatibleWith(Inspiration inspiration) {
        return inspiration instanceof EntityInspiration &&
                entity.getEntityId() == ((EntityInspiration) inspiration).entity.getEntityId();
    }

    @Override
    public void writeToNBT(NBTTagCompound serializedInspiration) {
        serializedInspiration.setString(Reference.Capability.NBT.InspirationType, Reference.Capability.NBT.EntityInspirationType);
        entity.writeToNBT(serializedInspiration);
    }
}
