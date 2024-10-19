package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBmanipulation extends DBconnect {
    private static DBmanipulation instance;

    public DBmanipulation() {
        super();
    }

    public DBmanipulation(String url) {
        super(url);
    }

    public static DBmanipulation getInstance() {
        if (instance == null) {
            instance = new DBmanipulation();
        }
        return instance;
    }

    public void getUpdate(String s) {
        try {
            con = DriverManager.getConnection(url);
            stm = con.createStatement();
            stm.executeUpdate(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            disconnect();
        }
    }

    public Connection getCon() {
        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return con;
    }

    public void disconnect() {
        try {
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public ResultSet getSelect(String s) {
        return null;
    }

}
