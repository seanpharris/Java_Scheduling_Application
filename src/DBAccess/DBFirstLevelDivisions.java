package DBAccess;

import DBConnection.DBConnection;
import customerWindows.AddCustomerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivisons;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DBFirstLevelDivisions interacts with the database to get the first level divisions information
 */
public class DBFirstLevelDivisions {
    /**
     * gets all FirstLevelDivisons information from the FirstLevelDivisons table
     * @return FirstLevelDivisons table information
     */
    public static ObservableList<String> getAllFirstLevelDivisons(){

        ObservableList<String> firstLevelDivisionList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from first_level_divisions";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int firstLevelDivisionId = rs.getInt( "Division_ID");
                String division = rs.getString("Division");
                Timestamp createdDate = rs.getTimestamp("Create_Date");
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdated = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int countryId = rs.getInt("Country_ID");

                FirstLevelDivisons C = new FirstLevelDivisons(firstLevelDivisionId, division, createdDate, createdBy, lastUpdated, lastUpdatedBy, countryId);
                firstLevelDivisionList.add(String.valueOf(C));
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return firstLevelDivisionList;
    }

    /**
     * list of divisions from each country
     */
    private static final ObservableList<String> divFromCountries = FXCollections.observableArrayList();

    /**
     * updates the divFromCountries list of divisions for the specified country
     * @param country
     * @return division
     */
    public static ObservableList<String> findDiv(String country) {
        try {

            if (country.contains("Canada")) {
                divFromCountries.clear();
                Connection con = DBConnection.getConnection();

                ResultSet rs = con.createStatement().executeQuery("SELECT * FROM first_level_divisions where Country_ID = 38;");

                while (rs.next()){
                    divFromCountries.add((String) rs.getObject("Division"));
                }
                return divFromCountries;

            }
            if (country.contains("United Kingdom")) {
                divFromCountries.clear();
                Connection con = DBConnection.getConnection();

                ResultSet rs = con.createStatement().executeQuery("SELECT * FROM first_level_divisions where Country_ID = 230;");

                while (rs.next()){
                    divFromCountries.add((String) rs.getObject("Division"));
                }
                return divFromCountries;
            }
            if (country.contains("United States")) {
                divFromCountries.clear();
                Connection conn = DBConnection.getConnection();

                ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM first_level_divisions where Country_ID = 231;");

                while (rs.next()){
                    divFromCountries.add((String) rs.getObject("Division"));
                }
                return divFromCountries;
            }

        }catch (SQLException ex) {
            Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return divFromCountries;
    }

    /**
     * gets the div id from the specified div name
     * @param Division_Name
     * @return id
     */
    public static Integer getDivisionId(String Division_Name) {
        String DivisionId = " ";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pst;

            pst = conn.prepareStatement(
                    "select * from first_level_divisions where Division = ?;");
            pst.setString(1, String.valueOf(Division_Name));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DivisionId = String.valueOf(rs.getInt("Division_ID"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Integer.valueOf(DivisionId);
    }


    /**
     * gets the div name from the specified div id
     * @param Division_Name
     * @return div name
     */
    public static String getDivisionName(Integer Division_Name) {
        String DivisionName = " ";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pst;

            pst = conn.prepareStatement(
                    "select * from first_level_divisions where Division_ID = ?;");
            pst.setString(1, String.valueOf(Division_Name));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DivisionName = String.valueOf(rs.getString("Division"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return String.valueOf(DivisionName);
    }

}
