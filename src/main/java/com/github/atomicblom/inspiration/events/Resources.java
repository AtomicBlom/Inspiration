package com.github.atomicblom.inspiration.events;

import net.minecraft.util.ResourceLocation;

public class Resources
{
    public static final String MODID = "@mod_id@";
    public static final String VERSION = "@mod_version@";
    public static final String IS_CI_BUILD = "@ci_build@";

    public static final ResourceLocation CapabilityResourceLocation = resource("inspirationCapability");

    private static ResourceLocation resource(String path)
    {
        return new ResourceLocation(MODID, path);
    }
}
