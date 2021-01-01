package dev.clatza.storage.database;

public interface iDatabase {
    public String getEntry(String index);

    public void setEntry(String index, String value);

    public void removeEntry(String index);
}
