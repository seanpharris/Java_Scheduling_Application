package model;

import customerWindows.ModifyCustomerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;


/**
 * Customers defines the appointment information - getters/setters/list/etc.
 */
public class Customers {

    private final ObservableList<Customers> allCustomers = FXCollections.observableArrayList();

    public static ObservableList<Customers> findCustomers = FXCollections.observableArrayList();

    public Customers(String string, String string1) {
    }

    /**
     *
     * @return allCustomers
     */
    public ObservableList<Customers> getAllCustomers(){
        return allCustomers;
    }

    /**
     *
     * @return customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     *
     * @param customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     *
     * @return customerName
     */
    public  String getCustomerName() {
        return customerName;
    }

    /**
     *
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     *
     * @return customerAddress
     */
    public  String getCustomerAddress() {
        return customerAddress;
    }

    /**
     *
     * @param customerAddress
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     *
     * @return postalCode
     */
    public  int getPostalCode() {
        return postalCode;
    }

    /**
     *
     * @param postalCode
     */
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    /**
     *
     * @return phoneNumber
     */
    public  int getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @return divisionId
     */
    public int getDivisionId() {
        return this.divisionId;
    }

    /**
     *
     * @param divisionId
     */
    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

    /**
     *
     * @return createdDate
     */
    public  Timestamp getCreatedDate() {
        return createdDate;
    }

    /**
     *
     * @param createdDate
     */
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    /**
     *
     * @return createdBy
     */
    public  String getCreatedBy() { return createdBy; }

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

    ModifyCustomerController modifyCustomerController;


    /**
     *
     * @param customerId
     * @param customerName
     * @param customerAddress
     * @param postalCode
     * @param phoneNumber
     * @param createdDate
     * @param createdBy
     * @param lastUpdated
     * @param lastUpdatedBy
     * @param divisionId
     */
    public Customers(int customerId, String customerName, String customerAddress, int postalCode, int phoneNumber, Timestamp createdDate, String createdBy, Timestamp lastUpdated, String lastUpdatedBy, Integer divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionId = divisionId;
    }
    private int customerId;
    private String customerName;
    private String customerAddress;
    private int postalCode;
    private int phoneNumber;
    private Timestamp createdDate;
    private String createdBy;
    private Timestamp lastUpdated;
    private String lastUpdatedBy;
    private Integer divisionId;

}
