package com.mandatoryfun.ultramegafactory.lib;

import net.minecraft.util.ResourceLocation;

import java.sql.Ref;

/**
 * Created by cendr_000 on 03.04.2016.
 */
public class RegistryHelper {

    public static ResourceLocation getResource(String file)
    {
        return new ResourceLocation(RefStrings.MODID, file);
    }
}
