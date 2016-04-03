package com.mandatoryfun.ultramegafactory.block;

import com.mandatoryfun.ultramegafactory.lib.IRegisterRender;
import com.mandatoryfun.ultramegafactory.lib.RefStrings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

/**
 * Created by cendr_000 on 30.03.2016.
 */
public abstract class BlockGenericContainer extends BlockContainer implements IPureName, IRegisterRender {

    private String pureName;
    private boolean registerRender = true;

    protected BlockGenericContainer(String unlocalizedName, Material materialIn) {
        super(materialIn);
        setUnlocalizedName(unlocalizedName);
    }

    @Override
    public Block setUnlocalizedName(String name) {
        pureName = name;
        return super.setUnlocalizedName(name);
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
