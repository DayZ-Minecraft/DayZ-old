package dayz.client;

import java.util.EnumSet;

import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class KeyHandlerDayZ extends KeyHandler
{
    // static KeyBinding dayzBinding = new KeyBinding("Backpack",
    // Keyboard.KEY_G);

    public KeyHandlerDayZ()
    {
        super(new KeyBinding[] {/* dayzBinding */}, new boolean[]
        { false });
    }

    @Override
    public String getLabel()
    {
        return "";
    }

    @Override
    public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat)
    {

    }

    @Override
    public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd)
    {

    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.CLIENT);
    }
}