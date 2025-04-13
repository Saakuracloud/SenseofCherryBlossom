package com.sakurac1oud.SenseofCherryBlossom.mixin;

import java.util.Map;

import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.MCVersion("1.7.10")
@IFMLLoadingPlugin.Name("SenseOfCherryBlossomMixinLoader")
public class mixin implements IFMLLoadingPlugin {

    public mixin() {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.SenseofCherryBlossom.json");
        MixinEnvironment.getDefaultEnvironment()
            .setSide(MixinEnvironment.Side.CLIENT);
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        // No injection needed
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
