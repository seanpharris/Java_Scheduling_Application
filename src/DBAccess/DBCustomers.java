package DBAccess;

import DBConnection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;
import model.Customers;
import model.FirstLevelDivisons;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DBCustomers {

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
                String postalCode = rs.getString("Postal_Code");
                String phoneNumber = rs.getString("Phone");
                Timestamp createdDate = rs.getTimestamp("Create_Date");
                String createdBy = rs.getString("Created_by");
                Timestamp lastUpdated = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                String divisionId = rs.getString("Division_ID");
                Customers C = new Customers(customerId, customerName, customerAddress, postalCode, phoneNumber, createdDate, createdBy, lastUpdated, lastUpdatedBy, divisionId);
                customersList.add(C);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return customersList;
    }

    public static void checkDateConversion(){
        String sql = "select Create_Date from customers";
        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Timestamp ts = rs.getTimestamp( "Create_Date");
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
