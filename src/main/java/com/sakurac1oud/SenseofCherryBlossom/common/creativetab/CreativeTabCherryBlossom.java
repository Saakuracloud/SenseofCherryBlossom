package com.sakurac1oud.SenseofCherryBlossom.common.creativetab;

import static com.sakurac1oud.SenseofCherryBlossom.common.CherryBlossomItemList.SakuraCloud;

import com.sakurac1oud.SenseofCherryBlossom.common.CherryBlossomItemList;
import com.sakurac1oud.SenseofCherryBlossom.common.item.CherryBlossomItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabCherryBlossom  {
    public static final CreativeTabs TabGeneral = new CreativeTabs("CherryBlossom") {

        @Override
        public Item getTabIconItem() {
            return null; // don't care about it, we have override below!
        }

        @Override
        public ItemStack getIconItemStack() {
            return SakuraCloud.get(1);
        }
    };

    public static final CreativeTabs TabMetaItems = new CreativeTabs("CherryBlossomItems") {

        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return SakuraCloud.getItem();
        }
    };
    public static final CreativeTabs TabGears = new CreativeTabs("CherryBlossomGears") {

        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return SakuraCloud.getItem();
        }
    };
}
