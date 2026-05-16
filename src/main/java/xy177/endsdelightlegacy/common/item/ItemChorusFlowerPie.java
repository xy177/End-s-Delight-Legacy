package xy177.endsdelightlegacy.common.item;

import com.wdcftgg.farmersdelightlegacy.api.food.AddonFoodItem;
import com.wdcftgg.farmersdelightlegacy.api.food.FoodItemApi;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemChorusFlowerPie extends AddonFoodItem
{
    public ItemChorusFlowerPie(FoodItemApi.FoodItemSettings settings)
    {
        super(settings);
    }

    @Override
    public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        super.onFoodEaten(stack, worldIn, player);
        if (player.isSneaking()) {
            ItemChorusFruitTeleportFood.teleportLikeChorusFruit(worldIn, player);
        }
    }
}
