package paintedstone;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PaintedStoneBlock extends Block
{
    public static final String[] colorNames = new String[] { "white", "orange", "magenta", "lightblue", "yellow", "lime", "pink", "gray", "silver", "aqua", "purple", "blue", "brown", "green", "red",
            "black" };
    public final String textureName;
    public final String localName;
    public IIcon[] icons;
    Block dropBlock;

    public PaintedStoneBlock(Material material, float hardness, String texture, String name)
    {
        super(material);
        //TODO setHardness()
        this.setHardness(hardness);
        this.textureName = texture;
        this.localName = name;
        //TODO setCreativeTab()
        this.setCreativeTab(PaintedStone.tab);
        this.dropBlock = this;
    }

    public PaintedStoneBlock(Material material, float hardness, String texture, String name, Block dropBlock)
    {
        this(material, hardness, texture, name);
        this.dropBlock = dropBlock;
    }

    //TODO getUnlocalizedName()
    @Override
    public String getUnlocalizedName ()
    {
        return "tile." + localName;
    }

    //TODO damageDropped
    @Override
    public int damageDropped (int meta)
    {
        return meta;
    }

    public Block blockDropped (int par1, Random par2Random, int par3)
    {
        return dropBlock;
    }

    //TODO registerIcons
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons (IIconRegister iconRegister)
    {
        this.icons = new IIcon[colorNames.length];

        for (int i = 0; i < this.icons.length; ++i)
        {
            this.icons[i] = iconRegister.registerIcon("paintedstone:" + textureName + "_" + colorNames[i]);
        }
    }

    //TODO getIcon
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon (int side, int meta)
    {
        return meta < icons.length ? icons[meta] : icons[0];
    }

    //TODO getSubBlocks
    @Override
    public void getSubBlocks (Item b, CreativeTabs tab, List list)
    {
        for (int iter = 0; iter < icons.length; iter++)
        {
            list.add(new ItemStack(b, 1, iter));
        }
    }
}
