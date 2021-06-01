package DBAccess;

import DBConnection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;



public class DBAppointments {

    public static ObservableList<Appointments> getAllAppointments(){

        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int appointmentId = rs.getInt( "Appointment_ID");
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
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                Appointments C = new Appointments(appointmentId, title, description, location, type, start, end, createdDate, createdBy, lastUpdated, lastUpdatedBy, customerId, userId, contactId);
                appointmentsList.add(C);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return appointmentsList;
    }
}
