package com.mandatoryfun.ultramegafactory.creativetab;

import net.minecraft.creativetab.CreativeTabs;

public class ModCreativeTabs {
    public static CreativeTabs tabBlock;
    public static CreativeTabs tabItems;
    public static CreativeTabs tabTools;

    public static void initializeTabs() {
        tabBlock = new CreativeTabBlock("BlockTab");
        tabItems = new CreativeTabItem("ItemTab");
        tabTools = new CreativeTabTool("ToolTab");
    }
}
