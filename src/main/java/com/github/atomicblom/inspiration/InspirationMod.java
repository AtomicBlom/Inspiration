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
    public static final String MODID = "@mod_id@";
    public static final String VERSION = "@mod_version@";
    public static boolean DEBUG = false;
    public static final String IS_CI_BUILD = "@ci_build@";

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
