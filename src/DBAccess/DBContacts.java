package DBAccess;

import DBConnection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;

import java.sql.*;


/**
 * DBContacts interacts with the database to get the contacts information
 */
public class DBContacts {

    private String contactName;

    public DBContacts(String contactName) {
        this.contactName = contactName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }


    public static ObservableList<String> getAllContacts(){

        ObservableList<String> contactsList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM contacts";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int contactId = rs.getInt( "Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String email = rs.getString("Email");

                Contacts U = new Contacts(contactId, contactName, email);
                contactsList.add(String.valueOf(U));
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return contactsList;
    }

    /**
     * gets contact id associated with the contact name
     * @param Contact_Name
     * @return contactID
     */
    public static Integer getContactId(String Contact_Name) {
        String ContactId = " ";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pst;

            pst = conn.prepareStatement(
                    "select Contact_ID from contacts where Contact_Name = ?;");
            pst.setString(1, String.valueOf(Contact_Name));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ContactId = String.valueOf(rs.getInt("Contact_ID"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Integer.valueOf(ContactId);
    }

    /**
     * gets contact name associated with the contact id
     * @param Contact_ID
     * @return ContactName
     * @throws SQLException if connection fails
     */
    public static String contactName(int Contact_ID) throws SQLException {
        String contactName = " ";

        String sql = "select * from contacts WHERE Contact_ID = ?;";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setInt(1, Contact_ID);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            contactName = rs.getString("Contact_Name");
        }
        return contactName;

    }


}
