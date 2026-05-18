package xy177.endsdelightlegacy;

import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xy177.endsdelightlegacy.common.CommonProxy;
import xy177.endsdelightlegacy.common.registry.EDBlocks;
import xy177.endsdelightlegacy.common.registry.EDCreativeTab;
import xy177.endsdelightlegacy.common.registry.EDHuntingDropRegistry;
import xy177.endsdelightlegacy.common.registry.EDItems;
import xy177.endsdelightlegacy.common.registry.EDRecipeRegistry;
import xy177.endsdelightlegacy.common.tile.TileEntityDragonLegFeast;
import xy177.endsdelightlegacy.common.world.EDWorldGenerator;

@Mod(
    modid = EndsDelightLegacy.MODID,
    name = EndsDelightLegacy.NAME,
    version = EndsDelightLegacy.VERSION,
    dependencies = "required-after:farmersdelight;after:futuremc;after:netherized;after:nb"
)
@Mod.EventBusSubscriber(modid = EndsDelightLegacy.MODID)
public class EndsDelightLegacy
{
    public static final String MODID = "ends_delight";
    public static final String NAME = "End's Delight Legacy";
    public static final String VERSION = "1.0.1";
    public static final CreativeTabs CREATIVE_TAB = EDCreativeTab.TAB;

    private static Logger logger;

    @SidedProxy(clientSide = "xy177.endsdelightlegacy.client.ClientProxy", serverSide = "xy177.endsdelightlegacy.common.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        GameRegistry.registerTileEntity(TileEntityDragonLegFeast.class, MODID + ":dragon_leg_feast");
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
        GameRegistry.registerWorldGenerator(new EDWorldGenerator(), 0);
        EDRecipeRegistry.registerAll();
        EDHuntingDropRegistry.register();
        logger.info("{} initialized.", NAME);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        proxy.serverStarting(event);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        EDBlocks.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        EDItems.register(event.getRegistry());
        EDBlocks.registerItemBlocks(event.getRegistry());
        xy177.endsdelightlegacy.common.registry.EDOreDictionary.register();
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event)
    {
        EDRecipeRegistry.registerCraftingRecipes(event.getRegistry());
    }

    public static Logger getLogger()
    {
        return logger;
    }
}
