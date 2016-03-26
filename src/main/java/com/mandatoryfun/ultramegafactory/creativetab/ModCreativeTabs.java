package com.mandatoryfun.ultramegafactory.creativetab;

import net.minecraft.creativetab.CreativeTabs;

public class ModCreativeTabs {
    public static CreativeTabs tabBlock;
    public static CreativeTabs tabItems;
    public static CreativeTabs tabTools;

    public static void init() {
        tabBlock = new CreativeTabBlock("UMFBlocks");
        tabItems = new CreativeTabItem("UMFItems");
        tabTools = new CreativeTabTool("UMFTools");
    }
}
