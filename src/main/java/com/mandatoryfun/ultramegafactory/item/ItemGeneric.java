package com.mandatoryfun.ultramegafactory.item;

import com.mandatoryfun.ultramegafactory.creativetab.ModCreativeTabs;
import com.mandatoryfun.ultramegafactory.lib.RefStrings;
import net.minecraft.item.Item;

/**
 * Created by cendr_000 on 3. 7. 2015.
 */
public class ItemGeneric extends Item {

    private String pureName;

    public ItemGeneric(String registryName) {
        super();
        setCreativeTab(ModCreativeTabs.tabItems);
        pureName = registryName;
        setUnlocalizedName(registryName);
        //setRegistryName(registryName);
    }

    public String getItemModelPath() {
        return RefStrings.MODID + ":" + pureName;
    }

    public String getPureName() {
        return pureName;
    }
}
