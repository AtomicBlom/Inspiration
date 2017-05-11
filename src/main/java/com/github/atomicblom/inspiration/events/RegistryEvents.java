package com.github.atomicblom.inspiration.events;

import com.github.atomicblom.inspiration.model.Action;
import com.github.atomicblom.inspiration.model.Inspiration;
import com.github.atomicblom.inspiration.util.Reference;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.RegistryBuilder;

@Mod.EventBusSubscriber
public class RegistryEvents {
    @SubscribeEvent
    public static void onCreateRegistries(RegistryEvent.NewRegistry event) {
        Reference.Registries.Inspirations = new RegistryBuilder<Inspiration>()
                .setType(Inspiration.class)
                .setIDRange(0, Short.MAX_VALUE)
                .setName(Reference.Registries.InspirationRegistryName)
                .create();

        Reference.Registries.Actions = new RegistryBuilder<Action>()
                .setType(Action.class)
                .setIDRange(0, Short.MAX_VALUE)
                .setName(Reference.Registries.ActionRegistryName)
                .create();
    }
}
