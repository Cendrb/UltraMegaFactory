package com.mandatoryfun.ultramegafactory.init;

import com.mandatoryfun.ultramegafactory.item.*;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;


public final class ModItems {

    private static ArrayList<ItemGeneric> allItems = new ArrayList<ItemGeneric>();

    public static class Ingot
    {
        //raw metals

        public static ItemIronIngot iron;
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
        public static ItemIngotGeneric gold;

        //alloys

        public static ItemIngotGeneric bronze;
        public static ItemIngotGeneric electrum;
        public static ItemIngotGeneric brass;
        public static ItemIngotGeneric PbSnSbAlloy;
    }

    public static class Dust
    {
        public static ItemGeneric kaoline;
        public static ItemGeneric lime;
        public static ItemGeneric sulfur;
        public static ItemGeneric copper_dust;
        public static ItemGeneric iron_dust;
        public static ItemGeneric antimony_dust;
        public static ItemGeneric tin_dust;
        public static ItemGeneric lead_dust;
        public static ItemGeneric silver_dust;
        public static ItemGeneric zinc_dust;
        public static ItemGeneric aluminium_dust;
        public static ItemGeneric titanium_dust;
    }

    public static ItemGeneric carbon;
    public static ItemGeneric bitumen;
    public static ItemGeneric lignite;
    public static ItemGeneric charcoal;
    public static ItemGeneric peat;

    public static ItemGeneric porcelain;

    public static void init()
    {
        // raw metals

        Ingot.iron = (ItemIronIngot) register(new ItemIronIngot("iron_ingot", "Fe", constructArray("dEx\u00AE")));
        Ingot.steel = registerIngot("steel_ingot", "Fe with less C", "C means communism");
        Ingot.enrichedSteel = registerIngot("enriched_steel_ingot", "Fe with almost no C", "C means communism");
        Ingot.copper = registerIngot("copper_ingot", "Cu", "Do not mismatch with rust");
        Ingot.tin = registerIngot("tin_ingot", "Sn", "If you really want", "you can make little tin soldiers");
        Ingot.silver = registerIngot("silver_ingot", "Ag", "Amazing electricity conductor" + "Often smelted with gold", "It's very expensive!");
        Ingot.aluminium = registerIngot("aluminium_ingot", "Al", "Do not cook with it!", "If you do not know why ask Uncle Google");
        Ingot.titanium = registerIngot("titanium_ingot", "Ti", "Light but durable metal", "Expensive as Steam\u00AE games");
        Ingot.zinc = registerIngot("zinc_ingot", "Zn", "Fairly reactive, ask Alessandro Volta");
        Ingot.antimony = registerIngot("antimony_ingot", "Sb", "Mostly useless", "With tin and lead useful for batteries");
        Ingot.lead = registerIngot("lead_ingot", "Pb", "Who is the leader now?");
        Ingot.cobalt = registerIngot("cobalt_ingot", "Co", "Totally useless", "Microsoft\u00AE");
        Ingot.chrome = registerIngot("chrome_ingot", "Cr", "One of the most expensive metals", "Try Ruby");
        Ingot.nickel = registerIngot("nickel_ingot", "Ni", "Metal made from Minecraft\u00AE nicks", "Minecraft is trademark of Microsoft, which also made MSMistake and Falldows");
        Ingot.gold = registerIngot("gold_ingot", "Au", "Ooh, shiny");

        // alloys

        Ingot.bronze = registerIngot("bronze_ingot", "3 Cu and 1 Sn", "You get the third place Lame!");
        Ingot.electrum = registerIngot("electrum_ingot", "Au and Ag", "Really good conductor of", "...wait for it...", "electricity!");
        Ingot.brass = registerIngot("brass_ingot", "Cu and Zn");
        Ingot.PbSnSbAlloy = registerIngot("PbSnSb_alloy_ingot", "Pb, Sn and Sb", "Also called battery alloy", "");

        // fuel
        carbon = registerFuel(new ItemFormulaDescriptionGeneric("carbon", "C (almost pure)", constructArray("Used in blast furnace to create better iron and steel")), 27500);
        lignite = registerFuel(new ItemFormulaDescriptionGeneric("lignite", "brown coal", constructArray("Younger, fewer life experience")), 16400);
        charcoal = registerFuel(new ItemFormulaDescriptionGeneric("charcoal", "coal from wood", constructArray("Coal made from chars", "programmers will get it")), 9700);
        peat = registerFuel(new ItemFormulaDescriptionGeneric("peat", "organic stuff", constructArray("Fire in the PEAT!")), 9700);
        bitumen = registerFuel(new ItemFormulaDescriptionGeneric("bitumen", "black coal", constructArray()), 24500);

        // dusts
        Dust.kaoline  = register(new ItemGeneric("kaoline_dust"));
        Dust.lime = register(new ItemGeneric("lime"));
        Dust.sulfur = registerDust("sulfur", "S");

        // metallic dusts
        Dust.aluminium_dust = registerDust("aluminium_dust", "Al");
        Dust.antimony_dust = registerDust("antimony_dust", "Sb");
        Dust.copper_dust = registerDust("copper_dust", "Cu");
        Dust.iron_dust = registerDust("iron_dust", "Fe", "Can not be gotten with", "anything softer than diamond");
        Dust.lead_dust = registerDust("lead_dust", "Pb", "Toxic", "You can poison your neighbors");
        Dust.silver_dust = registerDust("silver_dust", "Ag");
        Dust.tin_dust = registerDust("tin_dust", "Sn");
        Dust.titanium_dust = registerDust("titanium_dust", "Ti");
        Dust.zinc_dust = registerDust("zinc_dust", "Zn");

        //other
        porcelain = register(new ItemGeneric("porcelain_brick"));

    }

    private static ItemGeneric registerFuel(ItemGeneric item, int kJEnergyValue) {
        GameRegistry.registerItem(item, item.getPureName());
        allItems.add(item);
        UMFRegistry.Fuels.registerFuel(item, kJEnergyValue);
        return item;
    }

    private static String[] constructArray(String... strings) {
        return strings;
    }
    

    private static ItemGeneric register(ItemGeneric itemGeneric) {
        ItemGeneric item;
        GameRegistry.registerItem(item = itemGeneric, itemGeneric.getPureName());
        allItems.add(item);
        return item;
    }

    private static ItemIngotGeneric registerIngot(String unlocalizedName, String formula, String... description) {
        ItemIngotGeneric item;
        GameRegistry.registerItem(item = new ItemIngotGeneric(unlocalizedName, formula, description), unlocalizedName);
        allItems.add(item);
        return item;
    }

    private static ItemDustGeneric registerDust(String unlocalizedName, String formula, String... description) {
        ItemDustGeneric item;
        GameRegistry.registerItem(item = new ItemDustGeneric(unlocalizedName, formula, description), unlocalizedName);
        allItems.add(item);
        return item;
    }

    public static ArrayList<ItemGeneric> getAllItems() {
        return allItems;
    }
}
