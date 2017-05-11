package com.github.atomicblom.inspiration.events;

import com.github.atomicblom.inspiration.model.Capability;
import com.github.atomicblom.inspiration.capability.IInspirationCapability;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.SERVER)
public class ChatEvents
{
    @SubscribeEvent
    public static void onServerChatEvent(ServerChatEvent event) {

        final IInspirationCapability capability = event.getPlayer().getCapability(Capability.INSPIRATION, null);
        assert capability != null;
        capability.addChatMessage(event.getMessage());
        if (capability.isValidPoem()) {
            MinecraftForge.EVENT_BUS.post(new ValidPoemEvent(event.getPlayer(), capability));
        }
    }
}
