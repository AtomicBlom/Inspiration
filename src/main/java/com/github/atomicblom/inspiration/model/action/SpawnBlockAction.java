package com.github.atomicblom.inspiration.model.action;

import com.github.atomicblom.inspiration.model.IAcquiredInspiration;
import com.github.atomicblom.inspiration.model.inspiration.Inspiration;
import com.github.atomicblom.inspiration.model.inspiration.ItemInspiration;
import com.mojang.authlib.GameProfile;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import java.util.UUID;

public class SpawnBlockAction extends Action
{
    @Override
    public boolean canBePerformedOnInspiration(Inspiration inspiration)
    {
        return inspiration instanceof ItemInspiration &&
                ((ItemInspiration)inspiration).getItemStack().getItem() instanceof ItemBlock;
    }

    @Override
    public double getMinimumConsumedInspiration(ActionContext context)
    {
        return 1;
    }

    @Override
    protected void invoke(EntityPlayerMP target, WorldServer world, BlockPos position, IAcquiredInspiration acquiredInspiration)
    {
        final ItemInspiration inspiration = (ItemInspiration)acquiredInspiration.getInspiration();
        final ItemStack itemStack = inspiration.getItemStack();
        final ItemBlock item = (ItemBlock)itemStack.getItem();
        final Block block = item.getBlock();
        final FakePlayer player = new FakePlayer(world, new GameProfile(UUID.randomUUID(), "Inspiration"));
        player.setHeldItem(EnumHand.MAIN_HAND, itemStack.copy());
        final IBlockState stateForPlacement = block.getStateForPlacement(
                world,
                position,
                EnumFacing.NORTH,
                0, 0, 0,
                itemStack.getMetadata(),
                player,
                EnumHand.MAIN_HAND);
        world.setBlockState(position, stateForPlacement, 0x3);
    }
}
