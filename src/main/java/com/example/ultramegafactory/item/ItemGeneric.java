package com.example.ultramegafactory.item;

import com.cendrb.rododendron.creative_tab.CreativeTabRodo;
import com.cendrb.rododendron.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by cendr_000 on 3. 7. 2015.
 */
public class ItemGeneric extends Item {

    public ItemGeneric()
    {
        super();
        this.setNoRepair();
        this.setCreativeTab(ModCreativeTabs.RODODENDRON_TAB);
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("item.%s%s", RefStrings.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
