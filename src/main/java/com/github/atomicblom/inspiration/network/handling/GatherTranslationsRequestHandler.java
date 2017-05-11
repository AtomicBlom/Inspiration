package com.github.atomicblom.inspiration.network.handling;

import com.github.atomicblom.inspiration.network.message.GatherTranslationRequest;
import com.github.atomicblom.inspiration.network.message.GatherTranslationsResponse;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class GatherTranslationsRequestHandler implements IMessageHandler<GatherTranslationRequest, GatherTranslationsResponse>
{
    @Override
    public GatherTranslationsResponse onMessage(GatherTranslationRequest message, MessageContext ctx)
    {
        return new GatherTranslationsResponse(
                message.getTranslationKey(),
                I18n.format(message.getTranslationKey())
        ) ;
    }
}
