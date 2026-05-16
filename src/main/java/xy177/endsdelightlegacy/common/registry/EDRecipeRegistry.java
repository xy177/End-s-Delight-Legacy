package xy177.endsdelightlegacy.common.registry;

import com.wdcftgg.farmersdelightlegacy.api.heat.HeatSourceApi;
import com.wdcftgg.farmersdelightlegacy.api.recipe.CookingPotRecipeApi;
import com.wdcftgg.farmersdelightlegacy.api.recipe.CuttingBoardRecipeApi;
import com.wdcftgg.farmersdelightlegacy.common.recipe.CampfireCookingRecipeManager;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import xy177.endsdelightlegacy.EndsDelightLegacy;
import xy177.endsdelightlegacy.common.block.BlockEndStove;

public final class EDRecipeRegistry
{
    private static boolean registered;

    private EDRecipeRegistry()
    {
    }

    public static void registerAll()
    {
        if (registered) {
            return;
        }
        registered = true;

        registerFurnaceRecipes();
        registerCuttingBoardRecipes();
        registerCookingPotRecipes();
        registerCampfireRecipes();
        registerHeatSources();
        registerFutureMcSmithingRecipes();
    }

    public static void registerCraftingRecipes(IForgeRegistry<IRecipe> registry)
    {
        if (!Loader.isModLoaded("futuremc")) {
            registerDragonToothKnifeCrafting(registry);
        }
    }

    private static void registerFurnaceRecipes()
    {
        FurnaceRecipes.instance().addSmeltingRecipe(
            new ItemStack(Blocks.CHORUS_FLOWER),
            new ItemStack(EDItems.DRIED_CHORUS_FLOWER),
            0.35F
        );
        FurnaceRecipes.instance().addSmeltingRecipe(
            new ItemStack(EDItems.LIQUID_DRAGON_EGG),
            new ItemStack(EDItems.FRIED_DRAGON_EGG),
            0.35F
        );
        FurnaceRecipes.instance().addSmeltingRecipe(
            new ItemStack(EDItems.SHULKER_MEAT),
            new ItemStack(EDItems.ROASTED_SHULKER_MEAT),
            0.35F
        );
        FurnaceRecipes.instance().addSmeltingRecipe(
            new ItemStack(EDItems.SHULKER_MEAT_SLICE),
            new ItemStack(EDItems.ROASTED_SHULKER_MEAT_SLICE),
            0.35F
        );
        FurnaceRecipes.instance().addSmeltingRecipe(
            new ItemStack(EDItems.RAW_ENDER_MITE_MEAT),
            new ItemStack(EDItems.DRIED_ENDERMITE_MEAT),
            0.35F
        );
        FurnaceRecipes.instance().addSmeltingRecipe(
            new ItemStack(EDItems.RAW_DRAGON_MEAT),
            new ItemStack(EDItems.ROASTED_DRAGON_MEAT),
            0.35F
        );
        FurnaceRecipes.instance().addSmeltingRecipe(
            new ItemStack(EDItems.RAW_DRAGON_MEAT_CUTS),
            new ItemStack(EDItems.ROASTED_DRAGON_MEAT_CUTS),
            0.35F
        );
        FurnaceRecipes.instance().addSmeltingRecipe(
            new ItemStack(EDItems.DRAGON_LEG),
            new ItemStack(EDItems.SMOKED_DRAGON_LEG),
            0.35F
        );
        FurnaceRecipes.instance().addSmeltingRecipe(
            new ItemStack(EDItems.RAW_ENDER_SAUSAGE),
            new ItemStack(EDItems.ENDER_SAUSAGE),
            0.35F
        );
        FurnaceRecipes.instance().addSmeltingRecipe(
            new ItemStack(EDItems.SHULKER_OMELETTE_MIXTURE),
            new ItemStack(EDItems.SHULKER_OMELETTE),
            0.35F
        );
    }

    private static void registerCuttingBoardRecipes()
    {
        CuttingBoardRecipeApi.registerRecipe(
            id("chorus_fruit_grain"),
            "minecraft:chorus_fruit",
            null,
            id("chorus_fruit_grain"),
            4,
            1.0F
        );

        CuttingBoardRecipeApi.registerRecipe(
            id("ender_pearl_grain"),
            "minecraft:ender_pearl",
            null,
            id("ender_pearl_grain"),
            4,
            1.0F
        );

        CuttingBoardRecipeApi.registerRecipe(
            id("chorus_fruit_pie_cutting"),
            id("chorus_fruit_pie"),
            null,
            id("chorus_fruit_pie_slice"),
            4,
            1.0F
        );

        CuttingBoardRecipeApi.registerRecipe(
            id("crack_non_hatchable_dragon_egg"),
            new String[] { id("non_hatchable_dragon_egg") },
            new String[] { "minecraft:nether_star" },
            new String[] { id("liquid_dragon_egg"), id("half_dragon_egg_shell") },
            new int[] { 1, 1 },
            new float[] { 1.0F, 1.0F }
        );

        CuttingBoardRecipeApi.registerRecipe(
            id("shulker_meat_slice"),
            id("shulker_meat"),
            null,
            id("shulker_meat_slice"),
            2,
            1.0F
        );

        CuttingBoardRecipeApi.registerRecipe(
            id("roasted_shulker_meat_slice"),
            id("roasted_shulker_meat"),
            null,
            id("roasted_shulker_meat_slice"),
            2,
            1.0F
        );

        CuttingBoardRecipeApi.registerRecipe(
            id("raw_dragon_meat_cuts"),
            id("raw_dragon_meat"),
            null,
            id("raw_dragon_meat_cuts"),
            3,
            1.0F
        );

        CuttingBoardRecipeApi.registerRecipe(
            id("roasted_dragon_meat_cuts"),
            id("roasted_dragon_meat"),
            null,
            id("roasted_dragon_meat_cuts"),
            3,
            1.0F
        );

        CuttingBoardRecipeApi.registerRecipe(
            id("leg_to_meat"),
            new String[] { id("dragon_leg") },
            null,
            new String[] { id("raw_dragon_meat"), "minecraft:dye@15" },
            new int[] { 1, 6 },
            new float[] { 1.0F, 1.0F }
        );

        CuttingBoardRecipeApi.registerRecipe(
            id("smoked_leg_to_meat"),
            new String[] { id("smoked_dragon_leg") },
            null,
            new String[] { id("roasted_dragon_meat"), "minecraft:dye@15" },
            new int[] { 1, 6 },
            new float[] { 1.0F, 1.0F }
        );
    }

    private static void registerCookingPotRecipes()
    {
        CookingPotRecipeApi.registerRecipe(
            id("chorus_flower_tea"),
            new String[] {
                "minecraft:ghast_tear",
                id("dried_chorus_flower")
            },
            new ItemStack(EDItems.CHORUS_FLOWER_TEA),
            new ItemStack(Items.GLASS_BOTTLE),
            200,
            1.0F
        );

        CookingPotRecipeApi.registerRecipe(
            id("chorus_fruit_milk_tea"),
            new String[] {
                "ore:listAllmilk",
                ore(EDOreDictionary.CROP_CHORUS_FRUIT)
            },
            new ItemStack(EDItems.CHORUS_FRUIT_MILK_TEA),
            new ItemStack(Items.GLASS_BOTTLE),
            200,
            1.0F
        );

        CookingPotRecipeApi.registerRecipe(
            id("bubble_tea"),
            new String[] {
                "ore:listAllmilk",
                ore(EDOreDictionary.CROP_CHORUS_FRUIT),
                id("ender_pearl_grain")
            },
            new ItemStack(EDItems.BUBBLE_TEA),
            new ItemStack(Items.GLASS_BOTTLE),
            200,
            1.0F
        );

        CookingPotRecipeApi.registerRecipe(
            id("chorus_fruit_wine"),
            new String[] {
                "minecraft:sugar",
                ore(EDOreDictionary.CROP_CHORUS_FRUIT),
                ore(EDOreDictionary.CROP_CHORUS_FRUIT)
            },
            new ItemStack(EDItems.CHORUS_FRUIT_WINE),
            new ItemStack(Items.GLASS_BOTTLE),
            200,
            1.0F
        );

        CookingPotRecipeApi.registerRecipe(
            id("dragon_breath_and_chorus_soup"),
            new String[] {
                Loader.isModLoaded("futuremc") ? "futuremc:suspicious_stew" : "farmersdelight:bone_broth",
                "minecraft:dragon_breath",
                id("dried_chorus_flower"),
                "minecraft:chorus_fruit",
                "minecraft:chorus_fruit"
            },
            new ItemStack(EDItems.DRAGON_BREATH_AND_CHORUS_SOUP),
            new ItemStack(Items.BOWL),
            200,
            1.0F
        );

        CookingPotRecipeApi.registerRecipe(
            id("chorus_sauce"),
            new String[] {
                id("chorus_succulent"),
                ore(EDOreDictionary.CROP_CHORUS_FRUIT)
            },
            new ItemStack(EDItems.CHORUS_SAUCE),
            new ItemStack(Items.BOWL),
            200,
            0.35F
        );

        CookingPotRecipeApi.registerRecipe(
            id("stir_fried_shulker_meat"),
            new String[] {
                "ore:cropOnion",
                "ore:cropTomato",
                ore(EDOreDictionary.CROP_CHORUS_FRUIT),
                ore(EDOreDictionary.FOOD_SHULKER_MEAT)
            },
            new ItemStack(EDItems.STIR_FRIED_SHULKER_MEAT),
            new ItemStack(Items.BOWL),
            200,
            1.0F
        );

        CookingPotRecipeApi.registerRecipe(
            id("ender_congee"),
            new String[] {
                "ore:cropRice",
                id("dried_endermite_meat"),
                id("ender_pearl_grain")
            },
            new ItemStack(EDItems.ENDER_CONGEE),
            new ItemStack(Items.BOWL),
            200,
            1.0F
        );

        CookingPotRecipeApi.registerRecipe(
            id("shulker_soup"),
            new String[] {
                ore(EDOreDictionary.FOOD_SHULKER_MEAT),
                id("dried_chorus_flower"),
                id("chorus_succulent")
            },
            new ItemStack(EDItems.SHULKER_SOUP),
            new ItemStack(Items.BOWL),
            200,
            1.0F
        );

        CookingPotRecipeApi.registerRecipe(
            id("ender_noodle"),
            new String[] {
                ore(EDOreDictionary.FOOD_SHULKER_MEAT),
                id("dried_endermite_meat"),
                id("chorus_sauce"),
                "ore:listAllmushroom",
                ore(EDOreDictionary.END_PASTA_OR_DOUGH)
            },
            new ItemStack(EDItems.ENDER_NOODLE, 2),
            new ItemStack(Items.BOWL),
            200,
            1.0F
        );

        CookingPotRecipeApi.registerRecipe(
            id("ender_bamboo_rice"),
            new String[] {
                ore(EDOreDictionary.END_BAMBOO),
                "ore:cropRice",
                ore(EDOreDictionary.FOOD_RAW_DRAGON_MEAT),
                id("chorus_succulent"),
                "minecraft:carrot",
                "ore:cropOnion"
            },
            new ItemStack(EDItems.ENDER_BAMBOO_RICE, 2),
            200,
            1.0F
        );

        CookingPotRecipeApi.registerRecipe(
            id("enderman_gristle_stew"),
            new String[] {
                id("enderman_gristle"),
                id("enderman_gristle"),
                id("dried_chorus_flower"),
                id("chorus_succulent")
            },
            new ItemStack(EDItems.ENDERMAN_GRISTLE_STEW),
            new ItemStack(Items.BOWL),
            200,
            1.0F
        );

        CookingPotRecipeApi.registerRecipe(
            id("steamed_dragon_egg_block"),
            new String[] {
                id("liquid_dragon_egg"),
                ore(EDOreDictionary.FOOD_SHULKER_MEAT),
                ore(EDOreDictionary.CROP_CHORUS_FRUIT),
                "minecraft:brown_mushroom",
                id("chorus_succulent"),
                "ore:cropOnion"
            },
            new ItemStack(EDBlocks.STEAMED_DRAGON_EGG_BLOCK),
            new ItemStack(EDItems.HALF_DRAGON_EGG_SHELL),
            200,
            2.0F
        );

        CookingPotRecipeApi.registerRecipe(
            id("dragon_meat_stew_block"),
            new String[] {
                ore(EDOreDictionary.FOOD_RAW_DRAGON_MEAT),
                ore(EDOreDictionary.CROP_CHORUS_FRUIT),
                id("chorus_succulent"),
                "minecraft:carrot",
                "minecraft:potato",
                "ore:cropTomato"
            },
            new ItemStack(EDBlocks.DRAGON_MEAT_STEW_BLOCK),
            new ItemStack(EDItems.HALF_DRAGON_EGG_SHELL),
            200,
            2.0F
        );
    }

    private static void registerCampfireRecipes()
    {
        CampfireCookingRecipeManager.registerScriptRecipe(
            id("dried_chorus_flower_campfire"),
            new String[] { "minecraft:chorus_flower" },
            new ItemStack(EDItems.DRIED_CHORUS_FLOWER),
            600
        );
        CampfireCookingRecipeManager.registerScriptRecipe(
            id("fried_dragon_egg_campfire"),
            new String[] { id("liquid_dragon_egg") },
            new ItemStack(EDItems.FRIED_DRAGON_EGG),
            600
        );
        CampfireCookingRecipeManager.registerScriptRecipe(
            id("roasted_shulker_meat_campfire"),
            new String[] { id("shulker_meat") },
            new ItemStack(EDItems.ROASTED_SHULKER_MEAT),
            600
        );
        CampfireCookingRecipeManager.registerScriptRecipe(
            id("roasted_shulker_meat_slice_campfire"),
            new String[] { id("shulker_meat_slice") },
            new ItemStack(EDItems.ROASTED_SHULKER_MEAT_SLICE),
            600
        );
        CampfireCookingRecipeManager.registerScriptRecipe(
            id("dried_endermite_meat_campfire"),
            new String[] { id("raw_ender_mite_meat") },
            new ItemStack(EDItems.DRIED_ENDERMITE_MEAT),
            600
        );
        CampfireCookingRecipeManager.registerScriptRecipe(
            id("roasted_dragon_meat_campfire"),
            new String[] { id("raw_dragon_meat") },
            new ItemStack(EDItems.ROASTED_DRAGON_MEAT),
            600
        );
        CampfireCookingRecipeManager.registerScriptRecipe(
            id("roasted_dragon_meat_cuts_campfire"),
            new String[] { id("raw_dragon_meat_cuts") },
            new ItemStack(EDItems.ROASTED_DRAGON_MEAT_CUTS),
            600
        );
        CampfireCookingRecipeManager.registerScriptRecipe(
            id("smoked_dragon_leg_campfire"),
            new String[] { id("dragon_leg") },
            new ItemStack(EDItems.SMOKED_DRAGON_LEG),
            600
        );
        CampfireCookingRecipeManager.registerScriptRecipe(
            id("ender_sausage_campfire"),
            new String[] { id("raw_ender_sausage") },
            new ItemStack(EDItems.ENDER_SAUSAGE),
            600
        );
        CampfireCookingRecipeManager.registerScriptRecipe(
            id("shulker_omelette_campfire"),
            new String[] { id("shulker_omelette_mixture") },
            new ItemStack(EDItems.SHULKER_OMELETTE),
            600
        );
    }

    private static void registerHeatSources()
    {
        HeatSourceApi.registerDirectHeatSourcePredicate(
            id("end_stove"),
            (world, pos, state) -> state.getBlock() == EDBlocks.END_STOVE
                && state.getProperties().containsKey(BlockEndStove.LIT)
                && state.getValue(BlockEndStove.LIT)
        );
    }

    private static void registerFutureMcSmithingRecipes()
    {
        tryRegisterFutureMcSmithing();
    }

    private static boolean tryRegisterFutureMcSmithing()
    {
        if (!Loader.isModLoaded("futuremc")) {
            return false;
        }

        try {
            Class<?> recipesClass = Class.forName("thedarkcolour.futuremc.recipe.smithing.SmithingRecipes");
            Class<?> recipeClass = Class.forName("thedarkcolour.futuremc.recipe.smithing.SmithingRecipe");
            Field instanceField = recipesClass.getField("INSTANCE");
            Object instance = instanceField.get(null);
            Method getRecipes = recipesClass.getMethod("getRecipes");
            @SuppressWarnings("unchecked")
            List<Object> recipes = (List<Object>) getRecipes.invoke(instance);
            Constructor<?> ctor = recipeClass.getConstructor(Ingredient.class, Ingredient.class, ItemStack.class);
            Object recipe = ctor.newInstance(
                Ingredient.fromStacks(new ItemStack(EDItems.DRAGON_EGG_SHELL_KNIFE, 1, OreDictionary.WILDCARD_VALUE)),
                Ingredient.fromItem(EDItems.DRAGON_TOOTH),
                new ItemStack(EDItems.DRAGON_TOOTH_KNIFE)
            );
            recipes.add(recipe);
            return true;
        } catch (ReflectiveOperationException | LinkageError ignored) {
            return false;
        }
    }

    private static void registerDragonToothKnifeCrafting(IForgeRegistry<IRecipe> registry)
    {
        ResourceLocation id = new ResourceLocation(EndsDelightLegacy.MODID, "dragon_tooth_knife");
        if (registry.containsKey(id)) {
            return;
        }

        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.add(Ingredient.fromStacks(new ItemStack(EDItems.DRAGON_EGG_SHELL_KNIFE, 1, OreDictionary.WILDCARD_VALUE)));
        ingredients.add(Ingredient.fromItem(EDItems.DRAGON_TOOTH));
        ShapelessRecipes recipe = new ShapelessRecipes(EndsDelightLegacy.MODID, new ItemStack(EDItems.DRAGON_TOOTH_KNIFE), ingredients);
        recipe.setRegistryName(id);
        registry.register(recipe);
    }

    private static String id(String path)
    {
        return EndsDelightLegacy.MODID + ":" + path;
    }

    private static String ore(String name)
    {
        return "ore:" + name;
    }
}
