package xy177.endsdelightlegacy.common.compat;

import java.lang.reflect.Constructor;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Loader;

public final class EDNetherDelightCompat
{
    public static final String MODID = "nethers_delight_legacy";

    private EDNetherDelightCompat()
    {
    }

    public static boolean isLoaded()
    {
        return Loader.isModLoaded(MODID);
    }

    public static Item createMachete(Item.ToolMaterial material)
    {
        try {
            Class<?> clazz = Class.forName("xy177.nethersdelightlegacy.common.item.MacheteItem");
            Constructor<?> constructor = clazz.getConstructor(Item.ToolMaterial.class);
            Object item = constructor.newInstance(material);
            return item instanceof Item ? (Item) item : null;
        } catch (ReflectiveOperationException | LinkageError ignored) {
            return null;
        }
    }
}
