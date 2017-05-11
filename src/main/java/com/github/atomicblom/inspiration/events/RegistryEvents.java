package com.github.atomicblom.inspiration.events;

import com.github.atomicblom.inspiration.Services;
import com.github.atomicblom.inspiration.model.Action;
import com.github.atomicblom.inspiration.model.Inspiration;
import com.github.atomicblom.inspiration.util.Reference;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.RegistryBuilder;

@Mod.EventBusSubscriber
public final class RegistryEvents {
    @SubscribeEvent
    public static void onCreateRegistries(RegistryEvent.NewRegistry event) {
        Services.Inspirations = new RegistryBuilder<Inspiration>()
                .setType(Inspiration.class)
                .setIDRange(0, Short.MAX_VALUE)
                .setName(Reference.Registries.InspirationRegistryName)
                .create();

        Services.Actions = new RegistryBuilder<Action>()
                .setType(Action.class)
                .setIDRange(0, Short.MAX_VALUE)
                .setName(Reference.Registries.ActionRegistryName)
                .create();
    }
}
