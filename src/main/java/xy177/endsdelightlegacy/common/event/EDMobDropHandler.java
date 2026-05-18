package xy177.endsdelightlegacy.common.event;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xy177.endsdelightlegacy.EndsDelightLegacy;
import xy177.endsdelightlegacy.common.registry.EDItems;

@Mod.EventBusSubscriber(modid = EndsDelightLegacy.MODID)
public final class EDMobDropHandler
{
    private EDMobDropHandler()
    {
    }

    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event)
    {
        EntityLivingBase entity = event.getEntityLiving();
        World world = entity.world;
        if (world.isRemote) {
            return;
        }

        if (entity instanceof EntityDragon) {
            addDragonDrops(event);
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event)
    {
        if (!isDragonToothKnifeTarget(event.getEntityLiving())) {
            return;
        }

        EntityLivingBase attacker = getLivingAttacker(event.getSource());
        if (attacker != null && isDragonToothWeapon(attacker.getHeldItemMainhand())) {
            event.setAmount(event.getAmount() * 3.5F);
        }
    }

    private static void addDragonDrops(LivingDropsEvent event)
    {
        EntityLivingBase dragon = event.getEntityLiving();
        addDrop(event, EDItems.DRAGON_LEG, 2);
        addDrop(event, EDItems.RAW_DRAGON_MEAT, 5 + bonusCount(dragon));
        addDrop(event, EDItems.DRAGON_TOOTH, 3 + bonusCount(dragon));
    }

    private static int bonusCount(EntityLivingBase entity)
    {
        int count = 0;
        if (entity.getRNG().nextFloat() > 0.25F) {
            count++;
        }
        if (entity.getRNG().nextFloat() > 0.5F) {
            count++;
        }
        return count;
    }

    private static EntityLivingBase getLivingAttacker(DamageSource source)
    {
        if (source == null || !(source.getTrueSource() instanceof EntityLivingBase)) {
            return null;
        }
        return (EntityLivingBase) source.getTrueSource();
    }

    private static boolean isDragonToothKnifeTarget(EntityLivingBase entity)
    {
        return entity instanceof EntityEnderman
            || entity instanceof EntityEndermite
            || entity instanceof EntityShulker
            || entity instanceof EntityDragon;
    }

    private static boolean isDragonToothWeapon(ItemStack stack)
    {
        Item item = stack.getItem();
        return item == EDItems.DRAGON_TOOTH_KNIFE || item == EDItems.DRAGON_TOOTH_MACHETE;
    }

    private static void addDrop(LivingDropsEvent event, Item item, int count)
    {
        if (item == null || count <= 0) {
            return;
        }

        EntityLivingBase entity = event.getEntityLiving();
        event.getDrops().add(new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ, new ItemStack(item, count)));
    }
}
