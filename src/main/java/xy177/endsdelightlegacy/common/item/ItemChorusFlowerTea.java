package xy177.endsdelightlegacy.common.item;

import com.wdcftgg.farmersdelightlegacy.api.food.AddonDrinkItem;
import com.wdcftgg.farmersdelightlegacy.api.food.FoodItemApi;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemChorusFlowerTea extends AddonDrinkItem
{
    public ItemChorusFlowerTea(FoodItemApi.DrinkItemSettings settings)
    {
        super(settings);
    }

    @Override
    public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        player.removePotionEffect(MobEffects.LEVITATION);
        super.onFoodEaten(stack, worldIn, player);
    }
}
