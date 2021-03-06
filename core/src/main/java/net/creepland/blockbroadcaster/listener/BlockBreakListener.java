package net.creepland.blockbroadcaster.listener;

import lombok.RequiredArgsConstructor;
import net.creepland.blockbroadcaster.BlockBroadcaster;
import net.creepland.blockbroadcaster.api.block.BlockParser;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Map;

@RequiredArgsConstructor
public class BlockBreakListener implements Listener {

    private final BlockBroadcaster plugin;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Map<Material, String> whitelistedMaterials = BlockParser.parseWhitelistedBlocks(
                plugin.getSettings().getStringList("settings.whitelisted_blocks")
        );

        if (!plugin.getSettings().getBoolean("settings.enabled")) {
            return;
        }

        if (plugin.getSettings().getBoolean("settings.survival_only") && player.getGameMode() != GameMode.SURVIVAL) {
            return;
        }

        if(!whitelistedMaterials.containsKey(block.getType())) {
            return;
        }

        if(plugin.getSettings().getBoolean("settings.permission_enabled")) {
            Bukkit.getOnlinePlayers().forEach((target) -> {
                if(target.hasPermission("blockbroadcaster.see.broadcasts")) {
                    target.sendMessage(
                            plugin.getSettings().getString("settings.broadcast")
                                    .replace("%player%", player.getName())
                                    .replace("%block%", whitelistedMaterials.get(block.getType()))
                    );
                }
            });
        } else {
            Bukkit.broadcastMessage(
                    plugin.getSettings().getString("settings.broadcast")
                            .replace("%player%", player.getName())
                            .replace("%block%", whitelistedMaterials.get(block.getType()))
            );
        }
    }
}
