package labapp;

import java.beans.ConstructorProperties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort +"/"+ dbName +"?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void signUpUser(User user) {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USERS_USERNAME +
                "," + Const.USERS_LOGIN + "," + Const.USERS_PASSWORD + ")"
                + "VALUES(?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getUserName());
            prSt.setString(2, user.getLogin());
            prSt.setString(3, user.getPassword());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getUser(User user){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USERS_LOGIN + "=? AND " + Const.USERS_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return resSet;
    }
}
