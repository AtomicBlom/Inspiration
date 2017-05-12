package com.github.atomicblom.inspiration.model.location;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

public class DefaultLocationModifier extends LocationModifier
{
    @Override
    public Vec3d getLocation(EntityPlayer player)
    {
        Vec3d lookVec = player.getLookVec();
        //FIXME: this is nor correct and does not place in front of the player.
        lookVec = new Vec3d(lookVec.xCoord, 0, lookVec.zCoord);
        return lookVec.normalize().scale(2);
    }
}
