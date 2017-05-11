package com.github.atomicblom.inspiration.model;

import com.github.atomicblom.inspiration.capability.IInspirationCapability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public final class Capability {
    @CapabilityInject(IInspirationCapability.class)
    public static final net.minecraftforge.common.capabilities.Capability<IInspirationCapability> INSPIRATION;

    static {
        INSPIRATION = null;
    }
}
