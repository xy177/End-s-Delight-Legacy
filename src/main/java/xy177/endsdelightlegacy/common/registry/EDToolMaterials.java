package xy177.endsdelightlegacy.common.registry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public final class EDToolMaterials
{
    public static final Item.ToolMaterial END_STONE = EnumHelper.addToolMaterial("ENDS_DELIGHT_END_STONE", 1, 200, 4.0F, 1.0F, 5);
    public static final Item.ToolMaterial PURPUR = EnumHelper.addToolMaterial("ENDS_DELIGHT_PURPUR", 1, 200, 4.0F, 1.0F, 5);
    public static final Item.ToolMaterial DRAGON_EGG_SHELL = EnumHelper.addToolMaterial("ENDS_DELIGHT_DRAGON_EGG_SHELL", 2, 1250, 6.0F, 2.5F, 14);
    public static final Item.ToolMaterial DRAGON_TOOTH = EnumHelper.addToolMaterial("ENDS_DELIGHT_DRAGON_TOOTH", 3, 1561, 8.0F, 3.5F, 10);
    private static boolean repairItemsInitialized;

    private EDToolMaterials()
    {
    }

    public static void initRepairItems()
    {
        if (repairItemsInitialized) {
            return;
        }
        repairItemsInitialized = true;

        END_STONE.setRepairItem(new ItemStack(Blocks.END_STONE));
        PURPUR.setRepairItem(new ItemStack(Items.CHORUS_FRUIT_POPPED));
        DRAGON_EGG_SHELL.setRepairItem(new ItemStack(EDItems.HALF_DRAGON_EGG_SHELL));
        DRAGON_TOOTH.setRepairItem(new ItemStack(EDItems.DRAGON_TOOTH));
    }
}
