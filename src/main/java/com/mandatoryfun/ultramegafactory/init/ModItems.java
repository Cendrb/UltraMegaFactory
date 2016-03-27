package com.mandatoryfun.ultramegafactory.init;

import com.mandatoryfun.ultramegafactory.item.ItemGeneric;
import com.mandatoryfun.ultramegafactory.item.ItemIngotGeneric;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModItems {

    public static ItemGeneric johnCena;

    public static void init()
    {
        Ingot.iron = register("iron_ingot", "Fe", "dEx");
        Ingot.steel = register("steel_ingot", "Fe with less C", "C means communism");
        Ingot.enrichedSteel = register("enriched_steel_ingot", "Fe with almost none of C", "C means communism");
        Ingot.copper = register("copper_ingot", "Cu", "Do not mismatch with rust");
        Ingot.tin = register("tin_ingot", "Sn", "");
        Ingot.silver = register("silver_ingot", "Ag");
        Ingot.aluminium = register("aluminium_ingot", "Al", "Do not cook with it!");
        Ingot.titanium = register("titanium_ingot", "Ti", "More expensive way to achieve durability of steel");
        Ingot.zinc = register("zinc_ingot", "Zn", "Fairly reactive, ask Alessandro Volta");
        Ingot.antimony = register("antimony_ingot", "Sb", "Not that big deal", "But with tin and lead useful", "for charging your Hungary ovens");
        Ingot.cobalt = register("cobalt_ingot", "Co", "Totally useless", "It is an actual Microsoft game");
        Ingot.chrome = register("chrome_ingot", "Cr", "Used to browse internet");
        Ingot.nickel = register("nickel_ingot", "Ni", "Metal made from Minecraft nicks");

        Ingot.steel = register("bronze_ingot", "3 Cu and 1 Sn", "You get the third place Lame!");
        Ingot.steel = register("steel_ingot", "Fe with less C");
        Ingot.steel = register("steel_ingot", "Fe with less C");

    }

    public static class Ingot
    {
        //raw metals

        public static ItemIngotGeneric iron;
        public static ItemIngotGeneric steel;
        public static ItemIngotGeneric enrichedSteel;
        public static ItemIngotGeneric copper;
        public static ItemIngotGeneric tin;
        public static ItemIngotGeneric silver;
        public static ItemIngotGeneric aluminium;
        public static ItemIngotGeneric titanium;
        public static ItemIngotGeneric zinc;
        public static ItemIngotGeneric antimony;
        public static ItemIngotGeneric lead;
        public static ItemIngotGeneric cobalt;
        public static ItemIngotGeneric chrome;
        public static ItemIngotGeneric nickel;

        //alloys

        public static ItemIngotGeneric bronze;
        public static ItemIngotGeneric electrum;
        public static ItemIngotGeneric brass;
        public static ItemIngotGeneric PbSnSb_alloy;
    }

    private static ItemGeneric register(String unlocalizedName) {
        ItemGeneric item;
        GameRegistry.registerItem(item = new ItemGeneric(unlocalizedName), unlocalizedName);
        return item;
    }

    private static ItemIngotGeneric register(String unlocalizedName, String formula, String... description) {
        ItemIngotGeneric item;
        GameRegistry.registerItem(item = new ItemIngotGeneric(unlocalizedName, formula, description), unlocalizedName);
        return item;
    }
}
