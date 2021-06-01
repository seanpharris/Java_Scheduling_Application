package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;

public class Users {
    private int userId;
    private String userName;
    private String password;
    private Timestamp createdDate;
    private String createdBy;
    private Timestamp lastUpdated;
    private String lastUpdatedBy;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public String setUserName(String userName) {
        this.userName = userName;
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
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

    public Users(int userId, String userName, String password, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        this.userName = userName;
        this.password = password;
    }

    private ObservableList<Users> allUsers = FXCollections.observableArrayList();

    public boolean isValidLogin(String username, String userPassword)
    {
        if (username.equals(userName) && userPassword.equals(password))
        {
            return true;

        }
        else
            return false;
    }

}
