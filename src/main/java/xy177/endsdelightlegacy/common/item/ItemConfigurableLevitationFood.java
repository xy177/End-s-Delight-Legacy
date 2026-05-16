package xy177.endsdelightlegacy.common.item;

import java.util.ArrayList;
import java.util.List;

import com.wdcftgg.farmersdelightlegacy.api.food.AddonBowlFoodItem;
import com.wdcftgg.farmersdelightlegacy.api.food.AddonFoodItem;
import com.wdcftgg.farmersdelightlegacy.api.food.FoodItemApi;
import com.wdcftgg.farmersdelightlegacy.common.item.ItemFoodTooltip;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xy177.endsdelightlegacy.common.config.EDConfig;

public class ItemConfigurableLevitationFood extends AddonFoodItem
{
    private static final ResourceLocation LEVITATION = new ResourceLocation("minecraft", "levitation");

    private final List<ItemFoodTooltip.FoodEffectEntry> levitationEffects;

    public ItemConfigurableLevitationFood(FoodItemApi.FoodItemSettings settings)
    {
        super(withoutLevitation(settings));
        this.levitationEffects = getLevitationEffects(settings);
    }

    @Override
    public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        super.onFoodEaten(stack, worldIn, player);
        if (!EDConfig.levitationFoodsRequireSneaking || player.isSneaking()) {
            applyLevitationEffects(worldIn, player, this.levitationEffects);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        addLevitationTooltips(tooltip, this.levitationEffects);
    }

    protected static boolean isLevitationEffect(ItemFoodTooltip.FoodEffectEntry effect)
    {
        return effect != null && LEVITATION.equals(effect.getEffectId());
    }

    protected static FoodItemApi.FoodItemSettings withoutLevitation(FoodItemApi.FoodItemSettings settings)
    {
        FoodItemApi.FoodItemSettings value = settings == null ? FoodItemApi.FoodItemSettings.builder().build() : settings;
        List<ItemFoodTooltip.FoodEffectEntry> keptEffects = new ArrayList<>();
        for (ItemFoodTooltip.FoodEffectEntry effect : value.getFoodEffects()) {
            if (!isLevitationEffect(effect)) {
                keptEffects.add(effect);
            }
        }

        FoodItemApi.FoodItemSettings.Builder builder = FoodItemApi.FoodItemSettings.builder()
            .nutrition(value.getNutrition())
            .saturation(value.getSaturation())
            .effects(keptEffects)
            .extraTooltipKeys(value.getExtraTooltipKeys());
        if (value.isAlwaysEdible()) {
            builder.alwaysEdible();
        }
        if (value.hasBowlContainer()) {
            builder.bowlContainer();
        }
        if (value.getMaxStackSize() > 0) {
            builder.maxStackSize(value.getMaxStackSize());
        }
        return builder.build();
    }

    protected static List<ItemFoodTooltip.FoodEffectEntry> getLevitationEffects(FoodItemApi.FoodItemSettings settings)
    {
        List<ItemFoodTooltip.FoodEffectEntry> levitation = new ArrayList<>();
        FoodItemApi.FoodItemSettings value = settings == null ? FoodItemApi.FoodItemSettings.builder().build() : settings;
        for (ItemFoodTooltip.FoodEffectEntry effect : value.getFoodEffects()) {
            if (isLevitationEffect(effect)) {
                levitation.add(effect);
            }
        }
        return levitation;
    }

    protected static void applyLevitationEffects(World worldIn, EntityPlayer player, List<ItemFoodTooltip.FoodEffectEntry> effects)
    {
        if (worldIn.isRemote) {
            return;
        }

        for (ItemFoodTooltip.FoodEffectEntry effect : effects) {
            if (worldIn.rand.nextFloat() > effect.getChance()) {
                continue;
            }
            Potion potion = ForgeRegistries.POTIONS.getValue(effect.getEffectId());
            if (potion != null) {
                player.addPotionEffect(new PotionEffect(potion, effect.getDuration(), effect.getAmplifier()));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    protected static void addLevitationTooltips(List<String> tooltip, List<ItemFoodTooltip.FoodEffectEntry> effects)
    {
        for (ItemFoodTooltip.FoodEffectEntry effect : effects) {
            Potion potion = ForgeRegistries.POTIONS.getValue(effect.getEffectId());
            if (potion == null) {
                continue;
            }
            PotionEffect potionEffect = new PotionEffect(potion, effect.getDuration(), effect.getAmplifier());
            String durationText = Potion.getPotionDurationString(potionEffect, 1.0F);
            String effectName = new TextComponentTranslation(potionEffect.getEffectName()).getFormattedText();
            TextComponentTranslation effectTooltip = new TextComponentTranslation("farmersdelight.tooltip.food.effect", effectName, durationText);
            effectTooltip.getStyle().setColor(TextFormatting.BLUE);
            tooltip.add(effectTooltip.getFormattedText());
            if (effect.getChance() < 0.999F) {
                TextComponentTranslation translation = new TextComponentTranslation("farmersdelight.tooltip.food.effect_chance", Math.round(effect.getChance() * 100.0F));
                translation.getStyle().setColor(TextFormatting.BLUE);
                tooltip.add(translation.getFormattedText());
            }
        }
        if (EDConfig.levitationFoodsRequireSneaking && !effects.isEmpty()) {
            tooltip.add(TextFormatting.DARK_PURPLE + new TextComponentTranslation("farmersdelight.tooltip.levitation_requires_sneaking").getFormattedText());
        }
    }

    public static class Bowl extends AddonBowlFoodItem
    {
        private final List<ItemFoodTooltip.FoodEffectEntry> levitationEffects;

        public Bowl(FoodItemApi.FoodItemSettings settings)
        {
            super(withoutLevitation(settings));
            this.levitationEffects = getLevitationEffects(settings);
        }

        @Override
        public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
        {
            super.onFoodEaten(stack, worldIn, player);
            if (!EDConfig.levitationFoodsRequireSneaking || player.isSneaking()) {
                applyLevitationEffects(worldIn, player, this.levitationEffects);
            }
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
        {
            super.addInformation(stack, worldIn, tooltip, flagIn);
            addLevitationTooltips(tooltip, this.levitationEffects);
        }
    }
}
