package xy177.endsdelightlegacy.common.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public final class EDConfig
{
    public static boolean levitationFoodsRequireSneaking;
    public static boolean endermanGristleUsesRandomTeleport;
    public static float shulkerMeatDropChance;
    public static float endermanGristleDropChance;

    private static Configuration configuration;

    private EDConfig()
    {
    }

    public static void init(File configFile)
    {
        configuration = new Configuration(configFile);
        sync();
    }

    private static void sync()
    {
        if (configuration == null) {
            return;
        }

        configuration.load();
        String category = Configuration.CATEGORY_GENERAL;
        levitationFoodsRequireSneaking = configuration.getBoolean(
            "levitationFoodsRequireSneaking",
            category,
            true,
            "If true, End's Delight foods that grant Levitation only grant Levitation when eaten while sneaking. Other food effects still apply normally.\n"
                + "为 true 时，末地农家乐中会给予漂浮效果的食物只有潜行食用才会给予漂浮；其他食物效果仍正常生效。"
        );
        endermanGristleUsesRandomTeleport = configuration.getBoolean(
            "endermanGristleUsesRandomTeleport",
            category,
            true,
            "If true, Enderman Gristle, Raw Ender Sausage, and Ender Sausage use sneak-only random teleport without self-damage. If false, they use sneak-only forward teleport with self-damage.\n"
                + "为 true 时，末影人脆骨、生末影肉肠、末影肉肠会在潜行食用时随机传送且不受伤；为 false 时，潜行食用会向前传送并受伤。"
        );
        shulkerMeatDropChance = configuration.getFloat(
            "shulkerMeatDropChance",
            category,
            0.9F,
            0.0F,
            1.0F,
            "Chance for a Shulker killed with a knife to drop Shulker Meat. Range: 0.0 to 1.0.\n"
                + "使用刀击杀潜影贝时掉落潜影贝肉的概率。范围：0.0 到 1.0。"
        );
        endermanGristleDropChance = configuration.getFloat(
            "endermanGristleDropChance",
            category,
            0.35F,
            0.0F,
            1.0F,
            "Chance for an Enderman killed with a knife to drop Enderman Gristle. Range: 0.0 to 1.0.\n"
                + "使用刀击杀末影人时掉落末影人脆骨的概率。范围：0.0 到 1.0。"
        );

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
