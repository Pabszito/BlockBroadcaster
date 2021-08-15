package net.creepland.blockbroadcaster.loader.core;

import lombok.RequiredArgsConstructor;
import net.creepland.blockbroadcaster.BlockBroadcaster;
import net.creepland.blockbroadcaster.loader.Loader;
import net.creepland.blockbroadcaster.loader.command.CommandLoader;
import net.creepland.blockbroadcaster.loader.listener.ListenerLoader;
import net.creepland.blockbroadcaster.loader.settings.SettingsLoader;
import net.creepland.blockbroadcaster.loader.task.TaskLoader;
import net.creepland.blockbroadcaster.util.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.util.Consumer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

@RequiredArgsConstructor
public class CoreLoader implements Loader {

    private final BlockBroadcaster plugin;

    @Override
    public void load() {
        loadLoaders(
                new SettingsLoader(plugin),
                new ListenerLoader(plugin),
                new CommandLoader(plugin),
                new TaskLoader(plugin)
        );

        plugin.getLogger().info(
                "BlockBroadcaster version " + plugin.getDescription().getVersion() + " has been enabled."
        );
    }

    @Override
    public void unload() {
        plugin.getLogger().info(
                "BlockBroadcaster version " + plugin.getDescription().getVersion() + " has been disabled."
        );
    }

    private void loadLoaders(Loader... loaders) {
        for(Loader loader : loaders) {
            loader.load();
        }
    }
}
