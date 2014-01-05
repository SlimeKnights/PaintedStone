package paintedstone;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class PaintedStoneItem extends ItemBlock
{
    public PaintedStoneItem(Block b)
    {
        super(b);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata (int meta)
    {
        return meta;
    }

    @Override
    public String getUnlocalizedName (ItemStack par1ItemStack)
    {
        return super.getUnlocalizedName() + "." + PaintedStoneBlock.colorNames[par1ItemStack.getItemDamage()];
    }
}
