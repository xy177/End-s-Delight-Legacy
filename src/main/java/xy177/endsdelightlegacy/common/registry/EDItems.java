package xy177.endsdelightlegacy.common.registry;

import java.util.ArrayList;
import java.util.List;

import com.wdcftgg.farmersdelightlegacy.api.food.FoodItemApi;
import com.wdcftgg.farmersdelightlegacy.api.food.AddonBowlFoodItem;
import com.wdcftgg.farmersdelightlegacy.api.food.AddonFoodItem;
import com.wdcftgg.farmersdelightlegacy.api.food.AddonDrinkItem;
import com.wdcftgg.farmersdelightlegacy.common.item.ItemDrinkableTooltip;
import com.wdcftgg.farmersdelightlegacy.common.item.ItemKnife;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import xy177.endsdelightlegacy.EndsDelightLegacy;
import xy177.endsdelightlegacy.common.item.ItemChorusFlowerTea;
import xy177.endsdelightlegacy.common.item.ItemChorusFlowerPie;
import xy177.endsdelightlegacy.common.item.ItemChorusFruitTeleportFood;
import xy177.endsdelightlegacy.common.item.ItemChorusFruitWine;
import xy177.endsdelightlegacy.common.item.ItemConfigurableLevitationFood;
import xy177.endsdelightlegacy.common.item.ItemContainerFood;
import xy177.endsdelightlegacy.common.item.ItemDragonBreathAndChorusSoup;
import xy177.endsdelightlegacy.common.item.ItemEndermanGristleFood;
import xy177.endsdelightlegacy.common.item.ItemEndermanGristleTooltipFood;
import xy177.endsdelightlegacy.common.item.ItemLiquidDragonEgg;
import xy177.endsdelightlegacy.common.item.ItemSneakChorusTeleportDrink;

public final class EDItems
{
    private static final List<Item> ITEMS = new ArrayList<>();

    public static final Item ENDER_PEARL_GRAIN = registerItem("ender_pearl_grain", new Item());
    public static final Item CHORUS_FRUIT_GRAIN = registerItem("chorus_fruit_grain", new AddonFoodItem(1, 0.6F, false));
    public static final Item CHORUS_COOKIE = registerItem("chorus_cookie", new AddonFoodItem(2, 0.6F, false));
    public static final Item CHORUS_FRUIT_POPSICLE = registerItem("chorus_fruit_popsicle", new ItemChorusFruitTeleportFood(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(3)
            .saturation(0.2F)
            .alwaysEdible()
            .maxStackSize(16)
            .extraTooltipKeys("farmersdelight.tooltip.chorus_fruit_popsicle")
            .build()
    ));
    public static final Item CHORUS_FRUIT_PIE_SLICE = registerItem("chorus_fruit_pie_slice", new ItemChorusFruitTeleportFood(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(3)
            .saturation(0.3F)
            .alwaysEdible()
            .maxStackSize(16)
            .extraTooltipKeys("farmersdelight.tooltip.chorus_shift_teleport")
            .build()
    ));
    public static final Item CHORUS_FLOWER_TEA = registerItem("chorus_flower_tea", new ItemChorusFlowerTea(
        FoodItemApi.DrinkItemSettings.builder()
            .nutrition(0)
            .saturation(0.0F)
            .alwaysEdible()
            .effect(new ResourceLocation("minecraft", "regeneration"), 100, 1, 1.0F)
            .effect(new ResourceLocation("minecraft", "resistance"), 400, 0, 1.0F)
            .drinkEffect(ItemDrinkableTooltip.DrinkEffect.NONE)
            .containerItem(new ItemStack(Items.GLASS_BOTTLE))
            .maxStackSize(16)
            .extraTooltipKeys("farmersdelight.tooltip.chorus_flower_tea")
            .build()
    ));
    public static final Item CHORUS_FRUIT_WINE = registerItem("chorus_fruit_wine", new ItemChorusFruitWine(
        FoodItemApi.DrinkItemSettings.builder()
            .nutrition(0)
            .saturation(0.0F)
            .alwaysEdible()
            .drinkEffect(ItemDrinkableTooltip.DrinkEffect.NONE)
            .containerItem(new ItemStack(Items.GLASS_BOTTLE))
            .maxStackSize(16)
            .extraTooltipKeys("farmersdelight.tooltip.chorus_fruit_wine")
            .build()
    ));
    public static final Item CHORUS_FRUIT_MILK_TEA = registerItem("chorus_fruit_milk_tea", new ItemSneakChorusTeleportDrink(
        FoodItemApi.DrinkItemSettings.builder()
            .nutrition(6)
            .saturation(0.5F)
            .alwaysEdible()
            .containerItem(new ItemStack(Items.GLASS_BOTTLE))
            .maxStackSize(16)
            .extraTooltipKeys("farmersdelight.tooltip.chorus_shift_teleport")
            .build()
    ));
    public static final Item BUBBLE_TEA = registerItem("bubble_tea", new ItemSneakChorusTeleportDrink(
        FoodItemApi.DrinkItemSettings.builder()
            .nutrition(8)
            .saturation(0.5F)
            .alwaysEdible()
            .containerItem(new ItemStack(Items.GLASS_BOTTLE))
            .maxStackSize(16)
            .extraTooltipKeys("farmersdelight.tooltip.chorus_shift_teleport")
            .build()
    ));
    public static final Item DRAGON_BREATH_SODA = registerItem("dragon_breath_soda", new AddonDrinkItem(
        FoodItemApi.DrinkItemSettings.builder()
            .nutrition(0)
            .saturation(0.0F)
            .alwaysEdible()
            .effect(new ResourceLocation("minecraft", "strength"), 1200, 1, 1.0F)
            .effect(new ResourceLocation("minecraft", "resistance"), 1200, 0, 1.0F)
            .containerItem(new ItemStack(Items.GLASS_BOTTLE))
            .maxStackSize(16)
            .build()
    ));
    public static final Item STUFFED_RICE_CAKE = registerItem("stuffed_rice_cake", new ItemChorusFruitTeleportFood(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(6)
            .saturation(0.7F)
            .alwaysEdible()
            .maxStackSize(16)
            .extraTooltipKeys("farmersdelight.tooltip.chorus_shift_teleport")
            .build()
    ));
    public static final Item CHORUS_SAUCE = registerItem("chorus_sauce", new AddonBowlFoodItem(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(3)
            .saturation(0.5F)
            .build()
    ));
    public static final Item CHORUS_FLOWER_PIE = registerItem("chorus_flower_pie", new ItemChorusFlowerPie(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(4)
            .saturation(0.6F)
            .effect(new ResourceLocation("minecraft", "regeneration"), 100, 0, 1.0F)
            .extraTooltipKeys("farmersdelight.tooltip.chorus_shift_teleport")
            .build()
    ));
    public static final Item DRIED_CHORUS_FLOWER = registerItem("dried_chorus_flower", new Item());
    public static final Item DRAGON_TOOTH = registerItem("dragon_tooth", new Item());
    public static final Item END_STONE_KNIFE = registerItem("end_stone_knife", new ItemKnife(EDToolMaterials.END_STONE, 1.5D));
    public static final Item PURPUR_KNIFE = registerItem("purpur_knife", new ItemKnife(EDToolMaterials.PURPUR, 1.5D));
    public static final Item NON_HATCHABLE_DRAGON_EGG = registerItem("non_hatchable_dragon_egg", new Item());
    public static final Item HALF_DRAGON_EGG_SHELL = registerItem("half_dragon_egg_shell", new Item());
    public static final Item DRAGON_EGG_SHELL_KNIFE = registerItem("dragon_egg_shell_knife", new ItemKnife(EDToolMaterials.DRAGON_EGG_SHELL, 3.0D));
    public static final Item DRAGON_TOOTH_KNIFE = registerItem("dragon_tooth_knife", new ItemKnife(EDToolMaterials.DRAGON_TOOTH, 4.0D));
    public static final Item LIQUID_DRAGON_EGG = registerItem("liquid_dragon_egg", new ItemLiquidDragonEgg(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(14)
            .saturation(0.5F)
            .alwaysEdible()
            .effect(new ResourceLocation("minecraft", "regeneration"), 1200, 0, 1.0F)
            .effect(new ResourceLocation("minecraft", "strength"), 1200, 0, 1.0F)
            .effect(new ResourceLocation("minecraft", "resistance"), 1200, 0, 1.0F)
            .effect(new ResourceLocation("minecraft", "blindness"), 400, 0, 1.0F)
            .build()
    ));
    public static final Item FRIED_DRAGON_EGG = registerItem("fried_dragon_egg", new AddonFoodItem(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(20)
            .saturation(0.5F)
            .alwaysEdible()
            .effect(new ResourceLocation("minecraft", "regeneration"), 4800, 2, 1.0F)
            .effect(new ResourceLocation("minecraft", "strength"), 4800, 2, 1.0F)
            .effect(new ResourceLocation("minecraft", "resistance"), 4800, 2, 1.0F)
            .build()
    ));
    public static final Item END_MIXED_SALAD = registerItem("end_mixed_salad", new AddonFoodItem(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(10)
            .saturation(0.6F)
            .bowlContainer()
            .effect(new ResourceLocation("minecraft", "regeneration"), 120, 0, 1.0F)
            .effect(new ResourceLocation("minecraft", "speed"), 200, 0, 1.0F)
            .maxStackSize(16)
            .build()
    ));
    public static final Item SHULKER_MEAT = registerItem("shulker_meat", new ItemConfigurableLevitationFood(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(4)
            .saturation(0.5F)
            .alwaysEdible()
            .effect(new ResourceLocation("minecraft", "levitation"), 80, 1, 1.0F)
            .build()
    ));
    public static final Item SHULKER_MEAT_SLICE = registerItem("shulker_meat_slice", new ItemConfigurableLevitationFood(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(2)
            .saturation(0.6F)
            .alwaysEdible()
            .effect(new ResourceLocation("minecraft", "levitation"), 40, 1, 1.0F)
            .build()
    ));
    public static final Item ROASTED_SHULKER_MEAT = registerItem("roasted_shulker_meat", new ItemConfigurableLevitationFood(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(6)
            .saturation(0.5F)
            .alwaysEdible()
            .effect(new ResourceLocation("minecraft", "levitation"), 80, 1, 1.0F)
            .build()
    ));
    public static final Item ROASTED_SHULKER_MEAT_SLICE = registerItem("roasted_shulker_meat_slice", new ItemConfigurableLevitationFood(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(3)
            .saturation(0.8F)
            .alwaysEdible()
            .effect(new ResourceLocation("minecraft", "levitation"), 40, 1, 1.0F)
            .build()
    ));
    public static final Item RAW_ENDER_MITE_MEAT = registerItem("raw_ender_mite_meat", new AddonFoodItem(3, 0.6F, false));
    public static final Item DRIED_ENDERMITE_MEAT = registerItem("dried_endermite_meat", new AddonFoodItem(5, 0.6F, false));
    public static final Item RAW_DRAGON_MEAT = registerItem("raw_dragon_meat", new AddonFoodItem(8, 0.6F, false));
    public static final Item ROASTED_DRAGON_MEAT = registerItem("roasted_dragon_meat", new AddonFoodItem(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(15)
            .saturation(0.6F)
            .effect(new ResourceLocation("minecraft", "regeneration"), 300, 1, 1.0F)
            .build()
    ));
    public static final Item RAW_DRAGON_MEAT_CUTS = registerItem("raw_dragon_meat_cuts", new AddonFoodItem(3, 0.6F, false));
    public static final Item ROASTED_DRAGON_MEAT_CUTS = registerItem("roasted_dragon_meat_cuts", new AddonFoodItem(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(6)
            .saturation(0.6F)
            .effect(new ResourceLocation("minecraft", "regeneration"), 100, 1, 1.0F)
            .build()
    ));
    public static final Item DRAGON_LEG = registerItem("dragon_leg", new AddonFoodItem(8, 0.6F, false));
    public static final Item SMOKED_DRAGON_LEG = registerItem("smoked_dragon_leg", new AddonFoodItem(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(15)
            .saturation(0.6F)
            .effect(new ResourceLocation("minecraft", "regeneration"), 300, 1, 1.0F)
            .build()
    ));
    public static final Item ENDERMAN_GRISTLE = registerItem("enderman_gristle", new ItemEndermanGristleTooltipFood(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(2)
            .saturation(0.8F)
            .alwaysEdible()
            .build(),
        0.3F
    ));
    public static final Item RAW_ENDER_SAUSAGE = registerItem("raw_ender_sausage", new ItemEndermanGristleTooltipFood(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(6)
            .saturation(0.6F)
            .alwaysEdible()
            .build(),
        0.3F
    ));
    public static final Item ENDER_SAUSAGE = registerItem("ender_sausage", new ItemEndermanGristleTooltipFood(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(9)
            .saturation(0.6F)
            .alwaysEdible()
            .effect(new ResourceLocation("minecraft", "regeneration"), 160, 1, 1.0F)
            .build(),
        0.2F
    ));
    public static final Item SHULKER_OMELETTE_MIXTURE = registerItem("shulker_omelette_mixture", new ItemConfigurableLevitationFood(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(5)
            .saturation(0.5F)
            .alwaysEdible()
            .effect(new ResourceLocation("minecraft", "levitation"), 100, 1, 1.0F)
            .build()
    ));
    public static final Item SHULKER_OMELETTE = registerItem("shulker_omelette", new ItemConfigurableLevitationFood(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(11)
            .saturation(0.6F)
            .alwaysEdible()
            .effect(new ResourceLocation("minecraft", "levitation"), 100, 1, 1.0F)
            .effect(new ResourceLocation("minecraft", "regeneration"), 100, 0, 1.0F)
            .build()
    ));
    public static final Item STIR_FRIED_SHULKER_MEAT = registerItem("stir_fried_shulker_meat", new ItemConfigurableLevitationFood.Bowl(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(10)
            .saturation(0.8F)
            .alwaysEdible()
            .effect(new ResourceLocation("minecraft", "levitation"), 100, 1, 1.0F)
            .effect(new ResourceLocation("farmersdelight", "nourishment"), 3600, 0, 1.0F)
            .maxStackSize(16)
            .build()
    ));
    public static final Item END_BARBECUE_STICK = registerItem("end_barbecue_stick", new AddonFoodItem(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(10)
            .saturation(0.7F)
            .alwaysEdible()
            .effect(new ResourceLocation("minecraft", "regeneration"), 100, 1, 1.0F)
            .build()
    ));
    public static final Item ENDER_CONGEE = registerItem("ender_congee", new AddonBowlFoodItem(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(10)
            .saturation(0.6F)
            .alwaysEdible()
            .effect(new ResourceLocation("minecraft", "regeneration"), 300, 1, 1.0F)
            .effect(new ResourceLocation("minecraft", "resistance"), 900, 1, 1.0F)
            .effect(new ResourceLocation("minecraft", "health_boost"), 900, 2, 1.0F)
            .effect(new ResourceLocation("farmersdelight", "comfort"), 3600, 0, 1.0F)
            .maxStackSize(16)
            .build()
    ));
    public static final Item SHULKER_SOUP = registerItem("shulker_soup", new ItemConfigurableLevitationFood.Bowl(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(8)
            .saturation(0.6F)
            .alwaysEdible()
            .effect(new ResourceLocation("minecraft", "levitation"), 100, 1, 1.0F)
            .effect(new ResourceLocation("farmersdelight", "comfort"), 3600, 0, 1.0F)
            .maxStackSize(16)
            .build()
    ));
    public static final Item ENDER_NOODLE = registerItem("ender_noodle", new ItemConfigurableLevitationFood.Bowl(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(8)
            .saturation(0.6F)
            .alwaysEdible()
            .effect(new ResourceLocation("minecraft", "levitation"), 50, 1, 1.0F)
            .effect(new ResourceLocation("farmersdelight", "comfort"), 3600, 0, 1.0F)
            .maxStackSize(16)
            .build()
    ));
    public static final Item ROASTED_DRAGON_STEAK = registerItem("roasted_dragon_steak", new AddonBowlFoodItem(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(18)
            .saturation(0.5F)
            .alwaysEdible()
            .effect(new ResourceLocation("minecraft", "regeneration"), 500, 1, 1.0F)
            .effect(new ResourceLocation("farmersdelight", "nourishment"), 6000, 0, 1.0F)
            .maxStackSize(16)
            .build()
    ));
    public static final Item ASSORTED_SALAD = registerItem("assorted_salad", new ItemContainerFood(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(14)
            .saturation(0.5F)
            .effect(new ResourceLocation("minecraft", "regeneration"), 200, 0, 1.0F)
            .effect(new ResourceLocation("minecraft", "speed"), 500, 0, 1.0F)
            .effect(new ResourceLocation("minecraft", "strength"), 500, 0, 1.0F)
            .maxStackSize(16)
            .build(),
        new ItemStack(Items.SHULKER_SHELL)
    ));
    public static final Item ENDER_BAMBOO_RICE = registerItem("ender_bamboo_rice", new AddonFoodItem(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(15)
            .saturation(0.6F)
            .effect(new ResourceLocation("minecraft", "regeneration"), 300, 1, 1.0F)
            .effect(new ResourceLocation("farmersdelight", "nourishment"), 3600, 0, 1.0F)
            .build()
    ));
    public static final Item DRAGON_BREATH_AND_CHORUS_SOUP = registerItem("dragon_breath_and_chorus_soup", new ItemDragonBreathAndChorusSoup(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(16)
            .saturation(0.6F)
            .bowlContainer()
            .alwaysEdible()
            .effect(new ResourceLocation("minecraft", "regeneration"), 400, 1, 1.0F)
            .effect(new ResourceLocation("minecraft", "strength"), 1200, 1, 1.0F)
            .effect(new ResourceLocation("minecraft", "resistance"), 1200, 0, 1.0F)
            .effect(new ResourceLocation("farmersdelight", "comfort"), 3600, 0, 1.0F)
            .maxStackSize(16)
            .extraTooltipKeys("farmersdelight.tooltip.dragon_breath_and_chorus_soup")
            .build()
    ));
    public static final Item ENDERMAN_GRISTLE_STEW = registerItem("enderman_gristle_stew", new ItemEndermanGristleTooltipFood(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(7)
            .saturation(0.7F)
            .alwaysEdible()
            .effect(new ResourceLocation("farmersdelight", "nourishment"), 3600, 0, 1.0F)
            .bowlContainer()
            .maxStackSize(16)
            .build(),
        0.2F
    ));
    public static final Item DRAGON_LEG_WITH_SAUCE = registerItem("dragon_leg_with_sauce", new AddonBowlFoodItem(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(10)
            .saturation(0.7F)
            .effect(new ResourceLocation("minecraft", "absorption"), 1500, 2, 1.0F)
            .effect(new ResourceLocation("minecraft", "regeneration"), 300, 1, 1.0F)
            .effect(new ResourceLocation("farmersdelight", "nourishment"), 6000, 0, 1.0F)
            .maxStackSize(16)
            .build()
    ));
    public static final Item STEAMED_DRAGON_EGG = registerItem("steamed_dragon_egg", new AddonBowlFoodItem(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(12)
            .saturation(0.6F)
            .effect(new ResourceLocation("minecraft", "absorption"), 1200, 2, 1.0F)
            .effect(new ResourceLocation("farmersdelight", "nourishment"), 6000, 0, 1.0F)
            .effect(new ResourceLocation("minecraft", "regeneration"), 1200, 2, 1.0F)
            .effect(new ResourceLocation("minecraft", "strength"), 1200, 2, 1.0F)
            .effect(new ResourceLocation("minecraft", "resistance"), 1200, 2, 1.0F)
            .maxStackSize(16)
            .build()
    ));
    public static final Item DRAGON_MEAT_STEW = registerItem("dragon_meat_stew", new AddonBowlFoodItem(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(10)
            .saturation(0.6F)
            .effect(new ResourceLocation("minecraft", "absorption"), 1500, 2, 1.0F)
            .effect(new ResourceLocation("minecraft", "regeneration"), 300, 1, 1.0F)
            .effect(new ResourceLocation("minecraft", "strength"), 600, 1, 1.0F)
            .effect(new ResourceLocation("farmersdelight", "nourishment"), 6000, 0, 1.0F)
            .maxStackSize(16)
            .build()
    ));
    public static final Item GRILLED_SHULKER = registerItem("grilled_shulker", new ItemConfigurableLevitationFood.Bowl(
        FoodItemApi.FoodItemSettings.builder()
            .nutrition(8)
            .saturation(0.6F)
            .effect(new ResourceLocation("minecraft", "regeneration"), 100, 0, 1.0F)
            .effect(new ResourceLocation("minecraft", "levitation"), 40, 1, 1.0F)
            .effect(new ResourceLocation("farmersdelight", "nourishment"), 6000, 0, 1.0F)
            .maxStackSize(16)
            .build()
    ));

    private EDItems()
    {
    }

    public static void register(IForgeRegistry<Item> registry)
    {
        EDToolMaterials.initRepairItems();
        for (Item item : ITEMS) {
            registry.register(item);
        }
    }

    private static Item registerItem(String name, Item item)
    {
        item.setRegistryName(new ResourceLocation(EndsDelightLegacy.MODID, name));
        item.setUnlocalizedName(EndsDelightLegacy.MODID + "." + name);
        item.setCreativeTab(EndsDelightLegacy.CREATIVE_TAB);
        ITEMS.add(item);
        return item;
    }
}
