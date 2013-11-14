package paintedstone;

import java.io.File;
import java.io.IOException;

import net.minecraftforge.common.Configuration;

public class PHPaint
{
    public static void initProps (File location)
    {
        /* Here we will set up the config file for the mod 
         * First: Create a folder inside the config folder
         * Second: Create the actual config file
         * Note: Configs are a pain, but absolutely necessary for every mod.
         */
        File newFile = new File(location + "/PaintedStone.txt");

        /* Some basic debugging will go a long way */
        try
        {
            newFile.createNewFile();
        }
        catch (IOException e)
        {
            System.out.println("Could not create configuration file for Painted Stone. Reason:");
            System.out.println(e);
        }

        /* [Forge] Configuration class, used as config method */
        Configuration config = new Configuration(newFile);

        /* Load the configuration file */
        config.load();

        /* Define the mod's IDs. 
         * Avoid values below 4096 for items and in the 250-450 range for blocks
         */

        //Blocks
        coloredStone = config.getBlock("Colored Stone", 2903).getInt(2903);
        coloredCobble = config.getBlock("Colored Cobblestone", 2904).getInt(2904);
        coloredMossCobble = config.getBlock("Colored Mossy Cobble", 2905).getInt(2905);
        coloredStoneBrick = config.getBlock("Colored Stone Brick", 2906).getInt(2906);
        coloredMossStoneBrick = config.getBlock("Colored Mossy Stone Brick", 2907).getInt(2907);
        coloredCrackedBrick = config.getBlock("Colored Cracked Stone Brick", 2908).getInt(2908);
        coloredStoneRoad = config.getBlock("Colored Stone Road", 2909).getInt(2909);
        coloredStoneFancyBrick = config.getBlock("Colored Fancy Stone Brick", 2910).getInt(2910);
        coloredStoneSquareBrick = config.getBlock("Colored Chiseled Stone Brick", 2911).getInt(2911);
    }

    public static int coloredStone;
    public static int coloredCobble;
    public static int coloredMossCobble;
    public static int coloredStoneBrick;
    public static int coloredMossStoneBrick;
    public static int coloredCrackedBrick;
    public static int coloredStoneRoad;
    public static int coloredStoneFancyBrick;
    public static int coloredStoneSquareBrick;
}
