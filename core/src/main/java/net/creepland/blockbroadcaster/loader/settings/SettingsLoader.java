package net.creepland.blockbroadcaster.loader.settings;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.creepland.blockbroadcaster.BlockBroadcaster;
import net.creepland.blockbroadcaster.api.common.Configuration;
import net.creepland.blockbroadcaster.loader.Loader;

@RequiredArgsConstructor
public class SettingsLoader implements Loader {

    private final BlockBroadcaster plugin;
    private Configuration settings;

    @Override
    public void load() {
        this.settings = new Configuration(plugin, "settings");

        plugin.setSettings(settings);
    }

    @Override
    public void unload() {
        settings.save();
    }
}
