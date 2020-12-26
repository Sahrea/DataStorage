package dev.clatza.KeyValueStorage;

import dev.clatza.KeyValueStorage.Database.KeyValueDatabase;
import org.bukkit.plugin.Plugin;

public class GlobalDataStorage {
    public static KeyValueStorage Plugin = null;
    public static ConfigReader ConfigReader = null;
    public static KeyValueDatabase Database = null;
}
