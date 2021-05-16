package DBAccess;

import DBConnection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DBContacts {
    public static ObservableList<Contacts> getAllContacts(){

        ObservableList<Contacts> contactsList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM contacts";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int contactId = rs.getInt( "Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String email = rs.getString("Email");

                Contacts U = new Contacts(contactId, contactName, email);
                contactsList.add(U);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return contactsList;
    }
}
