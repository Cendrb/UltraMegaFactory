package com.mandatoryfun.ultramegafactory.item;

import com.mandatoryfun.ultramegafactory.creativetab.ModCreativeTabs;
import com.mandatoryfun.ultramegafactory.lib.IRegisterRender;
import com.mandatoryfun.ultramegafactory.lib.RefStrings;
import net.minecraft.item.Item;

/**
 * Created by cendr_000 on 3. 7. 2015.
 */
public class ItemGeneric extends Item implements IRegisterRender {

    private String pureName;
    private boolean registerRender = true;

    public ItemGeneric(String unlocalizedName) {
        super();
        setCreativeTab(ModCreativeTabs.tabItems);
        pureName = unlocalizedName;
        setUnlocalizedName(unlocalizedName);
    }

    @Override
    public void setRegisterRender(boolean registerRender) {
        this.registerRender = registerRender;
    }

    @Override
    public boolean getRegisterRender() {
        return registerRender;
    }

    public String getIdentifier() {
        return RefStrings.MODID + ":" + pureName;
    }

    public String getPureName() {
        return pureName;
    }


}
