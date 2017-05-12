package com.github.atomicblom.inspiration.eventhandler;

import com.github.atomicblom.inspiration.capability.InspirationCapabilityProvider;
import com.github.atomicblom.inspiration.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public final class CapabilityEvents
{
    @SubscribeEvent
    public static void onCapabilityAttaching(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer)
        {
            event.addCapability(Reference.Capability.CapabilityResourceLocation, new InspirationCapabilityProvider());
        }
    }
}
