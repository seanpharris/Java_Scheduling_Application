package appointmentWindows;

import DBAccess.DBContacts;
import DBConnection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Appointments;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static DBAccess.DBAppointments.overLap;

/**
 * AddAppointmentController controls the add appointment UI
 */

public class AddAppointmentController implements Initializable {
    /**
     * appointmentId text field
     */
    public TextField appointmentIdTxt;
    /**
     * titleTxt text field
     */
    public TextField titleTxt;
    /**
     * descriptionTxt text field
     */
    public TextField descriptionTxt;
    /**
     * locationTxt text field
     */
    public TextField locationTxt;
    /**
     * typeTxt text field
     */
    public TextField typeTxt;
    /**
     * customerIdTxt text field
     */
    public TextField customerIdTxt;
    /**
     * createdByTxt text field
     */
    public TextField createdByTxt;
    /**
     * createDateTxt text field
     */
    public TextField createDateTxt;
    /**
     * updateDateTxt text field
     */
    public TextField updateDateTxt;
    /**
     * updatedByTxt text field
     */
    public TextField updatedByTxt;
    /**
     * saveNewAppointment text field
     */
    public Button saveNewAppointment;
    /**
     * startDate Date picker
     */
    public DatePicker startDate;
    /**
     * endTime choice box
     */
    public ChoiceBox<String> endTime;
    /**
     * contact choice box
     */
    public ChoiceBox<String> contact;
    /**
     * startTime choice box
     */
    public ChoiceBox<String> startTime;
    /**
     * endDate Date picker
     */
    public DatePicker endDate;



    /**
     * backToDashboard is a mouse event for to go back to the main appointment window
     * @param event
     */
    public void backToDashboard (MouseEvent event){
        boolean cancel;
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Click ok to confirm");
        Optional<ButtonType> result=alert.showAndWait();
        if(result.isPresent()&&result.get()==ButtonType.OK) try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/appointmentWindows/Appointments.fxml"));

            Parent root=loader.load();
            Scene scene=new Scene(root);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }



    /**
     * saveNewAppointment is the mouseevent for the save button
     * @param event
     */


    @FXML
    private void saveNewAppointment(MouseEvent event) throws ParseException {



        String startingDate = (String.valueOf(startDate.getValue()));
        String startingTime = String.valueOf(startTime.getValue());
        String combiningStart = (startingDate + " " + startingTime);


        String endingDate = (String.valueOf(endDate.getValue()));
        String endingTime = String.valueOf(endTime.getValue());
        String combiningEnd = (endingDate + " " + endingTime);

        ZonedDateTime zone = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC"));



        Date localTimeStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(combiningStart);

        Date localTimeEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(combiningEnd);

        DateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        s.setTimeZone(TimeZone.getTimeZone("GMT"));


        System.out.println(s.format(localTimeStart));
        System.out.println(s.format(localTimeEnd));

        Timestamp dateStart = Timestamp.valueOf(s.format(localTimeStart));
        Timestamp dateEnd = Timestamp.valueOf(s.format(localTimeEnd));



        try {
            if(overLap(dateStart, dateEnd)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Appointment:");
                alert.setHeaderText("Cannot be scheduled. There is already an appointment at that time.");
                alert.showAndWait();
                return;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        String times = "00:00:00";String times24 = "00:30:00";String times1 = "01:00:00";
        String times25 = "01:30:00";String times2 = "02:00:00";String times26 = "02:30:00";
        String times3 = "03:00:00";String times27 = "03:30:00";String times4 = "04:00:00";
        String times28 = "04:30:00";String times5 = "05:00:00";String times29 = "05:30:00";
        String times6 = "06:00:00";String times30 = "06:30:00";String times7 = "07:00:00";
        String times31 = "07:30:00";String times8 = "08:00:00";String times32 = "08:30:00";
        String times9 = "09:00:00";String times33 = "09:30:00";String times10 = "10:00:00";
        String times34 = "10:30:00";String times11 = "11:00:00";String times35 = "11:30:00";
        String times12 = "12:00:00";String times36 = "12:30:00";String times13 = "13:00:00";
        String times37 = "13:30:00";String times14 = "14:00:00";String times38 = "14:30:00";
        String times15 = "15:00:00";String times39 = "15:30:00";String times16 = "16:00:00";
        String times40 = "16:30:00";String times17 = "17:00:00";String times41 = "17:30:00";
        String times18 = "18:00:00";String times42 = "18:30:00";String times19 = "19:00:00";
        String times43 = "19:30:00";String times20 = "20:00:00";String times44 = "20:30:00";
        String times21 = "21:00:00";String times45 = "21:30:00";String times22 = "22:00:00";
        String times46 = "22:30:00";String times23 = "23:00:00";String times47 = "23:30:00";




        if (endingTime.matches(times22)||(endingTime.matches(times46))||(endingTime.matches(times23))||
                (endingTime.matches(times47))||(endingTime.matches(times))||(endingTime.matches(times24))||
                (endingTime.matches(times1))||(endingTime.matches(times25))||(endingTime.matches(times2))||
                (endingTime.matches(times26))||(endingTime.matches(times3))||(endingTime.matches(times27))||
                (endingTime.matches(times4))||(endingTime.matches(times28))||(endingTime.matches(times5))||
                (endingTime.matches(times29))||(endingTime.matches(times6))||(endingTime.matches(times30))||
                (endingTime.matches(times7))||(endingTime.matches(times31))) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Appointment:");
            alert.setHeaderText("Cannot be scheduled. Please schedule between 08:00 and 22:00.");
            alert.showAndWait();
            return;
        }



        if (titleTxt.getText().isEmpty() || descriptionTxt.getText().isEmpty() || locationTxt.getText().isEmpty() ||
                typeTxt.getText().isEmpty() || contact.getValue() == null || startTime == null ||
                endDate == null || startDate == null || endTime == null || customerIdTxt.getText().isEmpty()){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Missing Appointment Information");
        alert.setHeaderText("Fill in all information");
        alert.showAndWait();
        return;
        }

        if(customerIdTxt.getText().matches("[A-Za-z]")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Customer ID:");
            alert.setHeaderText("Must be numerical");
            alert.showAndWait();
            return;
        }


        Timestamp today = Timestamp.valueOf(dateFormatter.format(dateTime));
        Timestamp appointRequest = Timestamp.valueOf(combiningStart);
        Timestamp endAppointRequest = Timestamp.valueOf(combiningEnd);


        if(appointRequest.equals(null) || endAppointRequest.equals(null) || (appointRequest.before(today) || !appointRequest.before(endAppointRequest))){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Appointment:");
            alert.setHeaderText("Cannot be scheduled. Please schedule for the future.");
            alert.showAndWait();
            return;
        }

        int appointmentId = Integer.parseInt(appointmentIdTxt.getText());
        String title = titleTxt.getText();
        String description = descriptionTxt.getText();
        String location = locationTxt.getText();
        String type = typeTxt.getText();
        Timestamp start = Timestamp.valueOf(zone.format(DateTimeFormatter.ofPattern(combiningStart)));
        Timestamp end = Timestamp.valueOf(zone.format(DateTimeFormatter.ofPattern(combiningEnd)));
        Timestamp createDate = Timestamp.valueOf(zone.format(DateTimeFormatter.ofPattern(createDateTxt.getText())));
        String createdBy = createdByTxt.getText();
        Timestamp updateDate = Timestamp.valueOf(zone.format(DateTimeFormatter.ofPattern(updateDateTxt.getText())));
        String updatedBy = updatedByTxt.getText();
        int custId = Integer.parseInt(customerIdTxt.getText());
        int contactId = DBContacts.getContactId(String.valueOf(contact.getValue()));

       Appointments appoint = new Appointments(appointmentId, title, description, location, type, start, end, createDate, createdBy, updateDate, updatedBy, custId, contactId);


        try {
            String sql = "INSERT INTO appointments (Appointment_Id, Title, Description, Location, Type, Contact_ID, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = DBConnection.conn.prepareStatement(sql);

               ps.setInt(1, Integer.parseInt(String.valueOf(appoint.getAppointmentId())));
               ps.setString(2, appoint.getTitle());
               ps.setString(3, appoint.getDescription());
               ps.setString(4, appoint.getLocation());
               ps.setString(5, appoint.getType());
               ps.setInt(6, appoint.getContactId());
               ps.setTimestamp(7, appoint.getStart());
               ps.setTimestamp(8, appoint.getEnd());
               ps.setTimestamp(9, appoint.getCreateDate());
               ps.setString(10, appoint.getCreatedBy());
               ps.setTimestamp(11, appoint.getLastUpdated());
               ps.setString(12, appoint.getLastUpdatedBy());
               ps.setInt(13, Integer.parseInt(String.valueOf(appoint.getCustomerId())));

            ps.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Appointments.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * createCustomerId creates the appointmentID assigned to each appointment
     * @return
     */

    public static String createCustomerId() {
        UUID uuid = UUID.randomUUID();

        return uuid.toString().substring(0, 10).replaceAll("[^0-9]", "");

    }


    /**
     * date time call for the local time
     */
    static LocalDateTime dateTime = LocalDateTime.now();

    /**
     * dateformatter formats the date
     */
    public static final DateTimeFormatter dateFormatter
                = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * timesForAppointments are the choices for the start/end choice boxes
     * @return
     */

    public static ObservableList <String> timesForAppointments() {
        ObservableList<String> startingTimes = FXCollections.observableArrayList();
        {
            String times = "00:00:00";String times24 = "00:30:00";String times1 = "01:00:00";
            String times25 = "01:30:00";String times2 = "02:00:00";String times26 = "02:30:00";
            String times3 = "03:00:00";String times27 = "03:30:00";String times4 = "04:00:00";
            String times28 = "04:30:00";String times5 = "05:00:00";String times29 = "05:30:00";
            String times6 = "06:00:00";String times30 = "06:30:00";String times7 = "07:00:00";
            String times31 = "07:30:00";String times8 = "08:00:00";String times32 = "08:30:00";
            String times9 = "09:00:00";String times33 = "09:30:00";String times10 = "10:00:00";
            String times34 = "10:30:00";String times11 = "11:00:00";String times35 = "11:30:00";
            String times12 = "12:00:00";String times36 = "12:30:00";String times13 = "13:00:00";
            String times37 = "13:30:00";String times14 = "14:00:00";String times38 = "14:30:00";
            String times15 = "15:00:00";String times39 = "15:30:00";String times16 = "16:00:00";
            String times40 = "16:30:00";String times17 = "17:00:00";String times41 = "17:30:00";
            String times18 = "18:00:00";String times42 = "18:30:00";String times19 = "19:00:00";
            String times43 = "19:30:00";String times20 = "20:00:00";String times44 = "20:30:00";
            String times21 = "21:00:00";String times45 = "21:30:00";String times22 = "22:00:00";
            String times46 = "22:30:00";String times23 = "23:00:00";String times47 = "23:30:00";

            startingTimes.add(times);
            startingTimes.add(times24);
            startingTimes.add(times1);
            startingTimes.add(times25);
            startingTimes.add(times2);
            startingTimes.add(times26);
            startingTimes.add(times3);
            startingTimes.add(times27);
            startingTimes.add(times4);
            startingTimes.add(times28);
            startingTimes.add(times5);
            startingTimes.add(times29);
            startingTimes.add(times6);
            startingTimes.add(times30);
            startingTimes.add(times7);
            startingTimes.add(times31);
            startingTimes.add(times8);
            startingTimes.add(times32);
            startingTimes.add(times9);
            startingTimes.add(times33);
            startingTimes.add(times10);
            startingTimes.add(times34);
            startingTimes.add(times11);
            startingTimes.add(times35);
            startingTimes.add(times12);
            startingTimes.add(times36);
            startingTimes.add(times13);
            startingTimes.add(times37);
            startingTimes.add(times14);
            startingTimes.add(times38);
            startingTimes.add(times15);
            startingTimes.add(times39);
            startingTimes.add(times16);
            startingTimes.add(times40);
            startingTimes.add(times17);
            startingTimes.add(times41);
            startingTimes.add(times18);
            startingTimes.add(times42);
            startingTimes.add(times19);
            startingTimes.add(times43);
            startingTimes.add(times20);
            startingTimes.add(times44);
            startingTimes.add(times21);
            startingTimes.add(times45);
            startingTimes.add(times22);
            startingTimes.add(times46);
            startingTimes.add(times23);
            startingTimes.add(times47);
        }

        return startingTimes;
    }

    /**
     * initializer loads the information for the UI
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentIdTxt.setText(createCustomerId());
        createDateTxt.setText(dateFormatter.format(dateTime));
        updateDateTxt.setText(dateFormatter.format(dateTime));


        contact.setItems(DBContacts.getAllContacts());

        startTime.setItems(timesForAppointments());
        endTime.setItems(timesForAppointments());
        createdByTxt.setText(Users.getUserName());
        updatedByTxt.setText(Users.getUserName());


/**
 * reformats the time
 */
        startDate.setConverter(new StringConverter<LocalDate>() {
            final String pattern = "yyyy-MM-dd";
            final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            {
                startDate.setPromptText(pattern.toLowerCase());
            }

            @Override public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
/**
 * reformats the time
 */
        endDate.setConverter(new StringConverter<LocalDate>() {
            final String pattern = "yyyy-MM-dd";
            final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
            {
                endDate.setPromptText(pattern.toLowerCase());
            }
            @Override public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

    }

}
