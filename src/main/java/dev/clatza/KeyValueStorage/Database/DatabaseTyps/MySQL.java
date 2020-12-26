package dev.clatza.KeyValueStorage.Database.DatabaseTyps;

import dev.clatza.KeyValueStorage.Database.iDatabase;
import dev.clatza.KeyValueStorage.GlobalDataStorage;

import java.sql.*;

public class MySQL implements iDatabase {

    Connection Connection = null;

    public MySQL()
    {
        try {
            this.createConecction();

            Statement _statement = this.Connection.createStatement();
            _statement.executeUpdate("CREATE TABLE IF NOT EXISTS `KeyValue` (`indexValue` VARCHAR(64) NOT NULL COLLATE 'utf8_unicode_ci', `dataValue` TEXT NOT NULL COLLATE 'utf8_unicode_ci', PRIMARY KEY (`indexValue`) USING BTREE) COLLATE='utf8_unicode_ci' ENGINE=InnoDB;");
            _statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void createConecction() throws SQLException {
        this.Connection = DriverManager.getConnection("jdbc:mysql://" + GlobalDataStorage.ConfigReader.getDBHost() + ":" + GlobalDataStorage.ConfigReader.getDBPort() + "/" + GlobalDataStorage.ConfigReader.getDBDatabase(), GlobalDataStorage.ConfigReader.getDBUsername(), GlobalDataStorage.ConfigReader.getDBPassword());
    }

    private void checkConecction() throws SQLException {
        if(this.Connection.isClosed() || !this.Connection.isValid(3))
        {
            this.Connection.close();
            createConecction();
        }
    }

    public String getEntry(String index)
    {
        try {
            checkConecction();

            PreparedStatement statement = this.Connection.prepareStatement("SELECT dataValue FROM KeyValue WHERE indexValue = ?");
            statement.setString(1, index);

            ResultSet result = statement.executeQuery();

            if(result.next())
                return result.getString("dataValue");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public void setEntry(String index, String value)
    {
        try {
            checkConecction();

            PreparedStatement statement = this.Connection.prepareStatement("REPLACE INTO KeyValue(indexValue, dataValue) VALUES (?, ?)");
            statement.setString(1, index);
            statement.setString(2, value);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeEntry(String index)
    {
        try {
            checkConecction();

            PreparedStatement statement = this.Connection.prepareStatement("DELETE FROM KeyValue WHERE indexValue = ?");
            statement.setString(1, index);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
