package DBAccess;

import DBConnection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DBUsers interacts with the database to get the users information
 */
public class DBUsers {

    public static ObservableList<Users> getAllUsers(){

        ObservableList<Users> usersList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM users";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int userId = rs.getInt( "User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");

                Users U = new Users(userId, userName, password);
                usersList.add(U);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return usersList;
    }




}
