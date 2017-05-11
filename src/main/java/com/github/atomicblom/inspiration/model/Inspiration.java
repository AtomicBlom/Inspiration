package com.github.atomicblom.inspiration.model;

import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

public abstract class Inspiration extends IForgeRegistryEntry.Impl<Inspiration> {
    public boolean canBeUsedFor(String part) { return false; }
    public boolean canBeUsedFor(String[] part) { return false; }
}
