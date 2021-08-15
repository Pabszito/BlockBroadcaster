package net.creepland.blockbroadcaster.loader.command;

import lombok.RequiredArgsConstructor;
import net.creepland.blockbroadcaster.BlockBroadcaster;
import net.creepland.blockbroadcaster.command.BlockBroadcasterCommand;
import net.creepland.blockbroadcaster.loader.Loader;

@RequiredArgsConstructor
public class CommandLoader implements Loader {

    private final BlockBroadcaster plugin;

    @Override
    public void load() {
        plugin.getCommand("blockbroadcaster").setExecutor(new BlockBroadcasterCommand(plugin));
    }
}
