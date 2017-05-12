package com.github.atomicblom.inspiration.model.inspiration;

import com.github.atomicblom.inspiration.model.action.Action;
import com.github.atomicblom.inspiration.model.action.SpawnBlockAction;
import com.github.atomicblom.inspiration.model.action.SpawnItemAction;
import com.github.atomicblom.inspiration.util.Reference;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemInspiration extends Inspiration
{

    private final ItemStack itemStack;

    public ItemInspiration(ItemStack item) {
        this.itemStack = item;
    }

    @Override
    public String getTranslationKey()
    {
        return itemStack.getUnlocalizedName() + ".name";
    }

    @Override
    public boolean isCompatibleWith(Inspiration inspiration) {
        return inspiration instanceof ItemInspiration &&
                itemStack.isItemEqual(((ItemInspiration) inspiration).itemStack);
    }

    @Override
    public void writeToNBT(NBTTagCompound serializedInspiration) {
        serializedInspiration.setString(Reference.Capability.NBT.InspirationType, Reference.Capability.NBT.ItemInspirationType);
        itemStack.writeToNBT(serializedInspiration);
    }

    @Override
    public Action getDefaultAction()
    {
        return itemStack.getItem() instanceof ItemBlock ?
                new SpawnBlockAction() :
                new SpawnItemAction();
    }

    public ItemStack getItemStack()
    {
        return itemStack;
    }
}
