package com.github.atomicblom.inspiration.events;

import com.github.atomicblom.inspiration.util.Logger;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.SERVER)
public class ChatEvents
{
    /*@SubscribeEvent
    public static void onChatEvent(ClientChatReceivedEvent event) {
        final ITextComponent textComponent = event.getMessage();
        Logger.info(textComponent.getFormattedText());
    }*/

    @SubscribeEvent
    public static void onServerChatEvent(ServerChatEvent event) {
        String message = event.getMessage();
        final EntityPlayerMP player = event.getPlayer();

        Logger.info(message);
    }
}
