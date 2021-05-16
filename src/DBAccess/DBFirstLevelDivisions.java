package DBAccess;

import DBConnection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;
import model.FirstLevelDivisons;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DBFirstLevelDivisions {

    public static ObservableList<FirstLevelDivisons> getAllFirstLevelDivisons(){

        ObservableList<FirstLevelDivisons> firstLevelDivisionList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from firstLevelDivisions";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int firstLevelDivisionId = rs.getInt( "Division_ID");
                String division = rs.getString("Division");
                Timestamp createdDate = rs.getTimestamp("Created_Date");
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdated = rs.getTimestamp("Last_Updated");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int countryId = rs.getInt("Country_ID");

                FirstLevelDivisons C = new FirstLevelDivisons(firstLevelDivisionId, division, createdDate, createdBy, lastUpdated, lastUpdatedBy, countryId);
                firstLevelDivisionList.add(C);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return firstLevelDivisionList;
    }

    public static ObservableList<FirstLevelDivisons> getAvailableFirstLevelDivisons(){

        ObservableList<FirstLevelDivisons> availableFirstLevelDivisionList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from firstLevelDivisions";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int firstLevelDivisionId = rs.getInt( "Division_ID");
                String division = rs.getString("Division");
                Timestamp createdDate = rs.getTimestamp("Created_Date");
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdated = rs.getTimestamp("Last_Updated");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int countryId = rs.getInt("Country_ID");

                FirstLevelDivisons C = new FirstLevelDivisons(firstLevelDivisionId, division, createdDate, createdBy, lastUpdated, lastUpdatedBy, countryId);
                availableFirstLevelDivisionList.add(C);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return availableFirstLevelDivisionList;
    }


}
