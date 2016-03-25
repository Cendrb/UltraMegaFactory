package com.mandatoryfun.ultramegafactory.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CreativeTabTool extends CreativeTabs {

    public CreativeTabTool(String string) {
        super(string);
    }

    @Override
    public Item getTabIconItem() {
        return Items.diamond_hoe;
    }

}
