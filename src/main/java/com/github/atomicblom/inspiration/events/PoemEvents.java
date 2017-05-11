package com.github.atomicblom.inspiration.events;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Optional;

@Mod.EventBusSubscriber
public class PoemEvents
{
    @SubscribeEvent
    public static void onValidPoem(ValidPoemEvent event) {
        final String[] firstLineParts = event.getCapability().getPoemParts(1);
        String playerName = getMentionedPlayer(firstLineParts, event.getWorld());
    }

    private static Optional<EntityPlayerMP> getMentionedPlayer(String[] firstLineParts, World world)
    {
        final PlayerList playerList = world.getMinecraftServer().getPlayerList();
        for (final String part : firstLineParts)
        {
            final EntityPlayerMP playerByUsername = playerList.getPlayerByUsername(part);

            return Optional.of(playerByUsername);
        }

    }
}
