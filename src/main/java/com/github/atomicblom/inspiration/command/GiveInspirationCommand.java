package com.github.atomicblom.inspiration.command;

import com.github.atomicblom.inspiration.capability.IInspirationCapability;
import com.github.atomicblom.inspiration.util.Reference;
import com.github.atomicblom.inspiration.model.Capability;
import com.github.atomicblom.inspiration.model.Inspiration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.stream.Collectors;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class GiveInspirationCommand extends CommandBase {
    @Override
    public String getName() {
        return "giveinspiration";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return Reference.Commands.GiveInspiration.Usage;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 2) {
            throw new CommandException(Reference.Commands.GiveInspiration.NotEnoughParameters);
        }

        IInspirationCapability capability = resolvePlayerInspirationCapability(server, args[0]);
        Inspiration inspiration = resolveRequestedInspiration(args[1]);
        double inspirationAmount = CommandBase.parseDouble(args[2], 0, Reference.Limits.Maximum);

        capability.addInspiration(inspiration, inspirationAmount);
        sender.sendMessage(new TextComponentTranslation(Reference.Commands.GiveInspiration.CommandSucceeded, inspiration.getRegistryName(), inspirationAmount));
    }

    private Inspiration resolveRequestedInspiration(String inspirationName) throws CommandException {

        Inspiration inspiration = Reference.Registries.Inspirations.getValue(new ResourceLocation(inspirationName));
        if (inspiration == null) {
            throw new CommandException(Reference.Commands.GiveInspiration.NoSuchInspiration);
        }

        return inspiration;
    }

    private IInspirationCapability resolvePlayerInspirationCapability(MinecraftServer server, String playerName) throws CommandException {
        EntityPlayerMP player = server.getPlayerList().getPlayerByUsername(playerName);
        if (player == null) {
            throw new CommandException(Reference.Commands.GiveInspiration.NoSuchPlayer);
        }
        if (!player.hasCapability(Capability.INSPIRATION, null)) {
            throw new CommandException(Reference.Errors.CapabilityNotAvailable);
        }
        IInspirationCapability capability = player.getCapability(Capability.INSPIRATION, null);
        assert capability != null;
        return capability;
    }

    @Override
    @Nonnull
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 0) {
            return java.util.Arrays.asList(server.getOnlinePlayerNames());
        }
        if (args.length == 1) {
            return java.util.Arrays.stream(server.getOnlinePlayerNames()).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
        }

        if (args.length == 2) {
            return Reference.Registries.Inspirations.getKeys()
                    .stream()
                    .map(ResourceLocation::toString)
                    .filter(s -> s.startsWith(args[1]))
                    .collect(Collectors.toList());
        }
        return super.getTabCompletions(server, sender, args, targetPos);
    }
}
