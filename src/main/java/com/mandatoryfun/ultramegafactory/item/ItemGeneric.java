package com.mandatoryfun.ultramegafactory.item;

import com.mandatoryfun.ultramegafactory.creativetab.ModCreativeTabs;
import com.mandatoryfun.ultramegafactory.lib.RefStrings;
import net.minecraft.item.Item;

/**
 * Created by cendr_000 on 3. 7. 2015.
 */
public class ItemGeneric extends Item {

    private String pureName;
    private boolean registerRender = true;

    public ItemGeneric(String unlocalizedName) {
        super();
        setCreativeTab(ModCreativeTabs.tabItems);
        pureName = unlocalizedName;
        setUnlocalizedName(unlocalizedName);
    }

    public void setRegisterRender(boolean registerRender) {
        this.registerRender = registerRender;
    }
    public boolean isRegisterRender() {
        return registerRender;
    }

    public String getIdentifier() {
        return RefStrings.MODID + ":" + pureName;
    }

    public String getPureName() {
        return pureName;
    }
}
