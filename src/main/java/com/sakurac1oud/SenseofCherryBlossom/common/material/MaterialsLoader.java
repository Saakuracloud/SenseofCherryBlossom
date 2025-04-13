package com.sakurac1oud.SenseofCherryBlossom.common.material;

import com.sakurac1oud.SenseofCherryBlossom.common.init.ItemRegister;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import bartworks.API.WerkstoffAdderRegistry;

/**
 * Materials loader for the Bartworks Werkstoff system
 */
public class MaterialsLoader {
    private static boolean isLoaded = false;

    /**
     * Loads and registers all materials
 */
    public static void load() {
        if (isLoaded) {
            return; // Prevent duplicate loading
        }

        // First register items with the game
        ItemRegister.registryItems();

        // Then register item containers after items are registered
        ItemRegister.registryItemContainers();

        // Finally register materials with Werkstoff system
        WerkstoffAdderRegistry.addWerkstoffAdder(new MaterialPool());

        isLoaded = true;
    }

    /**
     * Loads materials and initializes related items
     * @param event The pre-initialization event
     */
    public static void loadWithItems(FMLPreInitializationEvent event) {
        // First register materials to ensure they're available for items
        load();

        // Then initialize items that may depend on materials
    }
}
