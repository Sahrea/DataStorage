package dev.clatza.storage.database.DatabaseTyps;

import dev.clatza.storage.database.iDatabase;

import java.sql.*;

public class SQLite implements iDatabase {

    Connection Connection = null;

    public SQLite() {
        try {
            this.Connection = DriverManager.getConnection("jdbc:sqlite:DataStorage.db");

            Statement _statement = this.Connection.createStatement();

            _statement.executeUpdate("CREATE TABLE IF NOT EXISTS KeyValue(indexValue varchar(256) PRIMARY KEY, dataValue TEXT)");
            _statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getEntry(String index) {
        try {
            PreparedStatement statement = this.Connection.prepareStatement("SELECT dataValue FROM KeyValue WHERE indexValue = ?");
            statement.setString(1, index);

            ResultSet result = statement.executeQuery();

            if (result.next())
                return result.getString("dataValue");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public void setEntry(String index, String value) {
        try {
            PreparedStatement statement = this.Connection.prepareStatement("REPLACE INTO KeyValue(indexValue, dataValue) VALUES (?, ?)");
            statement.setString(1, index);
            statement.setString(2, value);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeEntry(String index) {
        try {
            PreparedStatement statement = this.Connection.prepareStatement("DELETE FROM KeyValue WHERE indexValue = ?");
            statement.setString(1, index);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
