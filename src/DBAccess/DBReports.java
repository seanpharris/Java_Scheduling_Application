package DBAccess;

import DBConnection.DBConnection;
import model.Appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * DBUsers interacts with the database to get the users information
 */
public class DBReports extends Appointments {
    public DBReports(int appointmentId, String title, String description, String location, String type, Timestamp start, Timestamp end, Timestamp createDate, String createdBy, Timestamp lastUpdated, String lastUpdatedBy, int customerId, int contactId) {
        super(appointmentId, title, description, location, type, start, end, createDate, createdBy, lastUpdated, lastUpdatedBy, customerId, contactId);
    }

    /**
     * gets the count of appointments by type and month
     * @return "Total of 'int' Type: 'string' Month: 'string' Year: 'string'"
     * @throws SQLException if connection fails
     */
    public static String typeMonthReport() throws SQLException {
        String sql = "SELECT COUNT(Appointment_ID) AS \"Count\", `Type` , MONTH(`Start`) AS \"Month\", YEAR(`Start`) AS \"Year\" FROM appointments a GROUP BY Year, Month, `Type`;";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        String report = "";
        while(rs.next()){
            report += "Total of " + rs.getString(1) + "\t\tType: " + rs.getString(2) + "\t\tMonth: " + rs.getString(3) + " \t\tYear: " + rs.getString(4) + "\n";
        }

        return report;
    }





    public static String convertCurrent(String Date) {
        String converted_date = "";
        try {

            DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date date = utcFormat.parse(Date);

            DateFormat currentTFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            currentTFormat.setTimeZone(TimeZone.getTimeZone(getCurrent()));

            converted_date =  currentTFormat.format(date);
        }catch (Exception e){ e.printStackTrace();}

        return converted_date;
    }


    //get the current time zone

    public static String getCurrent(){
        TimeZone t = Calendar.getInstance().getTimeZone();
        return t.getID();
    }

    /**
     * gets scheduled appointment for each contact
     * @return "Contact 'string' Appointment ID: 'int' Title: 'string' Description: 'string' Start: 'String' End: 'String' CustomerID 'int'"
     * @throws SQLException if connection fails
     */
    public static String contactSchedule() throws SQLException{

        String sql = "SELECT Contact_Name, Appointment_ID, Title, `Type`, Description, `Start`, `End`, Customer_ID \n" +
                "FROM appointments\n" +
                "INNER JOIN contacts USING (Contact_ID)\n" +
                "ORDER BY Contact_Name, Start";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();



        String report = "";
        while(rs.next()){
            report += "Contact: " + rs.getString(1) + "\t\tAppointment ID: " + rs.getString(2) + "\t\tTitle: " + rs.getString(3) + "\t\tType: " + rs.getString(4) + "\t\tDescription: " + rs.getString(5)
                    + "\t\tStart: " + convertCurrent(rs.getString(6)) + "\t\tEnd: " + convertCurrent(rs.getString(7)) + "\t\tCustomer ID: " + rs.getString(8) + "\n";
        }


        if(report.isEmpty()) report = "No appointments.";

        return report;
    }

    /**
     * gets scheduled appointments by title and day
     * @return "'Int' Title: 'String' Day: 'string' Month: 'string'"
     * @throws SQLException if connection fails
     */
    public static String titleDay() throws SQLException {
        String sql = "SELECT COUNT(Appointment_ID) AS \"Count\", `Title` , DAY(`Start`) AS \"Day\", MONTH(`Start`) AS \"Month\" FROM appointments a GROUP BY Month, Day, `Title`;";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        String report = "";
        while(rs.next()){
            report += " " + rs.getString(1) + "\t\tTitle: " + rs.getString(2) + "\t\tDay: " + rs.getString(3) + " \t\tMonth: " + rs.getString(4) + "\n";
        }

        return report;
    }
}
