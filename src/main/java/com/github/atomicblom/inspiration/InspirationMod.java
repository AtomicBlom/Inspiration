package com.github.atomicblom.inspiration;

import com.github.atomicblom.inspiration.capability.IInspirationCapability;
import com.github.atomicblom.inspiration.capability.InspirationCapability;
import com.github.atomicblom.inspiration.capability.InspirationCapabilityStorage;
import com.github.atomicblom.inspiration.command.GiveInspirationCommand;
import com.github.atomicblom.inspiration.util.Logger;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import static com.github.atomicblom.inspiration.util.Reference.*;

@Mod(modid = MODID, version = VERSION)
public class InspirationMod
{
    public static boolean DEBUG = true;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        if (Boolean.parseBoolean(IS_CI_BUILD)) {
            DEBUG = false;
        }
        if (DEBUG) {
            Logger.info("Inspiration Debugging is enabled.");
        }

        //Capabilities
        CapabilityManager.INSTANCE.register(IInspirationCapability.class, InspirationCapabilityStorage.instance, InspirationCapability::new);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }

    @EventHandler
    public void onServerStarted(FMLServerStartingEvent event) {
        event.registerServerCommand(new GiveInspirationCommand());
    }
}
