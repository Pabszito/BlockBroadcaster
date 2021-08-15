package net.creepland.blockbroadcaster.api.block;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockParser {

    public static Map<Material, String> parseWhitelistedBlocks(List<String> blocks) {
        Map<Material, String> materials = new HashMap<>();
        for(String block : blocks) {
            String[] split = block.split(";");
            try {
                Material material = Material.valueOf(split[0].toUpperCase());
                String localizedName = split[1];

                materials.put(material, localizedName);
            }catch(IllegalArgumentException ex) {
                Bukkit.getLogger().warning(
                        "[BlockBroadcaster] Unable to parse whitelisted block: " + split[0].toUpperCase() + "; "
                        + "are you in the right Minecraft version?");
            }
        }

        return materials;
    }
}
