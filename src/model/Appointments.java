package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;


/**
 * Appointments defines the appointment information - getters/setters/list/etc.
 */
public class Appointments {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private Timestamp start;
    private Timestamp end;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdated;
    private String lastUpdatedBy;
    private int customerId;
    private int contactId;


    /**
     *
     * @param appointmentId
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param createDate
     * @param createdBy
     * @param lastUpdated
     * @param lastUpdatedBy
     * @param customerId
     * @param contactId
     */
    public Appointments(int appointmentId, String title, String description, String location, String type, Timestamp start, Timestamp end, Timestamp createDate, String createdBy, Timestamp lastUpdated, String lastUpdatedBy, int customerId, int contactId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerId = customerId;
        this.contactId = contactId;

    }



    public static Appointments appoint;


    public ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

    /**
     *
     * @return appointmentsList
     */


    public ObservableList<Appointments> getGetAllAppointments(){
        return appointmentsList;
    }

    /**
     *
     * @param appointmentToAdd
     */

    public void addAppointment(Appointments appointmentToAdd) {
        if (appointmentToAdd != null) {
            appointmentsList.add(appointmentToAdd);
        }
    }

    /**
     * getter for appointmentId
     * @return
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * setter for appointmentId
     * @param appointmentId
     */
    public void setAppointmentId(int appointmentId){
        this.appointmentId = appointmentId;
    }

    /**
     * getter for title
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * setter for title
     * @param title
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     *
     * @return description
     */

    public String getDescription(){
        return description;
    }

    /**
     *
     * @param description description
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     *
     * @return location
     */
    public String getLocation(){
        return location;
    }

    /**
     *
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }


    /**
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @return start
     */
    public Timestamp getStart() {
        return start;
    }

    /**
     *
     * @return end
     */
    public Timestamp getEnd() {
        return end;
    }

    /**
     *
     * @return createDate
     */
    public Timestamp getCreateDate() {
        return createDate;
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
     * @return lastUpdated
     */
    public Timestamp getLastUpdated() {
        return lastUpdated;
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
     * @return contactId
     */
    public int getCustomerId() {
        return customerId;
    }


    /**
     *
     * @return contactId
     */
    public int getContactId() {
        return contactId;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @param start
     */
    public void setStart(Timestamp start) {
        this.start = start;
    }

    /**
     *
     * @param end
     */
    public void setEnd(Timestamp end) {
        this.end = end;
    }

    /**
     *
     * @param createDate
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
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
     * @param lastUpdated
     */

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
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
     * @param customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     *
     * @param contactId
     */

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }


}
