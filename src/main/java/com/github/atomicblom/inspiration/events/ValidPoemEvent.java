package com.github.atomicblom.inspiration.events;

import com.github.atomicblom.inspiration.capability.IInspirationCapability;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event;

public class ValidPoemEvent extends Event
{
    private final EntityPlayer player;
    private final IInspirationCapability capability;

    public ValidPoemEvent(EntityPlayer player, IInspirationCapability capability) {

        this.player = player;
        this.capability = capability;
    }

    public IInspirationCapability getCapability()
    {
        return capability;
    }

    public EntityPlayer getPlayer()
    {
        return player;
    }

    public World getWorld()
    {
        return player.world;
    }
}
