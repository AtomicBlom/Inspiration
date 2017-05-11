package com.github.atomicblom.inspiration.events;

import com.github.atomicblom.inspiration.model.IAcquiredInspiration;
import com.github.atomicblom.inspiration.model.Inspiration;
import com.github.atomicblom.inspiration.InspirationMod;
import com.github.atomicblom.inspiration.capability.IInspirationCapability;
import com.github.atomicblom.inspiration.util.Logger;
import com.google.common.collect.Lists;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;
import java.util.Optional;

@Mod.EventBusSubscriber
public final class PoemEvents
{
    @SubscribeEvent
    public static void onValidPoem(ValidPoemEvent event) {
        final IInspirationCapability capability = event.getCapability();
        final String[] firstLineParts = capability.getPoemParts(1);
        final Optional<EntityPlayerMP> player = getMentionedPlayer(firstLineParts, event.getWorld());
        if (!player.isPresent()) {
            Logger.info("Poem is not valid, no player specified");
            return;
        }
        final EntityPlayerMP target = player.get();
        Logger.info("Poem targets player " + target.getName());

        if (!InspirationMod.DEBUG && event.getPlayer().getName().equals(target.getName())) {
            Logger.info("player attempted to target themselves with a poem");
            return;
        }

        final String[] lastLineParts = capability.getPoemParts(3);
        final Optional<List<IAcquiredInspiration>> possibleActions = getInspirations(lastLineParts, capability.getInspirations());
        if (!possibleActions.isPresent()) {
            Logger.info("player attempted to compose a poem with no valid inspirations");
            return;
        }

        final List<Inspiration> validInspirations = Lists.newArrayList();
        for (final IAcquiredInspiration inspiration : possibleActions.get()) {
            
        }
    }
    
    private static Optional<List<IAcquiredInspiration>> getInspirations(String[] parts, List<IAcquiredInspiration> playerInspirations) {
        final List<IAcquiredInspiration> usableInspirations = Lists.newArrayList();

        for (final IAcquiredInspiration acquiredInspiration : playerInspirations) {
            if (acquiredInspiration.canBeUsedFor(parts) && !usableInspirations.contains(acquiredInspiration)) {
                usableInspirations.add(acquiredInspiration);
                Logger.info("Found usable inspiration: " + acquiredInspiration.getInspiration().getTranslationKey());
            }
        }

        if (usableInspirations.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(usableInspirations);
    }

    private static Optional<EntityPlayerMP> getMentionedPlayer(String[] parts, World world)
    {
        final MinecraftServer minecraftServer = world.getMinecraftServer();
        assert  minecraftServer != null;
        final PlayerList playerList = minecraftServer.getPlayerList();
        for (final String part : parts)
        {
            final EntityPlayerMP playerByUsername = playerList.getPlayerByUsername(part);
            if (playerByUsername != null)
            {
                return Optional.of(playerByUsername);
            }
        }
        return Optional.empty();
    }
}
