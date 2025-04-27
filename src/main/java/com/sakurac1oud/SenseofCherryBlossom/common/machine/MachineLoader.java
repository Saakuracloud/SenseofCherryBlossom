package com.sakurac1oud.SenseofCherryBlossom.common.machine;

import com.sakurac1oud.SenseofCherryBlossom.common.CherryBlossomItemList;
import com.sakurac1oud.SenseofCherryBlossom.common.machine.multiblock.CherryBlossomAssembler;
import com.sakurac1oud.SenseofCherryBlossom.common.machine.multiblock.CherryBlossomBorderofLife;
import com.sakurac1oud.SenseofCherryBlossom.common.machine.multiblock.CherryBlossomLCR;
import com.sakurac1oud.SenseofCherryBlossom.utils.TextLocalization;

@SuppressWarnings("deprecation") // ignore deprecation for TextLocalization
public class MachineLoader {
    // 使用更安全的ID范围 (30000-31999)
    private static final int BASE_ID = 20080;

    public static void loadMachines() {
        try {
            // 尝试注册机器，捕获可能的ID冲突
            CherryBlossomItemList.CherryBlossomLCR.set(new CherryBlossomLCR(
                BASE_ID, "NameCherryBlossomLCR", TextLocalization.NameCherryBlossomLCR));

            CherryBlossomItemList.CherryBlossomBorderofLife.set(new CherryBlossomBorderofLife(
                BASE_ID + 1, "NameCherryBlossomBorderofLife", TextLocalization.NameCherryBlossomBorderofLife));

            CherryBlossomItemList.CherryBlossomAssembler.set(new CherryBlossomAssembler(
                BASE_ID + 2, "NameCherryBlossomAssembler", TextLocalization.NameCherryBlossomAssembler));

        } catch (IllegalArgumentException e) {
            // ID冲突处理
            throw new RuntimeException("Failed to register machines due to ID conflict. Please check your mod configuration.", e);
        }
    }
}
