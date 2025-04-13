package com.sakurac1oud.SenseofCherryBlossom.common.recipe.MachineRecipe;

import com.dreammaster.gthandler.CustomItemList;
import com.sakurac1oud.SenseofCherryBlossom.common.CherryBlossomItemList;
import com.sakurac1oud.SenseofCherryBlossom.common.material.MaterialCherryBlossom;
import com.sakurac1oud.SenseofCherryBlossom.common.material.MaterialPool;
import com.sakurac1oud.SenseofCherryBlossom.common.recipe.RecipeMap.CherryBlossomRecipe;
import com.sakurac1oud.SenseofCherryBlossom.utils.rewrite.CherryBlossomItemID;
import gregtech.api.enums.*;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.util.GTModHandler;
import gregtech.api.util.GTRecipe;
import gregtech.api.util.GTRecipeBuilder;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.BlockFrameBox;
import gtPlusPlus.core.material.MaterialMisc;
import gtPlusPlus.core.material.MaterialsAlloy;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.ParametersAreNonnullByDefault;

import bartworks.util.BWUtil;
import gregtech.api.objects.ItemData;
import gregtech.api.util.GTOreDictUnificator;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import static com.sakurac1oud.SenseofCherryBlossom.common.machine.multiblock.CherryBlossomBorderofLife.circuitItemsToWrapped;
import static com.sakurac1oud.SenseofCherryBlossom.utils.Utils.copyAmount;
import static gregtech.api.enums.TierEU.RECIPE_MAX;
import static gregtech.api.recipe.RecipeMaps.circuitAssemblerRecipes;
import static gregtech.api.util.GTUtility.copyAmountUnsafe;

@ParametersAreNonnullByDefault
public class BorderofLife {
    private static final RecipeMap<?>BoL = CherryBlossomRecipe.TheBorderofLife;

    public static void loadRecipes(){

        GTValues.RA.stdBuilder()
            .itemInputs(
                GTOreDictUnificator.get(OrePrefixes.frameGt,MaterialsUEVplus.Eternity, 16384),
                ItemList.Field_Generator_UMV.get(64),
                CherryBlossomItemList.Hangonchou.get(2048),
                CherryBlossomItemList.Hangonchou.get(16384),
                GTOreDictUnificator.get(OrePrefixes.nanite,MaterialsUEVplus.Eternity, 2048),
                CustomItemList.RadoxPolymerLens.get(1024),
                GTOreDictUnificator.get(OrePrefixes.ingotQuintuple,Materials.RadoxPolymer, 16384)
            )
            .fluidInputs(MaterialPool.SakuraCloudDream.getFluidOrGas(144000),MaterialsUEVplus.BlackDwarfMatter.getFluid(14400),MaterialsUEVplus.SpaceTime.getMolten(1638400))
            .itemOutputs(CherryBlossomItemList
                    .CherryBlossomCircuit_ULV.get(16384*15),
                CherryBlossomItemList.CherryBlossomCircuit_LV.get(16384*14),
                CherryBlossomItemList.CherryBlossomCircuit_MV.get(16384*13),
                CherryBlossomItemList.CherryBlossomCircuit_HV.get(16384*12),
                CherryBlossomItemList.CherryBlossomCircuit_EV.get(16384*11),
                CherryBlossomItemList.CherryBlossomCircuit_IV.get(16384*10),
                CherryBlossomItemList.CherryBlossomCircuit_LuV.get(16384*9),
                CherryBlossomItemList.CherryBlossomCircuit_ZPM.get(16384*8),
                CherryBlossomItemList.CherryBlossomCircuit_UV.get(16384*7),
                CherryBlossomItemList.CherryBlossomCircuit_UHV.get(16384*6),
                CherryBlossomItemList.CherryBlossomCircuit_UEV.get(16384*5),
                CherryBlossomItemList.CherryBlossomCircuit_UIV.get(16384*4),
                CherryBlossomItemList.CherryBlossomCircuit_UMV.get(16384*3),
                CherryBlossomItemList.CherryBlossomCircuit_UXV.get(16384*2),
                CherryBlossomItemList.CherryBlossomCircuit_MAX.get(16384)
            )
            .duration(20*16384)
            .eut(RECIPE_MAX)
            .specialValue(15000)
            .addTo(BoL);

        CherryBlossomItemID IC2_Circuit = CherryBlossomItemID
            .create(GTModHandler.getModItem(Mods.IndustrialCraft2.ID, "itemPartCircuit", 1));
        CherryBlossomItemID IC2_AdvCircuit = CherryBlossomItemID
            .create(GTModHandler.getModItem(Mods.IndustrialCraft2.ID, "itemPartCircuitAdv", 1));
            FluidStack SolderingAlloy = Materials.SolderingAlloy.getMolten(0);
            FluidStack INDALLOY_140 = MaterialsAlloy.INDALLOY_140.getFluidStack(0);
            FluidStack MUTATED_LIVING_SOLDER = MaterialMisc.MUTATED_LIVING_SOLDER.getFluidStack(0);
            FluidStack SakuraCloudDream = MaterialPool.SakuraCloudDream.getFluidOrGas(0);

            for (GTRecipe originalRecipe : circuitAssemblerRecipes.getAllRecipes()) {
                if (originalRecipe == null) continue;
                ItemStack output = originalRecipe.mOutputs[0];
                // skip IC2 circuit
                if (IC2_Circuit.equalItemStack(output) || IC2_AdvCircuit.equalItemStack(output)) continue;

                // check fluid
                if (originalRecipe.mFluidInputs == null || originalRecipe.mFluidInputs.length < 1) continue;
                if (!originalRecipe.mFluidInputs[0].isFluidEqual(SolderingAlloy)
                    && !originalRecipe.mFluidInputs[0].isFluidEqual(INDALLOY_140)
                    && !originalRecipe.mFluidInputs[0].isFluidEqual(MUTATED_LIVING_SOLDER)
                    && !originalRecipe.mFluidInputs[0].isFluidEqual(SakuraCloudDream))continue;

                // check output item whether is a circuit
                String unlocalizedName = output.getUnlocalizedName();
                if (!isCircuitOreDict(output) && !unlocalizedName.contains("Circuit")
                    && !unlocalizedName.contains("circuit")) continue;

                GTRecipeBuilder.builder()
                    .itemInputs(ModifyInput(originalRecipe.mInputs.clone()))
                    .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getPlasma(144000))
                    .itemOutputs(copyAmountUnsafe(originalRecipe.mOutputs[0].stackSize * 640, originalRecipe.mOutputs[0]))
                    .eut(originalRecipe.mEUt*100)
                    .specialValue(9800)
                    .duration(100)
                    .addTo(BoL);

                GTRecipeBuilder.builder()
                    .itemInputs(ModifyInput(originalRecipe.mInputs.clone()))
                    .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(288000))
                    .itemOutputs(copyAmountUnsafe(originalRecipe.mOutputs[0].stackSize * 320, originalRecipe.mOutputs[0]))
                    .eut(originalRecipe.mEUt*100)
                    .specialValue(6400)
                    .duration(100)
                    .addTo(BoL);

                GTRecipeBuilder.builder()
                    .itemInputs(ModifyInput(originalRecipe.mInputs.clone()))
                    .fluidInputs(MaterialPool.SakuraCloudDream.getFluidOrGas(14400))
                    .itemOutputs(copyAmountUnsafe(originalRecipe.mOutputs[0].stackSize * 16384, originalRecipe.mOutputs[0]))
                    .eut(originalRecipe.mEUt*100)
                    .specialValue(14400)
                    .duration(100)
                    .addTo(BoL);
            }
        }

        // Modify the circuit part to warp, others turn 16 times.
        public static ItemStack[] ModifyInput(ItemStack[] input) {
            if (!(input != null && input.length > 0)) return new ItemStack[0];
            ArrayList<ItemStack> inputItems = new ArrayList<>();

            for (ItemStack aStack : input) {
                boolean isItemModified = false;
                // Modify circuit part
                for (Map.Entry<ItemStack, ItemStack> entry : circuitItemsToWrapped.entrySet()) {
                    if (GTUtility.areStacksEqual(entry.getKey(), aStack)) {
                        // not modify to wrap circuit
                        if (Item.itemRegistry.getNameForObject(
                                entry.getValue()
                                    .getItem())
                            .contains(Mods.GoodGenerator.ID)) break;
                        inputItems.add(copyAmount(aStack.stackSize, entry.getValue()));
                        isItemModified = true;
                        break;
                    }
                }

                // Modify wire
                if (!isItemModified && BWUtil.checkStackAndPrefix(aStack)) {
                    ItemData Data = Objects.requireNonNull(GTOreDictUnificator.getAssociation(aStack));
                    Materials Material = Data.mMaterial.mMaterial;
                    OrePrefixes OreDict = Data.mPrefix;
                    if (OreDict == OrePrefixes.wireGt01) {
                        ItemStack newStack = GTOreDictUnificator.get(OrePrefixes.wireGt16, Material, aStack.stackSize);
                        if (newStack != null) {
                            inputItems.add(newStack);
                            isItemModified = true;
                        }
                    } else if (OreDict == OrePrefixes.wireFine) {
                        ItemStack newStack = GTOreDictUnificator.get(OrePrefixes.wireGt04, Material, aStack.stackSize);
                        if (newStack != null) {
                            inputItems.add(newStack);
                            isItemModified = true;
                        }
                    }
                }

                if (!isItemModified) inputItems.add(copyAmount(aStack.stackSize * 16, aStack));
            }

            return inputItems.toArray(new ItemStack[0]);

        }

        public static boolean isCircuitOreDict(ItemStack item) {
            return BWUtil.isTieredCircuit(item) || BWUtil.getOreNames(item)
                .stream()
                .anyMatch("circuitPrimitiveArray"::equals);
        }

}
