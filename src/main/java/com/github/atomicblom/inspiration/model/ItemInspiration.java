package com.github.atomicblom.inspiration.model;

import com.github.atomicblom.inspiration.util.Reference;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemInspiration extends Inspiration {

    private final Item item;

    public ItemInspiration(Item item) {
        this.item = item;
    }

    @Override
    public boolean canBeUsedFor(String part) {
        ResourceLocation registryName = item.getRegistryName();
        assert registryName != null;

        if ("minecraft".equals(registryName.getResourceDomain())) {
            registryName = new ResourceLocation(Reference.MODID, registryName.getResourcePath());
        }
        return registryName.equals(new ResourceLocation(part));
    }
}
