package com.sakurac1oud.SenseofCherryBlossom;

import com.sakurac1oud.SenseofCherryBlossom.common.material.MaterialCherryBlossom;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        // 在这里添加你需要的预初始化逻辑
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        MaterialCherryBlossom.initClient(); // Initialize client-side material renderers
        registerRenderers(); // Register custom renderers
    }

    private void registerRenderers() {
        // Register any custom renderers here
        if (SenseofCherryBlossom.isInDevMode) {
            SenseofCherryBlossom.LOG.info("Registering renderers...");
        }
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        // 在这里添加你需要的后初始化逻辑
    }

    // Override CommonProxy methods here, if you want a different behaviour on the client (e.g. registering renders).
    // Don't forget to call the super methods as well.
}
