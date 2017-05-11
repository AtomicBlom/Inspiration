package com.github.atomicblom.inspiration.util;

import com.github.atomicblom.inspiration.model.Action;
import com.github.atomicblom.inspiration.model.Inspiration;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

public final class Reference
{
    public static final String MODID = "@mod_id@";
    public static final String VERSION = "@mod_version@";
    public static final String IS_CI_BUILD = "@ci_build@";

    public static final ResourceLocation CapabilityResourceLocation = resource("inspiration_capability");

    private static ResourceLocation resource(String path)
    {
        return new ResourceLocation(MODID, path);
    }

    public static final class Limits {

        public static final double Maximum = 10000;

        private Limits() {}
    }

    public static final class Registries {

        public static ResourceLocation InspirationRegistryName = resource("inspiration_registry");
        public static ResourceLocation ActionRegistryName = resource("action_registry");

        //Created during RegistryEvents.onCreateRegistries()
        public static IForgeRegistry<Inspiration> Inspirations;
        public static IForgeRegistry<Action> Actions;

        private Registries() {}
    }

    public static final class Commands {

        private Commands() {}

        public static final class GiveInspiration {

            public static final String NotEnoughParameters = resource("command.GiveInspiration.NotEnoughParameters").toString();
            public static final String NoSuchPlayer = resource("command.GiveInspiration.NoSuchPlayer").toString();
            public static final String Usage = resource("command.GiveInspiration.Usage").toString();

            private GiveInspiration() {}
        }
    }

    public static final class Errors {

        public static String CapabilityNotAvailable = resource("error.CapabilityNotAvailable").toString();

        private Errors() {}
    }

    private Reference() {}
}
