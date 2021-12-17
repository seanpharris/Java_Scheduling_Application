package schedulingApp;

import DBAccess.DBAppointments;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Appointments;
import DBAccess.DBReports;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import static DBAccess.DBReports.typeMonthReport;
import static model.Appointments.appoint;

/**
 * DashboardController controls the add appointment UI
 */
public class DashboardController implements Initializable {

    public TableColumn<Object, Object> locationColumn;
    public TableColumn<Appointments, LocalDateTime> startTimeColumn;
    public TableColumn<Object, Object> appointmentIdColumn;
    public TableView<Appointments> appointmentTableview;
    public Label locationTxt;
    public Label typeMonth;
    public Label thisLabel;
    public Label schedLabel;
    public Label timeLabel;
    private ZoneId userZoneId;

    /**
     * logout takes the user back to the login screen and os prompted first
     *
     * @param event
     */
    @FXML
    private void logOut(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logging out");
        alert.setHeaderText("You're about to log out");
        alert.setContentText("Are you sure?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK)
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Logon.fxml"));
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
     * customersWindow button event that goes to the main customers UI
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void customersWindow(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../customerWindows/CustomersWindow.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    /**
     * appointmentsWindow button event that goes to the main appointments UI
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void appointmentsWindow(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../appointmentWindows/Appointments.fxml"));
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

    public static ZonedDateTime startTime;
    public static ZonedDateTime endTime;

    LocalDateTime dateTime = LocalDateTime.now();
    public static final DateTimeFormatter timeFormatter
            = DateTimeFormatter.ofPattern("HH:mm:ss");

    Appointments appointments;

    /**
     * initializer loads the information for the UI
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userZoneId = ZoneId.systemDefault();
        ObservableList<Appointments> appointmentsList = DBAppointments.getAllAppointments();
        List<Appointments> appointmentsStartList = null;
        try {
            appointmentsStartList = DBAppointments.updateAppointmentListByStart();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        appointmentTableview.setItems(appointmentsList.sorted());
        appointmentTableview.refresh();

        try {
            typeMonth.setText(typeMonthReport());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            schedLabel.setText(DBReports.contactSchedule());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            thisLabel.setText(DBReports.titleDay());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        locationTxt.setText(userZoneId.getDisplayName(TextStyle.FULL, Locale.getDefault()));
        timeLabel.setText(timeFormatter.format(dateTime));


        startTime = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC"));
        endTime = startTime.plusMinutes(15);

        String begin = startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String end = endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.println(begin);
        System.out.println(dateTime);
        System.out.println(DBAppointments.getAppointments());

        try {
            DBAppointments.updateAppointmentListByStart();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        try {
            if (DBAppointments.updateAppointmentListByStart().size() > 0) {


                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Upcoming Appointments");
                    alert.setHeaderText("Upcoming Appointment in 15 minutes:");
                    alert.setContentText("Id: " + appointmentsList.get(0).getAppointmentId() + " Starts at: " + appointmentsList.get(0).getStart());
                    alert.showAndWait();

                } else {
                    System.out.println("No appointments");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Upcoming Appointments");
                alert.setHeaderText("No Upcoming Appointments");
                alert.showAndWait();
                }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    }

