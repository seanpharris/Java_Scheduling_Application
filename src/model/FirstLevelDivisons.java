package model;

import DBAccess.DBFirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;

public class FirstLevelDivisons {

    ObservableList<FirstLevelDivisons> firstLevelDivisionList = FXCollections.observableArrayList();

    public ObservableList<FirstLevelDivisons> getDivisionByCountry(Countries country) {
        ObservableList<FirstLevelDivisons> temp = FXCollections.observableArrayList();

        int i = 0;
        while (i < firstLevelDivisionList.size()) {
            if (country == firstLevelDivisionList.get(i).getCountry())
                temp.add(firstLevelDivisionList.get(i));
            i++;
        }
        return temp;
    }
    public Countries getCountry() {
        return Countries.findCountryId(countryId);
    }




    @Override
    public String toString() {
        return this.divisonName;
    }

    private int divisionId;

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivisonName() {
        return divisonName;
    }

    public void setDivisonName(String divisonName) {
        this.divisonName = divisonName;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public FirstLevelDivisons(int divisionId, String divisonName, Timestamp createdTime, String createdBy, Timestamp lastUpdated, String lastUpdatedBy, int countryId) {
        this.divisionId = divisionId;
        this.divisonName = divisonName;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryId = countryId;

    }

    private String divisonName;
    private Timestamp createdTime;
    private String createdBy;
    private Timestamp lastUpdated;
    private String lastUpdatedBy;
    private int countryId;


}