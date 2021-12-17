package DBAccess;

import DBConnection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * DBAppointments interacts with the database to get the appointment information
 */
public class DBAppointments {

    public ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();
    /**
     * interacts with the database to get appointment table information
     * @return all appointment information
     */
    public static ObservableList<Appointments> getAllAppointments() {

        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
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
                appointmentsList.add(C);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return appointmentsList;
    }





    public static ObservableList<Appointments> getAppointments() {

        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
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
                appointmentsList.add(C);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return appointmentsList;
    }

    /**
     * interacts with the appointment table to delete specified appointment
     * @param id is queried to for deletion
     * @return false
     */
    public static boolean deleteAppointment(int id) {
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            String queryOne = "DELETE FROM appointments WHERE Appointment_ID=" + id;
            statement.executeUpdate(queryOne);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static ZonedDateTime startTime;
    public static ZonedDateTime endTime;
    /**
     * interacts with the appointment table for start information
     * @return appoinment information queried by start time
     * @throws SQLException if connection fails
     */

    public static List<Appointments> updateAppointmentListByStart() throws SQLException {
        ObservableList<Appointments> appointmentsStartList = FXCollections.observableArrayList();

        startTime = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC"));
        endTime = startTime.plusMinutes(15);

        String begin = startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String endTimer = endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


        String sql = "SELECT * FROM appointments WHERE start > ? AND start < ?;";

        /*String sql = "SELECT * FROM appointments WHERE start > NOW() AND start < NOW() + INTERVAL 15 MINUTE;";*/
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setString(1, begin);
        ps.setString(2, endTimer);



        ResultSet rs = ps.executeQuery();


        appointmentsStartList.clear();


        while (rs.next()) {
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
            appointmentsStartList.add(C);
        }
        return appointmentsStartList;
    }


    /**
     * queries appointment table for appointments that overlap any other appointments
     * @param dateStart is the time of the starting appointment
     * @param dateEnd is the ending time of the appointment
     * @return true/false for if any appointments exist during the specified appointment times
     * @throws SQLException if connection fails
     */
    public static boolean overLap(Timestamp dateStart, Timestamp dateEnd) throws SQLException {


        ObservableList<Appointments> overLapList = FXCollections.observableArrayList();
        overLapList.clear();
        String sql = "SELECT * FROM appointments "
                + "WHERE (start >= ? AND end <= ?) "
                + "OR (start <= ? AND end >= ?) "
                + "OR (start BETWEEN ? AND ? OR end BETWEEN ? AND ?)";

        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setString(1, String.valueOf(dateStart));
        ps.setString(2, String.valueOf(dateEnd));
        ps.setString(3, String.valueOf(dateStart));
        ps.setString(4, String.valueOf(dateEnd));
        ps.setString(5, String.valueOf(dateStart));
        ps.setString(6, String.valueOf(dateEnd));
        ps.setString(7, String.valueOf(dateStart));
        ps.setString(8, String.valueOf(dateEnd));

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
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
            overLapList.add(C);

            if(overLapList.size()>0){
                return true;
            }
        }


        return false;
    }

    public static boolean modOverLap(Timestamp dateStart, Timestamp dateEnd, int appointId) throws SQLException {
        ObservableList<Appointments> overLapList = FXCollections.observableArrayList();
        overLapList.clear();
        String sql = "SELECT * FROM appointments "
                + "WHERE (start >= ? AND end <= ?) "
                + "OR (start <= ? AND end >= ?) "
                + "OR (start BETWEEN ? AND ? OR end BETWEEN ? AND ?)";

        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setString(1, String.valueOf(dateStart));
        ps.setString(2, String.valueOf(dateEnd));
        ps.setString(3, String.valueOf(dateStart));
        ps.setString(4, String.valueOf(dateEnd));
        ps.setString(5, String.valueOf(dateStart));
        ps.setString(6, String.valueOf(dateEnd));
        ps.setString(7, String.valueOf(dateStart));
        ps.setString(8, String.valueOf(dateEnd));
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
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
            overLapList.add(C);

            if(!(appointId == appointmentId) || overLapList.size()>1){
                return true;
            }
        }
        return false;
    }

    /**
     * queries appointment information by month for the radiobutton on the appointment controller
     * @return appointments within a month from now
     */
    public static ObservableList<Appointments> getAppointmentsByMonth() {

        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments where start BETWEEN NOW() AND (SELECT ADDDATE(NOW(), INTERVAL 30 DAY)) ORDER BY Month(start)";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
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
                appointmentsList.add(C);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return appointmentsList;
    }


    /**
     * queries appointment information by week for the radiobutton on the appointment controller
     * @return appointments within a week from now
     */
    public static ObservableList<Appointments> getAppointmentsByWeek() {

        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

        try {
            String sql = "select * from appointments where start BETWEEN NOW() AND (SELECT ADDDATE(NOW(), INTERVAL 7 DAY));";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
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
                appointmentsList.add(C);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return appointmentsList;
    }

}
