package com.github.atomicblom.inspiration.util;

import net.minecraft.util.ResourceLocation;

@SuppressWarnings("UtilityClass")
public final class Reference
{
    public static final String MOD_ID = "inspiration";
    public static final String MOD_VERSION = "@mod_version@";
    public static final String IS_CI_BUILD = "@ci_build@";

    public static final ResourceLocation CapabilityResourceLocation = resource("inspiration_capability");

    private static ResourceLocation resource(String path)
    {
        return new ResourceLocation(MOD_ID, path);
    }

    public static final class Limits {

        public static final double Maximum = 10000;

        private Limits() {}
    }

    public static final class Registries {

        public static final ResourceLocation InspirationRegistryName = resource("inspiration_registry");
        public static final ResourceLocation ActionRegistryName = resource("action_registry");

        private Registries() {}
    }

    public static final class Commands {

        private Commands() {}

        public static final class GiveInspiration {

            public static final String NotEnoughParameters = resource("command.GiveInspiration.NotEnoughParameters").toString();
            public static final String NoSuchPlayer = resource("command.GiveInspiration.NoSuchPlayer").toString();
            public static final String Usage = resource("command.GiveInspiration.Usage").toString();
            public static final String NoSuchInspiration = resource("command.GiveInspiration.NoSuchInspiration").toString();
            public static final String CommandSucceeded = resource("command.GiveInspiration.CommandSucceeded").toString();

            private GiveInspiration() {}
        }
    }

    public static final class Errors {

        public static final String CapabilityNotAvailable = resource("error.CapabilityNotAvailable").toString();

        private Errors() {}
    }

    private Reference() {}
}
