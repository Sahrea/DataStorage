package dev.clatza.storage;

//https://pad.sahrea.de/p/gu3CJlb1digoXR00LoB5
//https://javabeginners.de/Grundlagen/Code-Konventionen.php

import dev.clatza.storage.database.DataStorageDatabase;
import dev.clatza.storage.entities.StorageChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DataStorage extends JavaPlugin {
    private DataStorageDatabase database = null;
    private DataStorageConfigReader configuration = null;

    @Override
    public void onEnable() {
        configuration = new DataStorageConfigReader(this);
        database = new DataStorageDatabase(configuration.getDBType(), configuration.getDBHost(), configuration.getDBPort(), configuration.getDBUsername(), configuration.getDBPassword(), configuration.getDBDatabase());
    }

    public void setEntry(String path, String data) {
        database.setEntry(path, data);

        StorageChangeEvent eventData = new StorageChangeEvent(path, data);
        getServer().getPluginManager().callEvent(eventData);
    }

    public void removeEntry(String path) {
        database.removeEntry(path);

        StorageChangeEvent eventData = new StorageChangeEvent(path);
    }

    public String getEntry(String path) {
        return database.getEntry(path);
    }
}
