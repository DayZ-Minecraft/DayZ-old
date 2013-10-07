package dayz.common.effects;

import net.minecraft.potion.Potion;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Effect extends Potion
{
    public static Effect bleeding;
    public static int bleedingId;
    public static Effect zombification;
    public static int zombificationId;

    public Effect(int id, boolean isBadEffect, int color, String name)
    {
        super(id, isBadEffect, color);
        setPotionName("potion." + name);
        LanguageRegistry.instance().addStringLocalization(getName(), name);
    }

    @Override
    public Potion setIconIndex(int x, int y)
    {
        super.setIconIndex(x, y);
        return this;
    }

    public static void loadEffects()
    {
        bleeding = new Effect(bleedingId, true, 5149489, "Bleeding");
        zombification = new Effect(zombificationId, true, 5149489, "Zombification");
    }

    public static void effectConfig(Configuration config)
    {
        bleedingId = config.get("effect", "bleedingId", 29, "Bleeding Effect ID").getInt();
        zombificationId = config.get("effect", "zombificationId", 30, "Zombification Effect ID").getInt();
    }

    public static void register()
    {
        Potion.potionTypes[bleeding.getId()] = bleeding;
        Potion.potionTypes[zombification.getId()] = zombification;
    }
}