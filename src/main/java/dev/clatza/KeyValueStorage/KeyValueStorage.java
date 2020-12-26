package dev.clatza.KeyValueStorage;

import org.bukkit.event.*;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class KeyValueStorage extends JavaPlugin implements Listener {
    @Override
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerConnect(PlayerJoinEvent event)
    {

    }
}
