package com.github.atomicblom.inspiration.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class GatherTranslationsResponse implements IMessage
{
    private String translationKey;
    private String translation;

    public GatherTranslationsResponse() { }
    public GatherTranslationsResponse(String translationKey, String translation) {

        this.translationKey = translationKey;
        this.translation = translation;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        translationKey = ByteBufUtils.readUTF8String(buf);
        translation = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeUTF8String(buf, translationKey);
        ByteBufUtils.writeUTF8String(buf, translation);
    }

    public String getTranslationKey()
    {
        return translationKey;
    }

    public String getTranslation()
    {
        return translation;
    }
}
