package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;

public class Countries {

    public static ObservableList<Countries> countryList = FXCollections.observableArrayList();

    private int countryId;

    private String countryName;

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Countries(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public static Countries findCountryId(int id) {
        int i = 0;
        while (i < countryList.size()) {
            if (id == countryList.get(i).countryId)
                return countryList.get(i);
            i++;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.countryName;
    }
}
