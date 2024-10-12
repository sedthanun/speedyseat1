/**
 * @author marttpq
 */
package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DBconnect {
    protected static String url;
    protected Connection con = null;
    protected Statement stm;
    protected ResultSet rs;

    public DBconnect() {
        this.url = "jdbc:sqlite:test/src/main/resources/spd.db";
    }

    public DBconnect(String url) {
        this.url = url;
    }
    public abstract ResultSet getSelect(String s);

    public abstract void getUpdate(String s);

    public abstract void  disconnect();

}
