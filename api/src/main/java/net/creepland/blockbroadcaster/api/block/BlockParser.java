package net.creepland.blockbroadcaster.api.block;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockParser {

    public static Map<Material, String> parseWhitelistedBlocks(List<String> blocks) {
        Map<Material, String> materials = new HashMap<>();
        for(String block : blocks) {
            String[] split = block.split(";");
            Material material = Material.valueOf(split[0]);
            String localizedName = split[1];

            materials.put(material, localizedName);
        }

        return materials;
    }
}
