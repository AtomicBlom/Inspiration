package com.github.atomicblom.inspiration.model.location;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

public abstract class LocationModifier
{
    public abstract Vec3d getLocation(EntityPlayer player);
}
