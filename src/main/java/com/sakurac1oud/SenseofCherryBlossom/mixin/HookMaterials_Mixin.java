package com.sakurac1oud.SenseofCherryBlossom.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.sakurac1oud.SenseofCherryBlossom.SenseofCherryBlossom;
import com.sakurac1oud.SenseofCherryBlossom.common.material.MaterialCherryBlossom;
import com.sakurac1oud.SenseofCherryBlossom.utils.LanguageManager;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import gregtech.GTMod;

@Mixin(value = GTMod.class, remap = false)
public class HookMaterials_Mixin {

    @Inject(method = "onPreLoad", at = @At(value = "INVOKE", target = "Lgregtech/api/enums/Materials;init()V"))
    private void socb$runBeforeMaterialsInit(FMLPreInitializationEvent aEvent, CallbackInfo ci) {
        MaterialCherryBlossom.init();
        LanguageManager.initGTMaterials();
        SenseofCherryBlossom.LOG.info("Materials Hooked!");
    }

}
