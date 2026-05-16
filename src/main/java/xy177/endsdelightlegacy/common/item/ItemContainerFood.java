package xy177.endsdelightlegacy.common.item;

import com.wdcftgg.farmersdelightlegacy.api.food.AddonFoodItem;
import com.wdcftgg.farmersdelightlegacy.api.food.FoodItemApi;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemContainerFood extends AddonFoodItem
{
    private final ItemStack container;

    public ItemContainerFood(FoodItemApi.FoodItemSettings settings, ItemStack container)
    {
        super(settings);
        this.container = container == null ? ItemStack.EMPTY : container.copy();
    }

    @Override
    public boolean hasContainerItem(ItemStack stack)
    {
        return !this.container.isEmpty();
    }

    @Override
    public ItemStack getContainerItem(ItemStack stack)
    {
        return this.container.copy();
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        ItemStack result = super.onItemUseFinish(stack, worldIn, entityLiving);
        if (this.container.isEmpty() || !(entityLiving instanceof EntityPlayer)) {
            return result;
        }

        EntityPlayer player = (EntityPlayer) entityLiving;
        if (player.capabilities.isCreativeMode) {
            return result;
        }

        ItemStack containerStack = this.container.copy();
        if (result.isEmpty()) {
            return containerStack;
        }

        if (!player.inventory.addItemStackToInventory(containerStack)) {
            player.dropItem(containerStack, false);
        }
        return result;
    }
}
