package com.github.atomicblom.inspiration.model.shape;

import com.github.atomicblom.inspiration.model.location.LocationModifier;
import com.github.atomicblom.inspiration.model.size.SizeModifier;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class DefaultShapeModifier extends ShapeModifier
{
    @Override
    public Iterable<BlockPos.MutableBlockPos> iterateBlocks(EntityPlayerMP player, LocationModifier locationModifier, SizeModifier sizeModifier)
    {
        final Vec3d offset = locationModifier.getLocation(player);
        double originX = player.posX + offset.xCoord;
        double originY = player.posY + offset.yCoord;
        double originZ = player.posZ + offset.zCoord;

        BlockPos from = new BlockPos(
                originX - (sizeModifier.getWidth() / 2.0),
                originY - (sizeModifier.getHeight() / 2.0),
                originZ - (sizeModifier.getDepth() / 2.0)
        );

        BlockPos to = new BlockPos(
                originX + (sizeModifier.getWidth() / 2.0),
                originY + (sizeModifier.getHeight() / 2.0),
                originZ + (sizeModifier.getDepth() / 2.0)
        );

        return BlockPos.getAllInBoxMutable(from, to);
    }
}
