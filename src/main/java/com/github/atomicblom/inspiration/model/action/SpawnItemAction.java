package com.github.atomicblom.inspiration.model.action;

import com.github.atomicblom.inspiration.model.IAcquiredInspiration;
import com.github.atomicblom.inspiration.model.inspiration.Inspiration;
import com.github.atomicblom.inspiration.model.inspiration.ItemInspiration;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;

public class SpawnItemAction extends Action
{
    @Override
    public boolean canBePerformedOnInspiration(Inspiration inspiration)
    {
        return inspiration instanceof ItemInspiration;
    }

    @Override
    public double getMinimumConsumedInspiration(ActionContext context)
    {
        return 10;
    }

    @Override
    protected void invoke(EntityPlayerMP target, WorldServer world, BlockPos position, IAcquiredInspiration acquiredInspiration)
    {
        final ItemInspiration inspiration = (ItemInspiration)acquiredInspiration.getInspiration();
        final EntityItem entityIn = new EntityItem(world, position.getX(), position.getY(), position.getZ());
        entityIn.setEntityItemStack(inspiration.getItemStack());
        world.spawnEntity(entityIn);
    }
}
