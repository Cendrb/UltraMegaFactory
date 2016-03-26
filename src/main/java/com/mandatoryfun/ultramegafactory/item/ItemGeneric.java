package com.mandatoryfun.ultramegafactory.item;

import com.mandatoryfun.ultramegafactory.creativetab.ModCreativeTabs;
import com.mandatoryfun.ultramegafactory.lib.RefStrings;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by cendr_000 on 3. 7. 2015.
 */
public class ItemGeneric extends Item {

    private String pureName;

    public ItemGeneric(String unlocalizedName) {
        super();
        setNoRepair();
        setCreativeTab(ModCreativeTabs.tabItems);
        pureName = unlocalizedName;
        setUnlocalizedName(unlocalizedName);
    }

    public String getIdentifier()
    {
        return RefStrings.MODID + ":" + pureName;
    }
    public String getPureName()
    {
        return pureName;
    }
}
