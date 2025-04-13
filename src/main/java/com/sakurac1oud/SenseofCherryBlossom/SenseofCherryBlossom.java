package com.sakurac1oud.SenseofCherryBlossom;

import java.util.HashMap;
import java.util.Map;

import com.sakurac1oud.SenseofCherryBlossom.common.material.MaterialsLoader;
import net.minecraft.entity.player.EntityPlayer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sakurac1oud.SenseofCherryBlossom.common.material.MaterialCherryBlossom;
import com.sakurac1oud.SenseofCherryBlossom.common.material.MaterialPool;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;

@Mod(
    modid = "senseofcherryblossom",
    version = "0.0.1",
    name = "Sense of Cherry Blossom",
    dependencies = "required-after:gregtech;" + " required-after:bartworks;",
    acceptedMinecraftVersions = "[1.7.10]")
public class SenseofCherryBlossom {

    public static final String MODID = "senseofcherryblossom";
    public static final String MODNAME = "Sense of Cherry Blossom";
    public static final String VERSION = "0.0.1";
    public static final Logger LOG = LogManager.getLogger(MODID);
    public static final String RESOURCE_ROOT_ID = "senseofcherryblossom";
    public static final String RESOURCE_DOMAIN = "senseofcherryblossom";
    // Set to true during development to enable automatic translation key generation
    public static final boolean isInDevMode = true;

    @Mod.Instance
    public static SenseofCherryBlossom instance;

    @SidedProxy(
        clientSide = "com.sakurac1oud.SenseofCherryBlossom.ClientProxy",
        serverSide = "com.sakurac1oud.SenseofCherryBlossom.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {

        LOG.info("SenseofCherryBlossom preInit starting...");
        // Initialize materials early to ensure they're available for registration
        proxy.preInit(event);
        LOG.info("SenseofCherryBlossom preInit completed.");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LOG.info("SenseofCherryBlossom init starting...");
        proxy.init(event);
        LOG.info("SenseofCherryBlossom init completed.");
    }

    public static void registerRenderers() {}

    @Mod.EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this."
    public void postInit(FMLPostInitializationEvent event) {
        // Register Bartworks materials in postInit phase
        // This ensures GT materials are fully loaded


        proxy.postInit(event);
    }

    @Mod.EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);

    }

    @Mod.EventHandler
    public void onServerStarted(FMLServerStartedEvent event) {}

    @Mod.EventHandler
    public void earlyGame(FMLPreInitializationEvent event) {
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    public void onLoadComplete(FMLLoadCompleteEvent event) {
        proxy.onLoadComplete(event);
    }

    private final Map<EntityPlayer, Integer> playerTimerMap = new HashMap<>();
}
