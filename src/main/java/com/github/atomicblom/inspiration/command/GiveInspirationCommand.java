package com.github.atomicblom.inspiration.command;

import com.github.atomicblom.inspiration.Services;
import com.github.atomicblom.inspiration.capability.IInspirationCapability;
import com.github.atomicblom.inspiration.model.inspiration.EntityInspiration;
import com.github.atomicblom.inspiration.model.inspiration.ItemInspiration;
import com.github.atomicblom.inspiration.network.message.GatherTranslationRequest;
import com.github.atomicblom.inspiration.util.Reference;
import com.github.atomicblom.inspiration.model.Capability;
import com.github.atomicblom.inspiration.model.inspiration.Inspiration;
import com.google.common.collect.Lists;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class GiveInspirationCommand extends CommandBase {
    @Override
    public String getName() {
        return Reference.Commands.GiveInspiration.Command;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return Reference.Commands.GiveInspiration.Usage;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 4) {
            throw new CommandException(Reference.Commands.GiveInspiration.NotEnoughParameters);
        }

        final EntityPlayerMP player = getPlayer(server, args[0]);
        final IInspirationCapability capability = resolveInspirationCapability(server, player);
        final Inspiration inspiration = resolveRequestedInspiration(args[1], args[2]);
        final double inspirationAmount = CommandBase.parseDouble(args[3], 0, Reference.Limits.Maximum);

        capability.addInspiration(inspiration, inspirationAmount, theInspiration -> {
            Services.NETWORK.sendTo(
                    new GatherTranslationRequest(inspiration.getTranslationKey()),
                    player
            );
        });
        sender.sendMessage(new TextComponentTranslation(Reference.Commands.GiveInspiration.CommandSucceeded, inspiration.getTranslationKey(), inspirationAmount));
    }

    private static Inspiration resolveRequestedInspiration(String type, String inspirationName) throws CommandException {

        ResourceLocation name = new ResourceLocation(inspirationName);
        if ("item".equals(type)) {
            Item item = Item.REGISTRY.getObject(name);
            if (item == null) {
                throw new CommandException(Reference.Commands.GiveInspiration.NoSuchInspiration);
            }

            return new ItemInspiration(new ItemStack(item));
        } else if ("entity".equals(type)) {
            Entity entity = EntityList.createEntityByIDFromName(name, null);
            if (entity == null) {
                throw new CommandException(Reference.Commands.GiveInspiration.NoSuchInspiration);
            }

            return new EntityInspiration(entity);
        }

        throw new CommandException(Reference.Commands.GiveInspiration.NoSuchInspiration);
    }

    private static IInspirationCapability resolveInspirationCapability(MinecraftServer server, EntityPlayerMP player) throws CommandException {
        if (!player.hasCapability(Capability.INSPIRATION, null)) {
            throw new CommandException(Reference.Errors.CapabilityNotAvailable);
        }
        final IInspirationCapability capability = player.getCapability(Capability.INSPIRATION, null);
        assert capability != null;
        return capability;
    }

    private static EntityPlayerMP getPlayer(MinecraftServer server, String playerName) throws CommandException
    {
        final EntityPlayerMP player = server.getPlayerList().getPlayerByUsername(playerName);
        if (player == null) {
            throw new CommandException(Reference.Commands.GiveInspiration.NoSuchPlayer);
        }
        return player;
    }

    @Override
    @Nonnull
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 0) {
            return Arrays.asList(server.getOnlinePlayerNames());
        }
        if (args.length == 1) {
            return Arrays.stream(server.getOnlinePlayerNames()).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
        }

        if (args.length == 2) {
            return Lists.newArrayList("item", "entity");
        }

        if (args.length == 3) {
            String type = args[1].toLowerCase();
            if ("item".equals(type)) {
                return Item.REGISTRY.getKeys()
                        .stream()
                        .map(ResourceLocation::toString)
                        .filter(s -> s.startsWith(args[2]))
                        .collect(Collectors.toList());
            } else if ("entity".equals(type)) {
                return EntityList.getEntityNameList()
                        .stream()
                        .map(ResourceLocation::toString)
                        .filter(s -> s.startsWith(args[2]))
                        .collect(Collectors.toList());
            }
            return Lists.newArrayList();
        }
        return super.getTabCompletions(server, sender, args, targetPos);
    }
}
