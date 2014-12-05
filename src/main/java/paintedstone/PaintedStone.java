package paintedstone;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.oredict.OreDictionary;
//import tconstruct.common.TContent;
//import tconstruct.library.TConstructRegistry;
//import tconstruct.library.crafting.Detailing;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = "PaintedStone", name = "Painted Stone", version = "Beacon")
public class PaintedStone
{
    /* Define blocks, items, crucial info */
    @EventHandler
    public void preInit (FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
        TConstruct = Loader.isModLoaded("TConstruct");
        tab = new TabPaintedStone("paintedstone");
        //TODO Material.rock                                                                  setName
        coloredCobble = new PaintedStoneBlock(Material.rock, 2.0f, "stone_cobble", "stone.cobble").setUnlocalizedName("paintedstone.cobble");
        GameRegistry.registerBlock(coloredCobble, PaintedStoneItem.class, "cobble", "PaintedStone");
        coloredStone = new PaintedStoneBlock(Material.rock, 1.5f, "stone_raw", "stone.raw", coloredCobble).setUnlocalizedName("paintedstone.raw");
        GameRegistry.registerBlock(coloredStone, PaintedStoneItem.class, "raw", "PaintedStone");
        coloredMossCobble = new PaintedStoneBlock(Material.rock, 2.0f, "stone_mosscobble", "stone.mosscobble").setUnlocalizedName("paintedstone.mosscobble");
        GameRegistry.registerBlock(coloredMossCobble, PaintedStoneItem.class, "mosscobble", "PaintedStone");
        coloredStoneBrick = new PaintedStoneBlock(Material.rock, 1.5f, "stone_brick", "stone.brick").setUnlocalizedName("paintedstone.brick");
        GameRegistry.registerBlock(coloredStoneBrick, PaintedStoneItem.class, "brick", "PaintedStone");
        coloredMossStoneBrick = new PaintedStoneBlock(Material.rock, 1.5f, "stone_mossbrick", "stone.mossbrick").setUnlocalizedName("paintedstone.mossbrick");
        GameRegistry.registerBlock(coloredMossStoneBrick, PaintedStoneItem.class, "mossbrick", "PaintedStone");
        coloredCrackedStoneBrick = new PaintedStoneBlock(Material.rock, 1.5f, "stone_crackedbrick", "stone.crackedbrick").setUnlocalizedName("paintedstone.crackedbrick");
        GameRegistry.registerBlock(coloredCrackedStoneBrick, PaintedStoneItem.class, "crackedbrick", "PaintedStone");
        coloredStoneRoad = new PaintedStoneBlock(Material.rock, 1.5f, "stone_road", "stone.road").setUnlocalizedName("paintedstone.road");
        GameRegistry.registerBlock(coloredStoneRoad, PaintedStoneItem.class, "road", "PaintedStone");
        coloredStoneFancyBrick = new PaintedStoneBlock(Material.rock, 1.5f, "stone_fancy", "stone.fancy").setUnlocalizedName("paintedstone.fancy");
        GameRegistry.registerBlock(coloredStoneFancyBrick, PaintedStoneItem.class, "fancy", "PaintedStone");
        coloredStoneSquareBrick = new PaintedStoneBlock(Material.rock, 1.5f, "stone_square", "stone.chiseled").setUnlocalizedName("paintedstone.chiseled");
        GameRegistry.registerBlock(coloredStoneSquareBrick, PaintedStoneItem.class, "chiseled", "PaintedStone");

        for (int i = 0; i < 16; i++)
        {
            //TODO addSmelting
            FurnaceRecipes.instance().addSmeltingRecipeForBlock(coloredCobble, new ItemStack(coloredStone, 1, i), 0.2F);
            GameRegistry.addRecipe(new ItemStack(coloredStoneBrick, 4, i), "##", "##", '#', new ItemStack(coloredStone, 1, i));
            OreDictionary.registerOre("stone", new ItemStack(coloredStone, 1, i));
            OreDictionary.registerOre("cobblestone", new ItemStack(coloredCobble, 1, i));
        }
    }

    @EventHandler
    public void init (FMLInitializationEvent event)
    {
        /*if (TConstruct)
        {
            Detailing chiseling = TConstructRegistry.getChiselDetailing();
            for (int i = 0; i < 16; i++)
            {
                chiseling.addDetailing(coloredStone, i, coloredStoneBrick, i, TContent.chisel);
                chiseling.addDetailing(coloredStoneBrick, i, coloredStoneRoad, i, TContent.chisel);
                chiseling.addDetailing(coloredStoneRoad, i, coloredStoneFancyBrick, i, TContent.chisel);
                chiseling.addDetailing(coloredStoneFancyBrick, i, coloredStoneSquareBrick, i, TContent.chisel);
            }
        }*/
    }

    public static final String[] dyeTypes = new String[] { "white", "orange", "magenta", "lightblue", "yellow", "lime", "pink", "gray", "lightgray", "cyan", "purple", "blue", "brown", "green", "red",
            "black" };

    @SubscribeEvent
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
                    if (type.equals("dye" + dyeTypes[i]))
                    {
                        if (colorStoneBlocks(player.worldObj, event.pos, i))
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
                                Block block = Blocks.stone;
                                //TODO stepSound.getPlaceSound(), stepSound.getVolume(), stepSound.getPitch()
                                player.worldObj.playSoundEffect((double) ((float) event.pos.getX() + 0.5F), (double) ((float) event.pos.getY() + 0.5F), (double) ((float) event.pos.getZ() + 0.5F),
                                        block.stepSound.func_150496_b(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    public boolean colorStoneBlocks (World world, BlockPos pos, int inputMeta)
    {
        boolean changed = false;
        int range = 1;
        //System.out.println("Input: "+inputMeta);
        for (int xPos = -range; xPos <= range; xPos++)
        {
            for (int yPos = -range; yPos <= range; yPos++)
            {
                for (int zPos = -range; zPos <= range; zPos++)
                {
                    BlockPos tmp = pos.add(xPos,yPos, zPos);
                    //TODO getBlock()
                    Block block = world.getBlockState(tmp).getBlock();
                    if (block == Blocks.stone)
                    {
                        changed = true;
                        //TODO setBlock()
                        world.setBlock(tmp, coloredStone, inputMeta, 3);
                    }
                    else if (block == Blocks.cobblestone)
                    {
                        changed = true;
                        world.setBlock(tmp, coloredCobble, inputMeta, 3);
                    }
                    else if (block == Blocks.mossy_cobblestone)
                    {
                        changed = true;
                        world.setBlock(tmp, coloredMossCobble, inputMeta, 3);
                    }
                    else if (block == Blocks.stonebrick)
                    {
                        changed = true;
                        int meta = world.getBlockMetadata(tmp);

                        if (meta == 0)
                            world.setBlock(tmp, coloredStoneBrick, inputMeta, 3);
                        else if (meta == 1)
                            world.setBlock(tmp, coloredCrackedStoneBrick, inputMeta, 3);
                        else if (meta == 2)
                            world.setBlock(tmp, coloredMossStoneBrick, inputMeta, 3);
                        else if (meta == 3)
                            world.setBlock(tmp, coloredStoneSquareBrick, inputMeta, 3);

                    }
                    /*else if (TConstruct && block == TRepo.multiBrickFancy)
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
                    //Fancy brick*/
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
