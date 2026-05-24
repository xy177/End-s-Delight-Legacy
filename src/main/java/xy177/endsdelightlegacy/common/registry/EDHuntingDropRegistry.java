package xy177.endsdelightlegacy.common.registry;

import com.google.common.collect.ImmutableList;
import com.wdcftgg.farmersdelightlegacy.api.recipe.knife.HuntingDropOutput;
import com.wdcftgg.farmersdelightlegacy.api.recipe.knife.HuntingDropRecipeApi;
import com.wdcftgg.farmersdelightlegacy.common.recipe.HuntingDropRecipeManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import xy177.endsdelightlegacy.EndsDelightLegacy;
import xy177.endsdelightlegacy.common.config.EDConfig;

public final class EDHuntingDropRegistry
{
    private EDHuntingDropRegistry()
    {
    }

    public static void register()
    {
        registerJeiOnly("ender_dragon_base", EntityDragon.class, vanilla("ender_dragon"), ImmutableList.of(
            output(EDItems.DRAGON_LEG, 2, 1.0F),
            output(EDItems.RAW_DRAGON_MEAT, 5, 1.0F),
            output(EDItems.RAW_DRAGON_MEAT, 1, 0.75F),
            output(EDItems.RAW_DRAGON_MEAT, 1, 0.5F),
            output(EDItems.DRAGON_TOOTH, 3, 1.0F),
            output(EDItems.DRAGON_TOOTH, 1, 0.75F),
            output(EDItems.DRAGON_TOOTH, 1, 0.5F)
        ), "ends_delight.jei.mob_drops.any_kill");

        register("ender_dragon_knife", EntityDragon.class, vanilla("ender_dragon"), ImmutableList.of(
            output(EDItems.RAW_DRAGON_MEAT, 2, 1.0F),
            output(EDItems.DRAGON_TOOTH, 1, 1.0F)
        ), "ends_delight.jei.mob_drops.dragon_knife_meat", "ends_delight.jei.mob_drops.dragon_knife_tooth");

        register("shulker_meat", EntityShulker.class, vanilla("shulker"), ImmutableList.of(
            output(EDItems.SHULKER_MEAT, 1, EDConfig.shulkerMeatDropChance)
        ), "ends_delight.jei.mob_drops.shulker_meat");

        register("enderman_gristle", EntityEnderman.class, vanilla("enderman"), ImmutableList.of(
            output(EDItems.ENDERMAN_GRISTLE, 1, EDConfig.endermanGristleDropChance)
        ), "ends_delight.jei.mob_drops.enderman_gristle");

        registerEndermite("endermite_meat", EDItems.RAW_ENDER_MITE_MEAT, false);
        registerEndermite("dried_endermite_meat", EDItems.DRIED_ENDERMITE_MEAT, true);
    }

    private static void register(String key, Class<? extends EntityLivingBase> entityClass, ResourceLocation entityId, java.util.List<HuntingDropOutput> outputs, String... displayTexts)
    {
        HuntingDropRecipeApi.registerRecipeAdvance(
            EndsDelightLegacy.MODID + ":" + key,
            matcher(entityClass),
            outputs,
            false,
            entityId,
            null,
            false,
            ImmutableList.copyOf(displayTexts)
        );
    }

    private static void registerJeiOnly(String key, Class<? extends EntityLivingBase> entityClass, ResourceLocation entityId, java.util.List<HuntingDropOutput> outputs, String... displayTexts)
    {
        HuntingDropRecipeApi.registerRecipeAdvanceJei(
            EndsDelightLegacy.MODID + ":" + key,
            matcher(entityClass),
            outputs,
            false,
            entityId,
            null,
            false,
            ImmutableList.copyOf(displayTexts)
        );
    }

    private static void registerEndermite(String key, Item item, boolean burningRequired)
    {
        HuntingDropRecipeApi.registerRecipe(
            EndsDelightLegacy.MODID + ":" + key,
            matcher(EntityEndermite.class),
            ImmutableList.of(
                output(item, 2, 1.0F),
                output(item, 1, 0.75F),
                output(item, 1, 0.5F)
            ),
            burningRequired,
            false,
            vanilla("endermite")
        );
    }

    private static HuntingDropRecipeManager.HuntingTargetMatcher matcher(Class<? extends EntityLivingBase> entityClass)
    {
        return entityClass::isInstance;
    }

    private static HuntingDropOutput output(Item item, int count, float chance)
    {
        return output(item, count, chance, 0.0F);
    }

    private static HuntingDropOutput output(Item item, int count, float chance, float lootingBonus)
    {
        return HuntingDropOutput.of(new ItemStack(item, count), chance, lootingBonus);
    }

    private static ResourceLocation vanilla(String path)
    {
        return new ResourceLocation("minecraft", path);
    }
}
