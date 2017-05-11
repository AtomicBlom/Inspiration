package com.github.atomicblom.inspiration.model;

import net.minecraft.item.Item;

public class ItemInspiration extends Inspiration {

    private final Item item;

    public ItemInspiration(Item item) {
        this.item = item;
    }

    @Override
    public String getTranslationKey()
    {
        return item.getUnlocalizedName() + ".name";
    }
}
