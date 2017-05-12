package com.github.atomicblom.inspiration.model.inspiration;

import com.github.atomicblom.inspiration.model.action.Action;
import com.github.atomicblom.inspiration.model.action.SpawnEntityAction;
import com.github.atomicblom.inspiration.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public class EntityInspiration extends Inspiration {
    private final Entity entity;
    private final NBTTagCompound entityNBT;

    public EntityInspiration(Entity entity) {

        this.entity = entity;
        this.entityNBT = entity.writeToNBT(new NBTTagCompound());
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

    @Override
    public Action getDefaultAction()
    {
        return new SpawnEntityAction();
    }

    public NBTTagCompound getEntityNBT()
    {
        return entityNBT;
    }
}
