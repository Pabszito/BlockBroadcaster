package net.creepland.blockbroadcaster.api.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockAmount {

    public static int getAmountInRadius(Block block, int radius) {
        List<Block> blocks = new ArrayList<>();
        for (int x = block.getX() + radius; x < block.getX(); x++) {
            for (int y = block.getY() + radius; y < block.getY(); y++) {
                for (int z = block.getZ() + radius; z < block.getZ(); z++) {
                    Block blockInRadius = block.getWorld().getBlockAt(x,y,z);
                    if(blockInRadius.getType() == block.getType()) {
                        blocks.add(blockInRadius);
                    }
                }
            }
        }

        return blocks.size();
    }
}
