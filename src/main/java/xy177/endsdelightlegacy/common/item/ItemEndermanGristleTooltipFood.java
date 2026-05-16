package xy177.endsdelightlegacy.common.item;

import java.util.List;

import com.wdcftgg.farmersdelightlegacy.api.food.FoodItemApi;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xy177.endsdelightlegacy.common.config.EDConfig;

public class ItemEndermanGristleTooltipFood extends ItemEndermanGristleFood
{
    public ItemEndermanGristleTooltipFood(FoodItemApi.FoodItemSettings settings, float damageRatio)
    {
        super(settings, damageRatio);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        String key = EDConfig.endermanGristleUsesRandomTeleport
            ? "farmersdelight.tooltip.enderman_gristle.random"
            : "farmersdelight.tooltip.enderman_gristle.forward";
        tooltip.add(TextFormatting.DARK_PURPLE + new TextComponentTranslation(key).getFormattedText());
    }
}
