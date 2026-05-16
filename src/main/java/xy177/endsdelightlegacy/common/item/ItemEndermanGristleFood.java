package xy177.endsdelightlegacy.common.item;

import com.wdcftgg.farmersdelightlegacy.api.food.AddonFoodItem;
import com.wdcftgg.farmersdelightlegacy.api.food.FoodItemApi;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import xy177.endsdelightlegacy.common.config.EDConfig;

public class ItemEndermanGristleFood extends AddonFoodItem
{
    private static final double FORWARD_TELEPORT_DISTANCE = 8.0D;

    private final float damageRatio;

    public ItemEndermanGristleFood(FoodItemApi.FoodItemSettings settings, float damageRatio)
    {
        super(settings);
        this.damageRatio = damageRatio;
    }

    @Override
    public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        super.onFoodEaten(stack, worldIn, player);
        if (!player.isSneaking()) {
            return;
        }
        if (EDConfig.endermanGristleUsesRandomTeleport) {
            ItemChorusFruitTeleportFood.teleportLikeChorusFruit(worldIn, player);
            return;
        }
        if (this.teleportForward(worldIn, player)) {
            float damage = player.getHealth() < player.getMaxHealth() * 0.3F
                ? player.getHealth() * 1.5F
                : player.getHealth() * this.damageRatio;
            player.attackEntityFrom(DamageSource.FALL, damage);
        }
    }

    private boolean teleportForward(World world, EntityPlayer player)
    {
        if (world.isRemote) {
            return false;
        }

        double startX = player.posX;
        double startY = player.posY;
        double startZ = player.posZ;
        double yaw = Math.toRadians(player.rotationYaw);
        double targetX = startX - Math.sin(yaw) * FORWARD_TELEPORT_DISTANCE;
        double targetZ = startZ + Math.cos(yaw) * FORWARD_TELEPORT_DISTANCE;

        if (player.isRiding()) {
            player.dismountRidingEntity();
        }

        boolean teleported = player.attemptTeleport(targetX, startY, targetZ);
        if (teleported) {
            world.playSound(null, startX, startY, startZ, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
            player.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
            player.fallDistance = 0.0F;
        }
        player.getCooldownTracker().setCooldown(this, 20);
        return teleported;
    }
}
