package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Countries defines the appointment information - getters/setters/list/etc.
 */
public class Countries {

    public static ObservableList<Countries> countryList = FXCollections.observableArrayList();

    private int countryId;

    private String countryName;

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
     * @param countryName
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     *
     * @param countryId
     * @param countryName
     */
    public Countries(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    /**
     *
     * @param id
     * @return countryid
     */
    public static Countries findCountryId(int id) {
        int i = 0;
        while (i < countryList.size()) {
            if (id == countryList.get(i).countryId)
                return countryList.get(i);
            i++;
        }
        return null;
    }

    /**
     *
     * @return countryName
     */
    @Override
    public String toString() {
        return this.countryName;
    }




}
