package net.creepland.blockbroadcaster.command;

import lombok.RequiredArgsConstructor;
import net.creepland.blockbroadcaster.BlockBroadcaster;
import net.creepland.blockbroadcaster.api.common.Configuration;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class BlockBroadcasterCommand implements CommandExecutor {

    private final BlockBroadcaster plugin;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(args.length < 1) {
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes(
                    '&',
                    "&3&lBLOCK &8» &rThis server is running &aBlockBroadcaster version &a"
                            + plugin.getDescription().getVersion()
                            + "&r made by &aPabszito&r."));

            commandSender.sendMessage(ChatColor.translateAlternateColorCodes(
                    '&',
                    "&3&lBLOCK &8» &rPlease use &a/blockbroadcaster help&r for a list of subcommands."));

            return true;
        }

        if(!commandSender.hasPermission("blockbroadcaster.command")) {
            commandSender.sendMessage(plugin.getSettings().getString("settings.no_permission"));
            return true;
        }

        switch(args[0].toLowerCase()) {
            case "help": {
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&',
                        "&3&lBLOCK &8» &rList of available subcommands:"));
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&',
                        "&3&lBLOCK &8» &a/block enable: &renables broadcasting"));
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&',
                        "&3&lBLOCK &8» &a/block disable: &rdisables broadcasting"));
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&',
                        "&3&lBLOCK &8» &a/block reload: &rreloads the plugin"));

                return true;
            }
            case "enable": {
                if(plugin.getSettings().getBoolean("settings.enabled")) {
                    commandSender.sendMessage(plugin.getSettings().getString("settings.already_enabled"));
                    return true;
                }

                plugin.getSettings().set("settings.enabled", true);
                plugin.getSettings().save();

                commandSender.sendMessage(plugin.getSettings().getString("settings.broadcasting_enabled"));
                return true;
            }
            case "disable": {
                if(!plugin.getSettings().getBoolean("settings.enabled")) {
                    commandSender.sendMessage(plugin.getSettings().getString("settings.already_disabled"));
                    return true;
                }

                plugin.getSettings().set("settings.enabled", false);
                plugin.getSettings().save();

                commandSender.sendMessage(plugin.getSettings().getString("settings.broadcasting_disabled"));
                return true;
            }
            case "reload": {
                plugin.setSettings(new Configuration(plugin, "settings"));
                commandSender.sendMessage(plugin.getSettings().getString("settings.settings_reloaded"));
                return true;
            }
            default: {
                commandSender.sendMessage(plugin.getSettings().getString("settings.unknown_subcommand"));
                return true;
            }
        }
    }
}
