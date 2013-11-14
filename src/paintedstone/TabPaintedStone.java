package paintedstone;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabPaintedStone extends CreativeTabs
{
    public TabPaintedStone(String label)
    {
        super(label);
    }

    public ItemStack getIconItemStack ()
    {
        return new ItemStack(PaintedStone.coloredStone);
    }
}