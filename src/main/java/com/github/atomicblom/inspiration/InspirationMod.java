package com.github.atomicblom.inspiration;

import com.github.atomicblom.inspiration.util.Logger;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static com.github.atomicblom.inspiration.events.Resources.IS_CI_BUILD;
import static com.github.atomicblom.inspiration.events.Resources.MODID;
import static com.github.atomicblom.inspiration.events.Resources.VERSION;

@Mod(modid = MODID, version = VERSION)
public class InspirationMod
{

    public static boolean DEBUG = false;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        if (Boolean.parseBoolean(IS_CI_BUILD)) {
            DEBUG = false;
        }
        if (DEBUG) {
            Logger.info("Inspiration Debugging is enabled.");
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        System.out.println("DIRT BLOCK >> "+Blocks.DIRT.getUnlocalizedName());
    }
}
