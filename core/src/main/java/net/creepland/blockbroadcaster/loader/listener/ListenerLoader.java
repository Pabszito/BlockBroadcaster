package net.creepland.blockbroadcaster.loader.listener;

import lombok.RequiredArgsConstructor;
import net.creepland.blockbroadcaster.BlockBroadcaster;
import net.creepland.blockbroadcaster.listener.BlockBreakListener;
import net.creepland.blockbroadcaster.loader.Loader;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

@RequiredArgsConstructor
public class ListenerLoader implements Loader {

    private final BlockBroadcaster plugin;

    @Override
    public void load() {
        loadListeners(
                new BlockBreakListener(plugin)
        );
    }

    private void loadListeners(Listener... listeners) {
        PluginManager manager = Bukkit.getServer().getPluginManager();
        for(Listener listener : listeners) {
            manager.registerEvents(listener, plugin);
        }
    }
}
