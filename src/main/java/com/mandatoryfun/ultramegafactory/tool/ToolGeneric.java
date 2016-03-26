package com.mandatoryfun.ultramegafactory.tool;

import com.mandatoryfun.ultramegafactory.creativetab.ModCreativeTabs;
import com.mandatoryfun.ultramegafactory.item.ItemGeneric;

/**
 * Created by Wester on 3/26/2016.
 */
public class ToolGeneric extends ItemGeneric {
    public ToolGeneric(String unlocalizedName) {
        super(unlocalizedName);
        setCreativeTab(ModCreativeTabs.tabTools);
    }

}
