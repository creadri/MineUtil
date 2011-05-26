package creadri.worldgen;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

/**
 *
 * @author creadri
 */
public class WorldManager {

    static public Sign getNearestPanel(World world, int x, int y, int z) {
        int nx, ny, nz;
        for (int i = -1; i < 2; i++) {
            nx = x + i;
            for (int j = -1; j < 2; j++) {
                ny = y + j;
                for (int k = -1; k < 2; k++) {
                    nz = z + k;
                    Block b = world.getBlockAt(nx, ny, nz);
                    if (b.getType() == Material.SIGN || b.getType() == Material.SIGN_POST || b.getType() == Material.WALL_SIGN) {
                        Sign sign = (Sign) b.getState();
                        return sign;
                    }
                }
            }
        }
        return null;
    }

    static public Sign getFaceUpPanel(Block block) {
        World world = block.getWorld();
        int nx = block.getX() + 1;
        int ny, nz;
        int y = block.getY();
        int z = block.getZ();

        for (int i = -1; i < 2; i++) {
            ny = y + i;
            for (int j = -1; j < 2; j++) {
                nz = z + j;
                Block b = world.getBlockAt(nx, ny, nz);

                if (b.getType() == Material.SIGN || b.getType() == Material.SIGN_POST || b.getType() == Material.WALL_SIGN) {
                    return (Sign) b.getState();
                }
            }
        }
        
        return null;
    }

    static public Block getNearestMaterial(World world, int x, int y, int z, Material type) {
        int nx, ny, nz;
        for (int i = -1; i < 2; i++) {
            nx = x + i;
            for (int j = -1; j < 2; j++) {
                ny = y + j;
                for (int k = -1; k < 2; k++) {
                    nz = z + k;
                    Block b = world.getBlockAt(nx, ny, nz);

                    if (b.getType() == type) {
                        return b;
                    }
                }
            }
        }
        return null;
    }
}
