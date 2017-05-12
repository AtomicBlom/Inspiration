package com.github.atomicblom.inspiration.model.shape;

import com.github.atomicblom.inspiration.model.location.LocationModifier;
import com.github.atomicblom.inspiration.model.size.SizeModifier;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;

public abstract class ShapeModifier
{
    public abstract Iterable<BlockPos.MutableBlockPos> iterateBlocks(EntityPlayerMP target, LocationModifier locationModifier, SizeModifier sizeModifier);
}
