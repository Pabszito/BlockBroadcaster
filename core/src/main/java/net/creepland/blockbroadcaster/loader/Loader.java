package net.creepland.blockbroadcaster.loader;

public interface Loader {

    void load();
    default void unload() {}
}
