package com.github.atomicblom.inspiration.eventhandler;

import com.github.atomicblom.inspiration.InspirationMod;
import com.github.atomicblom.inspiration.events.ValidPoemEvent;
import com.github.atomicblom.inspiration.model.Capability;
import com.github.atomicblom.inspiration.capability.IInspirationCapability;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.SERVER)
public final class ChatEvents
{
    @SubscribeEvent
    public static void onServerChatEvent(ServerChatEvent event) {

        final IInspirationCapability capability = event.getPlayer().getCapability(Capability.INSPIRATION, null);
        assert capability != null;

        final String message = event.getMessage();
        if (InspirationMod.DEBUG) {
            switch (message) {
                case "retrigger":
                    break;
                case "prefab":
                    capability.addPoemLine(event.getUsername() + " did a little poem");
                    capability.addPoemLine("It was a little example for testing");
                    capability.addPoemLine("That invoked the magic cactus");
                    break;
                default:
                    capability.addPoemLine(message);
                    break;
            }
        } else {
            capability.addPoemLine(message);
        }
        if (capability.isValidPoem()) {
            MinecraftForge.EVENT_BUS.post(new ValidPoemEvent(event.getPlayer(), capability));
        }

    }
}