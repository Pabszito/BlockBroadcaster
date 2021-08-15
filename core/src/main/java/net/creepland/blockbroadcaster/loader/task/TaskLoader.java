package net.creepland.blockbroadcaster.loader.task;

import lombok.RequiredArgsConstructor;
import net.creepland.blockbroadcaster.BlockBroadcaster;
import net.creepland.blockbroadcaster.loader.Loader;
import net.creepland.blockbroadcaster.util.UpdateChecker;

@RequiredArgsConstructor
public class TaskLoader implements Loader {

    private final BlockBroadcaster plugin;

    @Override
    public void load() {
        new UpdateChecker(plugin).checkForUpdates(version -> {
            if(plugin.getDescription().getVersion().equalsIgnoreCase(version)) {
                plugin.getLogger().info("BlockBroadcaster is up to date!");
            } else {
                plugin.getLogger().info("There is a new version of BlockBroadcaster available: " + version);
                plugin.getLogger().info("You can download it from https://www.spigotmc.org/resources/95345/");
            }
        });
    }
}
