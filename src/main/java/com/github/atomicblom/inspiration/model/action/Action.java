package com.github.atomicblom.inspiration.model.action;

import com.github.atomicblom.inspiration.model.IAcquiredInspiration;
import com.github.atomicblom.inspiration.model.inspiration.Inspiration;
import com.github.atomicblom.inspiration.model.shape.ShapeModifier;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

/**
 * Created by codew on 11/05/2017.
 */
public abstract class Action extends IForgeRegistryEntry.Impl<Action> {
    public abstract boolean canBePerformedOnInspiration(Inspiration inspiration);

    public abstract double getMinimumConsumedInspiration(ActionContext context);

    public void invoke(EntityPlayerMP target, WorldServer world, IAcquiredInspiration acquiredInspiration, ActionContext context)
    {
        final ShapeModifier shapeModifier = context.getShapeModifier();
        Iterable<BlockPos.MutableBlockPos> positions = shapeModifier.iterateBlocks(
                target,
                context.getLocationModifier(),
                context.getSizeModifier()
        );

        for (final BlockPos position : positions)
        {
            invoke(target, world, position, acquiredInspiration);
        }
    }

    protected abstract void invoke(EntityPlayerMP target, WorldServer world, BlockPos position, IAcquiredInspiration acquiredInspiration);
}
