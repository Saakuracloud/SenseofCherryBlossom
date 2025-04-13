package com.sakurac1oud.SenseofCherryBlossom.common.item;

import com.sakurac1oud.SenseofCherryBlossom.common.item.AbstractCherryBlossomMetaItem;

public class ItemAdder extends AbstractCherryBlossomMetaItem {

    /**
     * Create the basic item MetaItem.
     */
    public ItemAdder() {
        // #tr item.MetaItem.0.name
        // # Test Item
        // #zh_CN 测试物品
        super("MetaItem");
        // 不需要手动设置纹理路径，AbstractCherryBlossomMetaItem 会自动处理
    }

}
