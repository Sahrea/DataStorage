package dev.clatza.storage.database.DatabaseTyps;

import dev.clatza.storage.database.iDatabase;

import java.sql.*;

public class MySQL implements iDatabase {

    Connection Connection = null;
    private String host = null;
    private String user = null;
    private String password = null;
    private String database = null;
    private Integer port = 0;

    public MySQL(String host, int port, String user, String password, String db) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.database = db;
        this.port = port;

        try {
            this.createConecction();

            Statement _statement = this.Connection.createStatement();
            _statement.executeUpdate("CREATE TABLE IF NOT EXISTS `KeyValue` (`indexValue` VARCHAR(256) NOT NULL COLLATE 'utf8_unicode_ci', `dataValue` TEXT NOT NULL COLLATE 'utf8_unicode_ci', PRIMARY KEY (`indexValue`) USING HASH) COLLATE='utf8_unicode_ci' ENGINE=InnoDB;");
            _statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void createConecction() throws SQLException {
        this.Connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.user, this.password);
    }

    private void checkConecction() throws SQLException {
        if (this.Connection.isClosed() || !this.Connection.isValid(3)) {
            this.Connection.close();
            createConecction();
        }
    }

    public String getEntry(String index) {
        try {
            checkConecction();

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
            checkConecction();

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
            checkConecction();

            PreparedStatement statement = this.Connection.prepareStatement("DELETE FROM KeyValue WHERE indexValue = ?");
            statement.setString(1, index);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void close(){
        try {
            if (this.Connection.isClosed() || !this.Connection.isValid(3)) {
                this.Connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
