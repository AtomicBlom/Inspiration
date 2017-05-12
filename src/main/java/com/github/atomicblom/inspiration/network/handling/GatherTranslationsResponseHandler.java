package com.github.atomicblom.inspiration.network.handling;

import com.github.atomicblom.inspiration.capability.IInspirationCapability;
import com.github.atomicblom.inspiration.model.Capability;
import com.github.atomicblom.inspiration.model.IAcquiredInspiration;
import com.github.atomicblom.inspiration.network.message.GatherTranslationsResponse;
import com.github.atomicblom.inspiration.util.Logger;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class GatherTranslationsResponseHandler implements IMessageHandler<GatherTranslationsResponse, IMessage>
{
    @SuppressWarnings("ReturnOfNull")
    @Override
    public IMessage onMessage(GatherTranslationsResponse message, MessageContext ctx)
    {
        final EntityPlayerMP player = ctx.getServerHandler().player;
        final IInspirationCapability capability = player.getCapability(Capability.INSPIRATION, null);
        assert capability != null;

        for (final IAcquiredInspiration inspiration : capability.getAcquiredInspirations())
        {
            if (inspiration.getInspiration().getTranslationKey().equals(message.getTranslationKey())) {
                Logger.info("Translation key %s maps to %s for %s", message.getTranslationKey(), message.getTranslation(), player.getName());
                inspiration.setTranslation(message.getTranslation());
            }

        }

        return null;
    }
}
