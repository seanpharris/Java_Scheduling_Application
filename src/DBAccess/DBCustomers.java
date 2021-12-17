package DBAccess;

import DBConnection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import model.Countries;
import model.Customers;
import model.FirstLevelDivisons;

import java.sql.*;


/**
 * DBCustomers interacts with the database to get the customers information
 */
public class DBCustomers {

    /**
     * gets all customers information from the customers table
     * @return customer table information
     */
    public static ObservableList<Customers> getAllCustomers(){

        ObservableList<Customers> customersList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM customers";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int customerId = rs.getInt( "Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                int postalCode = rs.getInt("Postal_Code");
                int phoneNumber = rs.getInt("Phone");
                Timestamp createdDate = rs.getTimestamp("Create_Date");
                String createdBy = rs.getString("Created_by");
                Timestamp lastUpdated = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int divisionId = rs.getInt("Division_ID");
                Customers C = new Customers(customerId, customerName, customerAddress, postalCode, phoneNumber, createdDate, createdBy, lastUpdated, lastUpdatedBy, divisionId);
                customersList.add(C);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return customersList;
    }

    /**
     * Deletes customer information specified by customer id
     * @param id
     * @return false
     */
    public static boolean deleteCustomer(int id) {
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            String queryOne = "DELETE FROM customers WHERE Customer_ID=" + id;
            statement.executeUpdate(queryOne);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static boolean appointmentsCustomer(int id) throws SQLException {
        ObservableList<Appointments> appList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointments WHERE Customer_ID=" + id;
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        appList.clear();


        while(rs.next()){
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end = rs.getTimestamp("End");
            Timestamp createdDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_by");
            Timestamp lastUpdated = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerId = rs.getInt("Customer_ID");
            int contactId = rs.getInt("Contact_ID");
            Appointments C = new Appointments(appointmentId, title, description, location, type, start, end, createdDate, createdBy, lastUpdated, lastUpdatedBy, customerId, contactId);
            appList.add(C);
        }

        return appList.size() > 0;
    }

}
