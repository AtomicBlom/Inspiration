package com.github.atomicblom.inspiration.util;

import net.minecraft.util.ResourceLocation;

@SuppressWarnings("UtilityClass")
public final class Reference
{
    public static final String MOD_ID = "inspiration";
    public static final String MOD_VERSION = "@mod_version@";
    public static final String IS_CI_BUILD = "@ci_build@";

    private static ResourceLocation resource(String path)
    {
        return new ResourceLocation(MOD_ID, path);
    }

    public static final class Limits {
        public static final double Maximum = 10000;

        private Limits() {}
    }

    public static final class Registries {
        public static final ResourceLocation ActionRegistryName = resource("action_registry");

        private Registries() {}
    }

    public static final class Capability {
        public static final ResourceLocation CapabilityResourceLocation = resource("inspiration_capability");

        public static final class NBT {
            public static final String PoemText = "poem_text";
            public static final String InspirationAmount = "inspiration_amount";
            public static final String InspirationTranslation = "inspiration_translation";
            public static final String PoemLineCount = "poem_line_count";
            public static final String InspirationList = "acquired_inspirations";
            public static final String InspirationType = "type";

            public static final String ItemInspirationType = "item";
            public static final String EntityInspirationType = "entity";


            private NBT() {}
        }

        private Capability() {}
    }

    public static final class Commands {

        private Commands() {}

        public static final class GiveInspiration {

            public static final String NotEnoughParameters = resource("command.GiveInspiration.NotEnoughParameters").toString();
            public static final String NoSuchPlayer = resource("command.GiveInspiration.NoSuchPlayer").toString();
            public static final String Usage = resource("command.GiveInspiration.Usage").toString();
            public static final String NoSuchInspiration = resource("command.GiveInspiration.NoSuchInspiration").toString();
            public static final String CommandSucceeded = resource("command.GiveInspiration.CommandSucceeded").toString();
            public static final String Command =  "giveinspiration";

            private GiveInspiration() {}
        }
    }

    public static final class Errors {

        public static final String CapabilityNotAvailable = resource("error.CapabilityNotAvailable").toString();

        private Errors() {}
    }

    private Reference() {}
}
