package paintedstone;

import java.io.File;
import java.io.IOException;

import net.minecraftforge.common.config.Configuration;

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
        coloredStone = config.get("Blocks", "Colored Stone", true).getBoolean(true);
        coloredCobble = config.get("Blocks", "Colored Cobblestone", true).getBoolean(true);
        coloredMossCobble = config.get("Blocks", "Colored Mossy Cobble", true).getBoolean(true);
        coloredStoneBrick = config.get("Blocks", "Colored Stone Brick", true).getBoolean(true);
        coloredMossStoneBrick = config.get("Blocks", "Colored Mossy Stone Brick", true).getBoolean(true);
        coloredCrackedBrick = config.get("Blocks", "Colored Cracked Stone Brick", true).getBoolean(true);
        coloredStoneRoad = config.get("Blocks", "Colored Stone Road", true).getBoolean(true);
        coloredStoneFancyBrick = config.get("Blocks", "Colored Fancy Stone Brick", true).getBoolean(true);
        coloredStoneSquareBrick = config.get("Blocks", "Colored Chiseled Stone Brick", true).getBoolean(true);

        config.save();
    }

    public static boolean coloredStone;
    public static boolean coloredCobble;
    public static boolean coloredMossCobble;
    public static boolean coloredStoneBrick;
    public static boolean coloredMossStoneBrick;
    public static boolean coloredCrackedBrick;
    public static boolean coloredStoneRoad;
    public static boolean coloredStoneFancyBrick;
    public static boolean coloredStoneSquareBrick;
}
