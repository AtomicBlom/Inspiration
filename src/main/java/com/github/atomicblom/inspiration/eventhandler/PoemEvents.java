package com.github.atomicblom.inspiration.eventhandler;

import com.github.atomicblom.inspiration.events.ValidPoemEvent;
import com.github.atomicblom.inspiration.model.*;
import com.github.atomicblom.inspiration.InspirationMod;
import com.github.atomicblom.inspiration.capability.IInspirationCapability;
import com.github.atomicblom.inspiration.model.action.Action;
import com.github.atomicblom.inspiration.model.action.ActionContext;
import com.github.atomicblom.inspiration.model.action.ActionToPerform;
import com.github.atomicblom.inspiration.model.behaviour.BehaviourModifier;
import com.github.atomicblom.inspiration.model.behaviour.DefaultBehaviourModifier;
import com.github.atomicblom.inspiration.model.inspiration.Inspiration;
import com.github.atomicblom.inspiration.model.location.DefaultLocationModifier;
import com.github.atomicblom.inspiration.model.location.LocationModifier;
import com.github.atomicblom.inspiration.model.shape.DefaultShapeModifier;
import com.github.atomicblom.inspiration.model.shape.ShapeModifier;
import com.github.atomicblom.inspiration.model.size.DefaultSizeModifier;
import com.github.atomicblom.inspiration.model.size.SizeModifier;
import com.github.atomicblom.inspiration.util.Logger;
import com.google.common.collect.Lists;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Comparator;
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
        final Optional<List<IAcquiredInspiration>> possibleActions = getInspirations(lastLineParts, capability.getAcquiredInspirations());
        if (!possibleActions.isPresent()) {
            Logger.info("player attempted to compose a poem with no valid inspirations");
            return;
        }

        final LocationModifier locationModifier = getLocationModifier(lastLineParts);
        final SizeModifier sizeModifier = getSizeModifier(lastLineParts);
        final ShapeModifier shapeModifier = getShapeModifier(lastLineParts);
        final BehaviourModifier behaviourModifier = getBehaviourModifier(lastLineParts);

        final List<ActionToPerform> validInspirations = Lists.newArrayList();
        for (final IAcquiredInspiration acquiredInspiration : possibleActions.get()) {
            final Inspiration inspiration = acquiredInspiration.getInspiration();
            final Action action = getAction(inspiration, lastLineParts);

            final ActionContext context = new ActionContext(
                    locationModifier,
                    sizeModifier,
                    shapeModifier,
                    behaviourModifier);

            if (action.canBePerformedOnInspiration(inspiration)) {
                double minimumInspiration = action.getMinimumConsumedInspiration(context);
                if (acquiredInspiration.getAmount() >= minimumInspiration) {
                    validInspirations.add(new ActionToPerform(acquiredInspiration, action, context));
                }
            }
        }

        final Optional<ActionToPerform> firstValid = validInspirations
                .stream()
                .sorted(Comparator.comparingDouble((i) -> i.getAcquiredInspiration().getAmount()))
                .findFirst();

        if (firstValid.isPresent()) {
            final ActionToPerform actionToPerform = firstValid.get();
            actionToPerform.getAction().invoke(
                    target,
                    (WorldServer)target.world,
                    actionToPerform.getAcquiredInspiration(),
                    actionToPerform.getContext()
            );
        } else {
            Logger.info("Could not find a valid action to perform");
        }
    }

    private static Action getAction(Inspiration inspiration, String[] lastLineParts)
    {
        return inspiration.getDefaultAction();
    }

    private static BehaviourModifier getBehaviourModifier(String[] lastLineParts)
    {
        return new DefaultBehaviourModifier();
    }

    private static ShapeModifier getShapeModifier(String[] lastLineParts)
    {
        return new DefaultShapeModifier();
    }


    private static SizeModifier getSizeModifier(String[] lastLineParts)
    {
        return new DefaultSizeModifier();
    }

    private static LocationModifier getLocationModifier(String[] lastLineParts)
    {
        return new DefaultLocationModifier();
    }

    private static Optional<List<IAcquiredInspiration>> getInspirations(String[] parts, List<IAcquiredInspiration> playerInspirations) {
        final List<IAcquiredInspiration> usableInspirations = Lists.newArrayList();

        for (final IAcquiredInspiration acquiredInspiration : playerInspirations) {
            if (acquiredInspiration.canBeUsedFor(parts) && !usableInspirations.contains(acquiredInspiration)) {
                usableInspirations.add(acquiredInspiration);
                Logger.info("Found usable inspiration: %s (%f)",  acquiredInspiration.getTranslation(), acquiredInspiration.getAmount());
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
