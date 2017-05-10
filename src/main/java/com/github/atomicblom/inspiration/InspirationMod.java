package com.github.atomicblom.inspiration;

import com.github.atomicblom.inspiration.util.Logger;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = InspirationMod.MODID, version = InspirationMod.VERSION)
public class InspirationMod
{
    public static final String MODID = "@MOD_ID@";
    public static final String VERSION = "@MOD_VERSION@";
    public static boolean DEBUG = false;
    public static final String IS_CI_BUILD = "@CI_BUILD@";

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
