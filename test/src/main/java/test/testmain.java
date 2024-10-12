package test;
import java.sql.*;
import Database.*;

public class testmain {
    public static void main(String[] args) {
        try {
            ResultSet rs = DBquery.getInstance().getSelect("SELECT * FROM Theatre;");
            while (rs.next()) {
                System.out.println(rs.getString("theatre_id")); // SELECT

            }

//            DBmanipulation.getInstance().getUpdate("INSERT INTO Theatre(theatre_branch,theatre_number) VALUES(2,3)");  // INSERT
//             DBmanipulation.getInstance().getUpdate("UPDATE Theatre set theatre_branch = 4 WHERE theatre_id = 0"); // UPDATE
            DBquery.getInstance().disconnect(); //อย่าลืม disconnectด้วยครับ
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
