package dev.clatza.KeyValueStorage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigReader {
    private Plugin Plugin;
    public ConfigReader(Plugin plugin)
    {
        this.Plugin = plugin;

        setDefaultConfigValues(this.Plugin.getConfig());
        getConfigValues(this.Plugin.getConfig());
        saveConfigValues(this.Plugin.getConfig());
    }

    private String DBType = "SQLite";
    public String getDBType()
    {
        return this.DBType;
    }

    private String DBUsername = "root";
    public String getDBUsername()
    {
        return this.DBUsername;
    }

    private String DBPassword = "";
    public String getDBPassword()
    {
        return this.DBPassword;
    }

    private String DBDatabase = "KeyValueStorage";
    public String getDBDatabase()
    {
        return this.DBDatabase;
    }

    private String DBHost = "127.0.0.1";
    public String getDBHost()
    {
        return this.DBHost;
    }

    private int DBPort = 3306;
    public int getDBPort()
    {
        return this.DBPort;
    }

    private void setDefaultConfigValues(FileConfiguration _config)
    {
        _config.addDefault("DB.Type", this.DBType);

        _config.addDefault("MySQL.Host", this.DBHost);
        _config.addDefault("MySQL.Port", this.DBPort);
        _config.addDefault("MySQL.Database", this.DBDatabase);
        _config.addDefault("MySQL.User", this.DBUsername);
        _config.addDefault("MySQL.Password", this.DBPassword);
    }

    private void getConfigValues(FileConfiguration _config)
    {
        this.DBType = _config.getString("DB.Type");

        this.DBHost = _config.getString("MySQL.Host");
        this.DBPort = _config.getInt("MySQL.Port");
        this.DBDatabase = _config.getString("MySQL.Database");
        this.DBUsername = _config.getString("MySQL.User");
        this.DBPassword = _config.getString("MySQL.Password");
    }

    private void saveConfigValues(FileConfiguration _config)
    {
        _config.set("DB.Type", this.DBType);

        _config.set("MySQL.Host", this.DBHost);
        _config.set("MySQL.Port", this.DBPort);
        _config.set("MySQL.Database", this.DBDatabase);
        _config.set("MySQL.User", this.DBUsername);
        _config.set("MySQL.Password", this.DBPassword);

        this.Plugin.saveConfig();
    }
}
