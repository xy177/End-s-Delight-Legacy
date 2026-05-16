package xy177.endsdelightlegacy.common.item;

import com.wdcftgg.farmersdelightlegacy.api.food.AddonDrinkItem;
import com.wdcftgg.farmersdelightlegacy.api.food.FoodItemApi;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ItemChorusFruitWine extends AddonDrinkItem
{
    public ItemChorusFruitWine(FoodItemApi.DrinkItemSettings settings)
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
        clearOneHarmfulMilkCurableEffect(player, worldIn);
    }

    private static void clearOneHarmfulMilkCurableEffect(EntityPlayer player, World worldIn)
    {
        List<Potion> removable = new ArrayList<>();
        for (PotionEffect effect : player.getActivePotionEffects()) {
            if (!effect.getPotion().isBadEffect()) {
                continue;
            }
            boolean curableByMilk = effect.getCurativeItems().stream().anyMatch(curative -> curative.getItem() == Items.MILK_BUCKET);
            if (curableByMilk) {
                removable.add(effect.getPotion());
            }
        }

        if (!removable.isEmpty()) {
            Potion selected = removable.get(worldIn.rand.nextInt(removable.size()));
            player.removePotionEffect(selected);
        }
    }
}
