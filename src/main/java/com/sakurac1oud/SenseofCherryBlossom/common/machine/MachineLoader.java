package com.sakurac1oud.SenseofCherryBlossom.common.machine;

import com.sakurac1oud.SenseofCherryBlossom.common.CherryBlossomItemList;
import com.sakurac1oud.SenseofCherryBlossom.common.machine.multiblock.CherryBlossomBorderofLife;
import com.sakurac1oud.SenseofCherryBlossom.common.machine.multiblock.CherryBlossomLCR;
import com.sakurac1oud.SenseofCherryBlossom.utils.TextLocalization;

@SuppressWarnings("deprecation") // ignore deprecation for TextLocalization
public class MachineLoader {
    public static void loadMachines() {
        // 使用安全范围内的ID (1-32000)
        // 为避免与其他模组冲突，使用10000-10999范围作为本模组的ID范围
        CherryBlossomItemList.CherryBlossomLCR.set(new CherryBlossomLCR(10081,"NameCherryBlossomLCR", TextLocalization.NameCherryBlossomLCR));
        CherryBlossomItemList.CherryBlossomBorderofLife.set(new CherryBlossomBorderofLife(10082,"NameCherryBlossomBorderofLife",TextLocalization.NameCherryBlossomBorderofLife));
    }
}
