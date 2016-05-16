package com.mandatoryfun.ultramegafactory.lib;

import net.minecraft.util.math.BlockPos;

/**
 * Created by cendr_000 on 28.03.2016.
 */
public class UMFLogger {
    public static void logInfo(Object string) {
        System.out.println(string);
    }

    public static void logWarning(Object string) {
        System.out.println("WARNING: " + string.toString());
    }

    public static void logError(Object string) {
        System.out.println("ERROR: " + string.toString());
    }

    public static String formatBlockPos(BlockPos blockPos) {
        return blockPos.toString();
    }
}
