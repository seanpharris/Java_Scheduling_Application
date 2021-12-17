package DBAccess;

import DBConnection.DBConnection;
import com.mysql.cj.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * DBCountries interacts with the database to get the Countries information
 */
public class DBCountries {

    public static ObservableList<Countries> getAllCountries(){

        ObservableList<Countries> countryList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from countries";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int countryId = rs.getInt( "Country_ID");
                String countryName = rs.getString("Country");
                Countries C = new Countries(countryId, countryName);
                countryList.add(C);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return countryList;
    }

    /**
     * gets countries that are available
     * @return countries
     */
    public static ObservableList<String> getAvailableCountries(){

        ObservableList<String> availableCountryList = FXCollections.observableArrayList();

        try {
            String sql = "select * from countries\n" +
                    "where Country_ID = 38 OR Country_ID = 230 OR Country_ID = 231;";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int countryId = rs.getInt( "Country_ID");
                String countryName = rs.getString("Country");
                Countries C = new Countries(countryId, countryName);
                availableCountryList.add(String.valueOf(C));
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return availableCountryList;
    }

    /**
     * gets country id associated with the country name
     * @param Country
     * @return countryID
     */
    public static String getChosenCountry(Integer Country) {
        String CountryId = " ";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pst;

            pst = conn.prepareStatement(
                    "select Country_ID from first_level_divisions where Division_ID = ?;");
            pst.setString(1, String.valueOf(Country));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CountryId = String.valueOf(rs.getString("Country_ID"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return String.valueOf(CountryId);
    }

    /**
     * gets country name associated with the countryID
     * @param Country
     * @return countryID
     */
    public static String getChosenCountryName(String Country) {
        String CountryName = " ";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pst;

            pst = conn.prepareStatement(
                    "select * from countries where Country_ID = ?;");
            pst.setString(1, String.valueOf(Country));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CountryName = String.valueOf(rs.getString("Country"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return String.valueOf(CountryName);
    }

}
