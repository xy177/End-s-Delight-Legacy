package xy177.endsdelightlegacy.client;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xy177.endsdelightlegacy.common.registry.EDBlocks;
import xy177.endsdelightlegacy.common.registry.EDItems;

public class ClientRegistryHandler
{
    @SubscribeEvent
    public void onModelRegistry(ModelRegistryEvent event)
    {
        registerItemModel(Item.getItemFromBlock(EDBlocks.END_STOVE));
        registerItemModel(EDItems.ENDER_PEARL_GRAIN);
        registerItemModel(EDItems.CHORUS_FRUIT_GRAIN);
        registerItemModel(EDItems.CHORUS_COOKIE);
        registerItemModel(Item.getItemFromBlock(EDBlocks.CHORUS_SUCCULENT));
        registerItemModel(EDItems.CHORUS_FRUIT_POPSICLE);
        registerItemModel(Item.getItemFromBlock(EDBlocks.CHORUS_FRUIT_PIE));
        registerItemModel(EDItems.CHORUS_FRUIT_PIE_SLICE);
        registerItemModel(EDItems.CHORUS_FLOWER_TEA);
        registerItemModel(EDItems.CHORUS_FRUIT_WINE);
        registerItemModel(EDItems.CHORUS_FRUIT_MILK_TEA);
        registerItemModel(EDItems.BUBBLE_TEA);
        registerItemModel(EDItems.DRAGON_BREATH_SODA);
        registerItemModel(EDItems.STUFFED_RICE_CAKE);
        registerItemModel(EDItems.CHORUS_SAUCE);
        registerItemModel(EDItems.CHORUS_FLOWER_PIE);
        registerItemModel(EDItems.DRIED_CHORUS_FLOWER);
        registerItemModel(EDItems.DRAGON_TOOTH);
        registerItemModel(EDItems.END_STONE_KNIFE);
        registerItemModel(EDItems.PURPUR_KNIFE);
        registerItemModel(EDItems.NON_HATCHABLE_DRAGON_EGG);
        registerItemModel(EDItems.HALF_DRAGON_EGG_SHELL);
        registerItemModel(EDItems.DRAGON_EGG_SHELL_KNIFE);
        registerItemModel(EDItems.DRAGON_TOOTH_KNIFE);
        registerItemModel(EDItems.END_STONE_MACHETE);
        registerItemModel(EDItems.PURPUR_MACHETE);
        registerItemModel(EDItems.DRAGON_EGG_SHELL_MACHETE);
        registerItemModel(EDItems.DRAGON_TOOTH_MACHETE);
        registerItemModel(EDItems.LIQUID_DRAGON_EGG);
        registerItemModel(EDItems.FRIED_DRAGON_EGG);
        registerItemModel(EDItems.END_MIXED_SALAD);
        registerItemModel(EDItems.SHULKER_MEAT);
        registerItemModel(EDItems.SHULKER_MEAT_SLICE);
        registerItemModel(EDItems.ROASTED_SHULKER_MEAT);
        registerItemModel(EDItems.ROASTED_SHULKER_MEAT_SLICE);
        registerItemModel(EDItems.RAW_ENDER_MITE_MEAT);
        registerItemModel(EDItems.DRIED_ENDERMITE_MEAT);
        registerItemModel(EDItems.RAW_DRAGON_MEAT);
        registerItemModel(EDItems.ROASTED_DRAGON_MEAT);
        registerItemModel(EDItems.RAW_DRAGON_MEAT_CUTS);
        registerItemModel(EDItems.ROASTED_DRAGON_MEAT_CUTS);
        registerItemModel(EDItems.DRAGON_LEG);
        registerItemModel(EDItems.SMOKED_DRAGON_LEG);
        registerItemModel(EDItems.ENDERMAN_GRISTLE);
        registerItemModel(EDItems.RAW_ENDER_SAUSAGE);
        registerItemModel(EDItems.ENDER_SAUSAGE);
        registerItemModel(EDItems.SHULKER_OMELETTE_MIXTURE);
        registerItemModel(EDItems.SHULKER_OMELETTE);
        registerItemModel(EDItems.STIR_FRIED_SHULKER_MEAT);
        registerItemModel(EDItems.END_BARBECUE_STICK);
        registerItemModel(EDItems.ENDER_CONGEE);
        registerItemModel(EDItems.SHULKER_SOUP);
        registerItemModel(EDItems.ENDER_NOODLE);
        registerItemModel(EDItems.ROASTED_DRAGON_STEAK);
        registerItemModel(EDItems.ASSORTED_SALAD);
        registerItemModel(EDItems.ENDER_BAMBOO_RICE);
        registerItemModel(EDItems.DRAGON_BREATH_AND_CHORUS_SOUP);
        registerItemModel(EDItems.ENDERMAN_GRISTLE_STEW);
        registerItemModel(EDItems.DRAGON_LEG_WITH_SAUCE);
        registerItemModel(EDItems.STEAMED_DRAGON_EGG);
        registerItemModel(EDItems.DRAGON_MEAT_STEW);
        registerItemModel(EDItems.GRILLED_SHULKER);
        registerItemModel(Item.getItemFromBlock(EDBlocks.CHORUS_FRUIT_CRATE));
        registerItemModel(Item.getItemFromBlock(EDBlocks.STEAMED_DRAGON_EGG_BLOCK));
        registerItemModel(Item.getItemFromBlock(EDBlocks.DRAGON_MEAT_STEW_BLOCK));
        registerItemModel(Item.getItemFromBlock(EDBlocks.GRILLED_SHULKER_BLOCK));
        registerItemModel(Item.getItemFromBlock(EDBlocks.DRAGON_LEG_WITH_SAUCE_BLOCK));
    }

    private static void registerItemModel(Item item)
    {
        if (item == null || item.getRegistryName() == null) {
            return;
        }
        ModelLoader.setCustomModelResourceLocation(item, 0,
            new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
