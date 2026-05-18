package xy177.endsdelightlegacy.common.registry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

public final class EDOreDictionary
{
    public static final String CROP_CHORUS_FRUIT = "cropChorusFruit";
    public static final String END_CRIMSON_FUNGUS = "endCrimsonFungus";
    public static final String END_WARPED_FUNGUS = "endWarpedFungus";
    public static final String END_BAMBOO = "endBamboo";
    public static final String END_PASTA_OR_DOUGH = "endPastaOrDough";
    public static final String FOOD_SHULKER_MEAT = "foodShulkerMeat";
    public static final String FOOD_ROASTED_SHULKER_MEAT = "foodRoastedShulkerMeat";
    public static final String FOOD_RAW_DRAGON_MEAT = "foodRawDragonMeat";
    public static final String FOOD_ROASTED_DRAGON_MEAT = "foodRoastedDragonMeat";

    private EDOreDictionary()
    {
    }

    public static void register()
    {
        OreDictionary.registerOre(CROP_CHORUS_FRUIT, new ItemStack(Items.CHORUS_FRUIT));
        OreDictionary.registerOre(CROP_CHORUS_FRUIT, new ItemStack(EDItems.CHORUS_FRUIT_GRAIN));
        OreDictionary.registerOre(FOOD_SHULKER_MEAT, new ItemStack(EDItems.SHULKER_MEAT));
        OreDictionary.registerOre(FOOD_SHULKER_MEAT, new ItemStack(EDItems.SHULKER_MEAT_SLICE));
        OreDictionary.registerOre(FOOD_ROASTED_SHULKER_MEAT, new ItemStack(EDItems.ROASTED_SHULKER_MEAT));
        OreDictionary.registerOre(FOOD_ROASTED_SHULKER_MEAT, new ItemStack(EDItems.ROASTED_SHULKER_MEAT_SLICE));
        OreDictionary.registerOre(FOOD_RAW_DRAGON_MEAT, new ItemStack(EDItems.RAW_DRAGON_MEAT));
        OreDictionary.registerOre(FOOD_RAW_DRAGON_MEAT, new ItemStack(EDItems.RAW_DRAGON_MEAT_CUTS));
        OreDictionary.registerOre(FOOD_ROASTED_DRAGON_MEAT, new ItemStack(EDItems.ROASTED_DRAGON_MEAT));
        OreDictionary.registerOre(FOOD_ROASTED_DRAGON_MEAT, new ItemStack(EDItems.ROASTED_DRAGON_MEAT_CUTS));
        OreDictionary.registerOre("toolKnife", new ItemStack(EDItems.END_STONE_KNIFE, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("toolKnife", new ItemStack(EDItems.PURPUR_KNIFE, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("toolKnife", new ItemStack(EDItems.DRAGON_EGG_SHELL_KNIFE, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("toolKnife", new ItemStack(EDItems.DRAGON_TOOTH_KNIFE, 1, OreDictionary.WILDCARD_VALUE));
        registerToolKnife(EDItems.END_STONE_MACHETE);
        registerToolKnife(EDItems.PURPUR_MACHETE);
        registerToolKnife(EDItems.DRAGON_EGG_SHELL_MACHETE);
        registerToolKnife(EDItems.DRAGON_TOOTH_MACHETE);
        registerFungusCompat();
        registerBambooCompat();
        registerPastaOrDoughCompat();
    }

    private static void registerFungusCompat()
    {
        boolean hasBackportFungus = false;
        hasBackportFungus |= registerExisting(END_CRIMSON_FUNGUS, "netherized:crimson_fungus");
        hasBackportFungus |= registerExisting(END_WARPED_FUNGUS, "netherized:warped_fungus");
        hasBackportFungus |= registerExisting(END_CRIMSON_FUNGUS, "nb:crimson_fungus");
        hasBackportFungus |= registerExisting(END_WARPED_FUNGUS, "nb:warped_fungus");

        if (!hasBackportFungus) {
            OreDictionary.registerOre(END_CRIMSON_FUNGUS, new ItemStack(Blocks.RED_MUSHROOM));
            OreDictionary.registerOre(END_WARPED_FUNGUS, new ItemStack(Blocks.BROWN_MUSHROOM));
        }
    }

    private static void registerBambooCompat()
    {
        if (Loader.isModLoaded("futuremc") && registerExisting(END_BAMBOO, "futuremc:bamboo")) {
            return;
        }
        OreDictionary.registerOre(END_BAMBOO, new ItemStack(Items.REEDS));
    }

    private static boolean registerExisting(String oreName, String itemId)
    {
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemId));
        if (item == null) {
            return false;
        }
        OreDictionary.registerOre(oreName, new ItemStack(item, 1, OreDictionary.WILDCARD_VALUE));
        return true;
    }

    private static void registerToolKnife(Item item)
    {
        if (item != null) {
            OreDictionary.registerOre("toolKnife", new ItemStack(item, 1, OreDictionary.WILDCARD_VALUE));
        }
    }

    private static void registerPastaOrDoughCompat()
    {
        int registered = 0;
        registered += registerOreCopies(END_PASTA_OR_DOUGH, "foodDough");
        registered += registerOreCopies(END_PASTA_OR_DOUGH, "foodPasta");

        if (registered == 0) {
            registerExisting(END_PASTA_OR_DOUGH, "farmersdelight:wheat_dough");
            registerExisting(END_PASTA_OR_DOUGH, "farmersdelight:raw_pasta");
        }
    }

    private static int registerOreCopies(String targetOreName, String sourceOreName)
    {
        int registered = 0;
        for (ItemStack stack : OreDictionary.getOres(sourceOreName, false)) {
            if (stack.isEmpty()) {
                continue;
            }
            OreDictionary.registerOre(targetOreName, stack.copy());
            registered++;
        }
        return registered;
    }
}
