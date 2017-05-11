package com.github.atomicblom.inspiration.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class GatherTranslationRequest implements IMessage
{
    private String translationKey;

    public GatherTranslationRequest() { }
    public GatherTranslationRequest(String translationKey) {

        this.translationKey = translationKey;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        translationKey = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeUTF8String(buf, translationKey);
    }

    public String getTranslationKey()
    {
        return translationKey;
    }
}
