package com.mandatoryfun.ultramegafactory.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class CreativeTabBlock extends CreativeTabs {

    public CreativeTabBlock(String arg0) {
        super(arg0);
    }

    @Override
    public Item getTabIconItem() {
        return Item.getItemFromBlock(Blocks.chain_command_block);
    }

}
