package net.creepland.blockbroadcaster.loader.core;

import lombok.RequiredArgsConstructor;
import net.creepland.blockbroadcaster.BlockBroadcaster;
import net.creepland.blockbroadcaster.loader.Loader;
import net.creepland.blockbroadcaster.loader.listener.ListenerLoader;
import net.creepland.blockbroadcaster.loader.settings.SettingsLoader;

@RequiredArgsConstructor
public class CoreLoader implements Loader {

    private final BlockBroadcaster plugin;

    @Override
    public void load() {
        loadLoaders(
                new SettingsLoader(plugin),
                new ListenerLoader(plugin)
        );

        plugin.getLogger().info(
                "BlockBroadcaster version " + plugin.getDescription().getVersion() + " has been enabled."
        );
    }

    @Override
    public void unload() {
        unloadLoaders(
                new SettingsLoader(plugin)
        );

        plugin.getLogger().info(
                "BlockBroadcaster version " + plugin.getDescription().getVersion() + " has been disabled."
        );
    }

    private void loadLoaders(Loader... loaders) {
        for(Loader loader : loaders) {
            loader.load();
        }
    }

    private void unloadLoaders(Loader... loaders) {
        for(Loader loader : loaders) {
            loader.unload();
        }
    }
}
