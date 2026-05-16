package xy177.endsdelightlegacy.common.item;

import com.wdcftgg.farmersdelightlegacy.api.food.AddonFoodItem;
import com.wdcftgg.farmersdelightlegacy.api.food.FoodItemApi;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemChorusFruitTeleportFood extends AddonFoodItem
{
    public ItemChorusFruitTeleportFood(FoodItemApi.FoodItemSettings settings)
    {
        super(settings);
    }

    @Override
    public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        super.onFoodEaten(stack, worldIn, player);
        if (player.isBurning()) {
            player.extinguish();
        }
        if (player.isSneaking()) {
            teleportLikeChorusFruit(worldIn, player);
        }
    }

    public static boolean teleportLikeChorusFruit(World world, EntityLivingBase entity)
    {
        if (world.isRemote) {
            return false;
        }

        double startX = entity.posX;
        double startY = entity.posY;
        double startZ = entity.posZ;
        boolean teleported = false;

        for (int i = 0; i < 16; ++i) {
            double targetX = startX + (entity.getRNG().nextDouble() - 0.5D) * 16.0D;
            double targetY = entity.posY + (double) (entity.getRNG().nextInt(16) - 8);
            double targetZ = startZ + (entity.getRNG().nextDouble() - 0.5D) * 16.0D;

            if (entity.isRiding()) {
                entity.dismountRidingEntity();
            }

            if (entity.attemptTeleport(targetX, targetY, targetZ)) {
                SoundCategory soundCategory = entity instanceof EntityPlayer ? SoundCategory.PLAYERS : SoundCategory.NEUTRAL;
                world.playSound(null, startX, startY, startZ, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, soundCategory, 1.0F, 1.0F);
                entity.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
                teleported = true;
                break;
            }
        }

        if (entity instanceof EntityPlayer) {
            ((EntityPlayer) entity).getCooldownTracker().setCooldown(entity.getHeldItemMainhand().getItem(), 20);
        }
        return teleported;
    }
}
