package com.github.atomicblom.inspiration.events;

import com.github.atomicblom.inspiration.model.Inspiration;
import com.github.atomicblom.inspiration.model.ItemInspiration;
import com.github.atomicblom.inspiration.util.Reference;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

@Mod.EventBusSubscriber()
public class RegisterInspirationEvent {
    @SubscribeEvent
    public static void onRegisterInspirations(RegistryEvent.Register<Inspiration> event) {
        IForgeRegistry<Inspiration> registry = event.getRegistry();

        for (Item item : Item.REGISTRY) {

            ResourceLocation registryName = item.getRegistryName();
            if ("minecraft".equals(registryName.getResourceDomain())) {
                registry.register(new ItemInspiration(item).setRegistryName(new ResourceLocation(Reference.MODID, registryName.getResourcePath())));
            }
        }
    }
}
