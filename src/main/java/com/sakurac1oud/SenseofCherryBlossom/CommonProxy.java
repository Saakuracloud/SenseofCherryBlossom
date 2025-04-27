package com.sakurac1oud.SenseofCherryBlossom;

import com.sakurac1oud.SenseofCherryBlossom.common.init.ItemRegister;
import com.sakurac1oud.SenseofCherryBlossom.common.machine.MachineLoader;
import com.sakurac1oud.SenseofCherryBlossom.common.material.MaterialPool;
import com.sakurac1oud.SenseofCherryBlossom.common.material.MaterialsLoader;
import com.sakurac1oud.SenseofCherryBlossom.common.recipe.RecipeLoader;
import com.sakurac1oud.SenseofCherryBlossom.utils.LanguageManager;
import cpw.mods.fml.common.event.*;
import gtPlusPlus.core.handler.CompatHandler;
import gtPlusPlus.core.handler.CompatIntermodStaging;

import static com.sakurac1oud.SenseofCherryBlossom.SenseofCherryBlossom.LOG;

/**
 * Common proxy handles mod initialization for both client and server
 */
public class CommonProxy {

    /**
     * Pre-initialization phase
     * Read configs, register blocks, items, etc.
     */
    public void preInit(FMLPreInitializationEvent event) {
        // Initialize items and materials in the correct order
        MaterialsLoader.load();
        ItemRegister.registry();
        LanguageManager.init();

        // Then initialize creative tabs (which depend on items)
    }

    /**
     * Initialization phase
     * Build data structures, register recipes
     */
    public void init(FMLInitializationEvent event) {
        // Initialize recipes and other systems
        MachineLoader.loadMachines();

    }

    /**
     * Post-initialization phase
     * Handle interaction with other mods, complete setup
     */
    public void postInit(FMLPostInitializationEvent event) {
        // Handle cross-mod integration
        try {
            MaterialPool materialPool = new MaterialPool();
            materialPool.run();
            RecipeLoader.loadRecipes();// Run directly instead of using a new thread.
        } catch (Exception e) {
            LOG.error("Failed to register Bartworks materials", e);
        }
    }

    /**
     * Server starting event
     * Register server commands
     */
    public void serverStarting(FMLServerStartingEvent event) {
        // Register server commands if needed
    }

    /**
     * Called when all mods have finished loading
     */
    public void onLoadComplete(FMLLoadCompleteEvent event) {
        CompatIntermodStaging.onLoadComplete(event);
        CompatHandler.onLoadComplete(event);

    }
}
