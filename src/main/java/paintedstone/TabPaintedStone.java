package paintedstone;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TabPaintedStone extends CreativeTabs
{
    public TabPaintedStone(String label)
    {
        super(label);
    }

    public ItemStack getIconItemStack ()
    {
        return new ItemStack(PaintedStone.coloredStone, 1, 13);
    }

    @Override
    public Item getTabIconItem ()
    {
        return new ItemStack(PaintedStone.coloredCobble).getItem();
    }
}