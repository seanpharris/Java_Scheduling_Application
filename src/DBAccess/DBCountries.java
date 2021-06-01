package DBAccess;

import DBConnection.DBConnection;
import com.mysql.cj.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public static ObservableList<Countries> getAvailableCountries(){

        ObservableList<Countries> availableCountryList = FXCollections.observableArrayList();

        try {
            String sql = "select * from countries\n" +
                    "where Country_ID = 38 OR Country_ID = 230 OR Country_ID = 231;";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int countryId = rs.getInt( "Country_ID");
                String countryName = rs.getString("Country");
                Countries C = new Countries(countryId, countryName);
                availableCountryList.add(C);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return availableCountryList;
    }


    public static void checkDateConversion(){
        String sql = "select Create_Date from countries";
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
