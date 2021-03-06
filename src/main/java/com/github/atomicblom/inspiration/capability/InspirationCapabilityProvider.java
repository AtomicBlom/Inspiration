package com.github.atomicblom.inspiration.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.github.atomicblom.inspiration.model.Capability.INSPIRATION;

@ParametersAreNonnullByDefault
public class InspirationCapabilityProvider implements ICapabilityProvider, INBTSerializable<NBTBase>
{
    private final IInspirationCapability capability;

    public InspirationCapabilityProvider()
    {
        capability = new InspirationCapability();
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == INSPIRATION;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        if (capability == INSPIRATION)
        {
            return INSPIRATION.cast(this.capability);
        }
        return null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return InspirationCapabilityStorage.instance.writeNBT(INSPIRATION, capability, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        InspirationCapabilityStorage.instance.readNBT(INSPIRATION, capability, null, nbt);
    }
}
