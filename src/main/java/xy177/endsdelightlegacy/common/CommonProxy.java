package xy177.endsdelightlegacy.common;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import xy177.endsdelightlegacy.common.config.EDConfig;

public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {
        EDConfig.init(event.getSuggestedConfigurationFile());
    }

    public void init(FMLInitializationEvent event)
    {
    }

    public void postInit(FMLPostInitializationEvent event)
    {
    }

    public void serverStarting(FMLServerStartingEvent event)
    {
    }
}
