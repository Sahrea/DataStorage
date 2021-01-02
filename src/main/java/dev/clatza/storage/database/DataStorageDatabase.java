package dev.clatza.storage.database;

import dev.clatza.storage.database.DatabaseTyps.MySQL;
import dev.clatza.storage.database.DatabaseTyps.SQLite;

public class DataStorageDatabase implements iDatabase {
    private iDatabase Database = null;

    public DataStorageDatabase(String type, String host, int port, String user, String password, String db) {
        if (type.toLowerCase().equals("sqlite") && this.Database == null)
            Database = new SQLite();

        if (type.toLowerCase().equals("mysql") && this.Database == null)
            Database = new MySQL(host, port, user, password, db);

        if (this.Database == null)
            Database = new SQLite();
    }

    public String getEntry(String index) {
        return this.Database.getEntry(index);
    }

    public void setEntry(String index, String value) {
        this.Database.setEntry(index, value);
    }

    public void removeEntry(String index) {
        this.Database.removeEntry(index);
    }

    public void close(){
        this.Database.close();
    }
}
