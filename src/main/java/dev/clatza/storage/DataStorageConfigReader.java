package dev.clatza.storage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class DataStorageConfigReader {
    private String DBType = "SQLite";
    private String DBUsername = "root";
    private String DBPassword = "";
    private String DBDatabase = "DataStorage";
    private String DBHost = "127.0.0.1";
    private int DBPort = 3306;

    protected DataStorageConfigReader(Plugin plugin) {
        setDefaultConfigValues(plugin.getConfig());
        getConfigValues(plugin.getConfig());
        saveConfigValues(plugin, plugin.getConfig());
    }

    protected String getDBType() {
        return this.DBType;
    }

    protected String getDBUsername() {
        return this.DBUsername;
    }

    protected String getDBPassword() {
        return this.DBPassword;
    }

    protected String getDBDatabase() {
        return this.DBDatabase;
    }

    protected String getDBHost() {
        return this.DBHost;
    }

    protected int getDBPort() {
        return this.DBPort;
    }

    private void setDefaultConfigValues(FileConfiguration _config) {
        _config.addDefault("DB.Type", this.DBType);

        _config.addDefault("MySQL.Host", this.DBHost);
        _config.addDefault("MySQL.Port", this.DBPort);
        _config.addDefault("MySQL.Database", this.DBDatabase);
        _config.addDefault("MySQL.User", this.DBUsername);
        _config.addDefault("MySQL.Password", this.DBPassword);
    }

    private void getConfigValues(FileConfiguration _config) {
        this.DBType = _config.getString("DB.Type");

        this.DBHost = _config.getString("MySQL.Host");
        this.DBPort = _config.getInt("MySQL.Port");
        this.DBDatabase = _config.getString("MySQL.Database");
        this.DBUsername = _config.getString("MySQL.User");
        this.DBPassword = _config.getString("MySQL.Password");
    }

    private void saveConfigValues(Plugin plugin, FileConfiguration _config) {
        _config.set("DB.Type", this.DBType);

        _config.set("MySQL.Host", this.DBHost);
        _config.set("MySQL.Port", this.DBPort);
        _config.set("MySQL.Database", this.DBDatabase);
        _config.set("MySQL.User", this.DBUsername);
        _config.set("MySQL.Password", this.DBPassword);

        plugin.saveConfig();
    }
}
