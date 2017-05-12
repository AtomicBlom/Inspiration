package com.github.atomicblom.inspiration.model.action;

import com.github.atomicblom.inspiration.model.inspiration.EntityInspiration;
import com.github.atomicblom.inspiration.model.IAcquiredInspiration;
import com.github.atomicblom.inspiration.model.inspiration.Inspiration;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;

public class SpawnEntityAction extends Action
{
    @Override
    public boolean canBePerformedOnInspiration(Inspiration inspiration)
    {
        return inspiration instanceof EntityInspiration;
    }

    @Override
    public double getMinimumConsumedInspiration(ActionContext context)
    {
        return 10;
    }

    @Override
    protected void invoke(EntityPlayerMP target, WorldServer world, BlockPos position, IAcquiredInspiration acquiredInspiration)
    {
        EntityInspiration entityInspiration = (EntityInspiration) acquiredInspiration.getInspiration();
        final NBTTagCompound entityNBT = entityInspiration.getEntityNBT();

        final Entity entityFromNBT = EntityList.createEntityFromNBT(entityNBT, world);

        entityFromNBT.setPosition(position.getX(), position.getY(), position.getZ());
        world.spawnEntity(entityFromNBT);
    }
}
