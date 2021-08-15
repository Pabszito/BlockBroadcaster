package net.creepland.blockbroadcaster;

import lombok.Getter;
import lombok.Setter;
import net.creepland.blockbroadcaster.api.common.Configuration;
import net.creepland.blockbroadcaster.loader.Loader;
import net.creepland.blockbroadcaster.loader.core.CoreLoader;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockBroadcaster extends JavaPlugin {

    private Loader loader;

    @Setter @Getter
    private Configuration settings;

    @Override
    public void onEnable() {
        this.loader = new CoreLoader(this);

        loader.load();
    }

    @Override
    public void onDisable() {
        loader.unload();
    }
}
