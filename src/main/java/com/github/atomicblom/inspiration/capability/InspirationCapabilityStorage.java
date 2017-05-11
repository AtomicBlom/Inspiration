package com.github.atomicblom.inspiration.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class InspirationCapabilityStorage implements IStorage<IInspirationCapability>
{
    public static final IStorage<IInspirationCapability> instance = new InspirationCapabilityStorage();

    @Override
    public NBTBase writeNBT(Capability<IInspirationCapability> capability, IInspirationCapability instance, EnumFacing side)
    {
        final NBTTagCompound compound = new NBTTagCompound();
        return compound;
    }

    @Override
    public void readNBT(Capability<IInspirationCapability> capability, IInspirationCapability instance, EnumFacing side, NBTBase nbt)
    {
        final NBTTagCompound compound = (NBTTagCompound) nbt;
    }
}
