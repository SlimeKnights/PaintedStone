package paintedstone;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.oredict.OreDictionary;
import tconstruct.common.TContent;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "PaintedStone", name = "Painted Stone", version = "Arrow")
public class PaintedStone
{
    /* Define blocks, items, crucial info */
    @EventHandler
    public void preInit (FMLPreInitializationEvent event)
    {
        PHPaint.initProps(event.getModConfigurationDirectory());
        MinecraftForge.EVENT_BUS.register(this);
        TConstruct = Loader.isModLoaded("TConstruct");
        tab = new TabPaintedStone("paintedstone");

        coloredCobble = new PaintedStoneBlock(PHPaint.coloredCobble, Material.rock, 2.0f, "stone_cobble", "stone.cobble").setUnlocalizedName("paintedstone.cobble");
        GameRegistry.registerBlock(coloredCobble, PaintedStoneItem.class, "paintedstone.cobble", "PaintedStone");
        coloredStone = new PaintedStoneBlock(PHPaint.coloredStone, Material.rock, 1.5f, "stone_raw", "stone.raw", coloredCobble.blockID).setUnlocalizedName("paintedstone.raw");
        GameRegistry.registerBlock(coloredStone, PaintedStoneItem.class, "paintedstone.raw", "PaintedStone");
        coloredMossCobble = new PaintedStoneBlock(PHPaint.coloredMossCobble, Material.rock, 2.0f, "stone_mosscobble", "stone.mosscobble").setUnlocalizedName("paintedstone.mosscobble");
        GameRegistry.registerBlock(coloredMossCobble, PaintedStoneItem.class, "paintedstone.mosscobble", "PaintedStone");
        coloredStoneBrick = new PaintedStoneBlock(PHPaint.coloredStoneBrick, Material.rock, 1.5f, "stone_brick", "stone.brick").setUnlocalizedName("paintedstone.brick");
        GameRegistry.registerBlock(coloredStoneBrick, PaintedStoneItem.class, "paintedstone.brick", "PaintedStone");
        coloredMossStoneBrick = new PaintedStoneBlock(PHPaint.coloredMossStoneBrick, Material.rock, 1.5f, "stone_mossbrick", "stone.mossbrick").setUnlocalizedName("paintedstone.mossbrick");
        GameRegistry.registerBlock(coloredMossStoneBrick, PaintedStoneItem.class, "paintedstone.mossbrick", "PaintedStone");
        coloredCrackedStoneBrick = new PaintedStoneBlock(PHPaint.coloredCrackedBrick, Material.rock, 1.5f, "stone_crackedbrick", "stone.crackedbrick").setUnlocalizedName("paintedstone.crackedbrick");
        GameRegistry.registerBlock(coloredCrackedStoneBrick, PaintedStoneItem.class, "paintedstone.crackedbrick", "PaintedStone");
        coloredStoneRoad = new PaintedStoneBlock(PHPaint.coloredStoneRoad, Material.rock, 1.5f, "stone_road", "stone.road").setUnlocalizedName("paintedstone.road");
        GameRegistry.registerBlock(coloredStoneRoad, PaintedStoneItem.class, "paintedstone.road", "PaintedStone");
        coloredStoneFancyBrick = new PaintedStoneBlock(PHPaint.coloredStoneFancyBrick, Material.rock, 1.5f, "stone_fancy", "stone.fancy").setUnlocalizedName("paintedstone.fancy");
        GameRegistry.registerBlock(coloredStoneFancyBrick, PaintedStoneItem.class, "paintedstone.fancy", "PaintedStone");
        coloredStoneSquareBrick = new PaintedStoneBlock(PHPaint.coloredStoneSquareBrick, Material.rock, 1.5f, "stone_square", "stone.chiseled").setUnlocalizedName("paintedstone.chiseled");
        GameRegistry.registerBlock(coloredStoneSquareBrick, PaintedStoneItem.class, "paintedstone.chiseled", "PaintedStone");
    }
    
    @ForgeSubscribe
    public void playerInteract (PlayerInteractEvent event)
    {
        EntityPlayer player = event.entityPlayer;
        //Dyes
        if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
        {
            ItemStack stack = player.getCurrentEquippedItem();
            String type = OreDictionary.getOreName(OreDictionary.getOreID(stack));
            if (type != null)
            {
                type = type.toLowerCase();
                for (int i = 0; i < 16; i++)
                {
                    if (type.equals("dye" + PaintedStoneBlock.colorNames[i]))
                    {
                        if (colorStoneBlocks(player.worldObj, event.x, event.y, event.z, i))
                        {
                            if (!player.capabilities.isCreativeMode)
                            {
                                stack.stackSize--;
                                if (stack.stackSize <= 0)
                                    player.destroyCurrentEquippedItem();
                            }

                            player.swingItem();
                            if (!player.worldObj.isRemote)
                            {
                                Block block = Block.stone;
                                player.worldObj.playSoundEffect((double) ((float) event.x + 0.5F), (double) ((float) event.y + 0.5F), (double) ((float) event.z + 0.5F),
                                        block.stepSound.getPlaceSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    public boolean colorStoneBlocks (World world, int x, int y, int z, int inputMeta)
    {
        boolean changed = false;
        int range = 1;
        for (int xPos = -range; xPos <= range; xPos++)
        {
            for (int yPos = -range; yPos <= range; yPos++)
            {
                for (int zPos = -range; zPos <= range; zPos++)
                {
                    int blockID = world.getBlockId(x + xPos, y + yPos, z + zPos);
                    if (blockID == Block.stone.blockID)
                    {
                        changed = true;
                        world.setBlock(x + xPos, y + yPos, z + zPos, coloredStone.blockID, inputMeta, 3);
                    }
                    else if (blockID == Block.cobblestone.blockID)
                    {
                        changed = true;
                        world.setBlock(x + xPos, y + yPos, z + zPos, coloredCobble.blockID, inputMeta, 3);
                    }
                    else if (blockID == Block.cobblestoneMossy.blockID)
                    {
                        changed = true;
                        world.setBlock(x + xPos, y + yPos, z + zPos, coloredMossCobble.blockID, inputMeta, 3);
                    }
                    else if (blockID == Block.stoneBrick.blockID)
                    {
                        changed = true;
                        int meta = world.getBlockMetadata(x + xPos, y + yPos, z + zPos);

                        if (meta == 0)
                            world.setBlock(x + xPos, y + yPos, z + zPos, coloredStoneBrick.blockID, inputMeta, 3);
                        else if (meta == 1)
                            world.setBlock(x + xPos, y + yPos, z + zPos, coloredCrackedStoneBrick.blockID, inputMeta, 3);
                        else if (meta == 2)
                            world.setBlock(x + xPos, y + yPos, z + zPos, coloredMossStoneBrick.blockID, inputMeta, 3);
                        else if (meta == 3)
                            world.setBlock(x + xPos, y + yPos, z + zPos, coloredStoneSquareBrick.blockID, inputMeta, 3);

                    }
                    else if (TConstruct && blockID == TContent.multiBrickFancy.blockID)
                    {
                        int meta = world.getBlockMetadata(x + xPos, y + yPos, z + zPos);
                        if (meta == 14)
                        {
                            changed = true;
                            world.setBlock(x + xPos, y + yPos, z + zPos, coloredStoneFancyBrick.blockID, inputMeta, 3);                            
                        }
                        else if (meta == 15)
                        {
                            changed = true;
                            world.setBlock(x + xPos, y + yPos, z + zPos, coloredStoneRoad.blockID, inputMeta, 3);                            
                        }
                    }
                    //Road
                    //Fancy brick
                }
            }
        }
        return changed;
    }

    public static CreativeTabs tab;
    private boolean TConstruct;
    public static Block coloredStone;
    public static Block coloredCobble;
    public static Block coloredMossCobble;
    public static Block coloredStoneBrick;
    public static Block coloredMossStoneBrick;
    public static Block coloredCrackedStoneBrick;
    public static Block coloredStoneRoad;
    public static Block coloredStoneFancyBrick;
    public static Block coloredStoneSquareBrick;
}
