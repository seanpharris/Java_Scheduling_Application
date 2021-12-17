package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;


/**
 * FirstLevelDivisons defines the appointment information - getters/setters/list/etc.
 */
public class FirstLevelDivisons {

    ObservableList<FirstLevelDivisons> firstLevelDivisionList = FXCollections.observableArrayList();

    /**
     *
     * @return countryId
     */
    public Countries getCountry() {
        return Countries.findCountryId(countryId);
    }


    /**
     *
     * @return divisonName
     */
    @Override
    public String toString() {
        return this.divisonName;
    }

    private int divisionId;

    /**
     *
     * @return divisionId
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     *
     * @param divisionId
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     *
     * @return divisonName
     */
    public String getDivisonName() {
        return divisonName;
    }

    /**
     *
     * @param divisonName
     */
    public void setDivisonName(String divisonName) {
        this.divisonName = divisonName;
    }

    /**
     *
     * @return createdTime
     */
    public Timestamp getCreatedTime() {
        return createdTime;
    }

    /**
     *
     * @param createdTime
     */
    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    /**
     *
     * @return createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     *
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     *
     * @return lastUpdated
     */
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    /**
     *
     * @param lastUpdated
     */
    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     *
     * @return lastUpdatedBy
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     *
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     *
     * @return countryId
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     *
     * @param countryId
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     *
     * @param divisionId
     * @param divisonName
     * @param createdTime
     * @param createdBy
     * @param lastUpdated
     * @param lastUpdatedBy
     * @param countryId
     */
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
