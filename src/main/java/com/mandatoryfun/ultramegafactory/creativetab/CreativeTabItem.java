package com.mandatoryfun.ultramegafactory.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CreativeTabItem extends CreativeTabs {

    public CreativeTabItem(String arg0) {
        super(arg0);
    }

    @Override
    public Item getTabIconItem() {
        return Items.chorus_fruit;
    }

}
