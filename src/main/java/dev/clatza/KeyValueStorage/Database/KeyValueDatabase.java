package dev.clatza.KeyValueStorage.Database;

import dev.clatza.KeyValueStorage.Database.DatabaseTyps.MySQL;
import dev.clatza.KeyValueStorage.Database.DatabaseTyps.SQLite;
import dev.clatza.KeyValueStorage.GlobalDataStorage;

public class KeyValueDatabase implements iDatabase{
    private iDatabase Database = null;

    public KeyValueDatabase()
    {
        String dbType = GlobalDataStorage.ConfigReader.getDBType().toLowerCase().trim();

        if(dbType.equals("sqlite") && this.Database == null)
            Database = new SQLite();

        if(dbType.equals("mysql") && this.Database == null)
            Database = new MySQL();

        if(this.Database == null)
            Database = new SQLite();
    }

    public String getEntry(String index)
    {
        return this.Database.getEntry(index);
    }

    public void setEntry(String index, String value)
    {
        this.Database.setEntry(index, value);
    }

    public void removeEntry(String index)
    {
        this.Database.removeEntry(index);
    }
}
