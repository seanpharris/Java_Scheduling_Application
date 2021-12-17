package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Contacts defines the appointment information - getters/setters/list/etc.
 */
public class Contacts {
    private int contactId;
    private String contactName;
    private final String contactEmail;


    public static ObservableList<Contacts> allContacts = FXCollections.observableArrayList();

    public static ObservableList<Contacts> getAllContacts = FXCollections.observableArrayList();

    /**
     *
     * @return allContacts
     */
    public ObservableList<Contacts> getGetAllContacts(){
        return allContacts;
    }

    /**
     *
     * @param contactToAdd
     */
    public void addContact(Contacts contactToAdd) {
        if (contactToAdd != null) {
            allContacts.add(contactToAdd);
        }
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
     * @param contactId
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     *
     * @return contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     *
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     *
     * @param contactId
     * @param contactName
     * @param contactEmail
     */
    public Contacts(int contactId, String contactName, String contactEmail) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    /**
     *
     * @return contactName
     */
    public String toString() {return contactName;}

}


