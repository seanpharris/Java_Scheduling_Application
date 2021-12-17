package appointmentWindows;

import DBAccess.DBAppointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * AppointmentController class control the appointments UI
 */
public class AppointmentsController implements Initializable {

    public TableColumn<Object, Object> CreatedByColumn;
    public TableColumn<Object, Object> createdDateColumn;
    public TableColumn<Object, Object> LastUpdatedColumn;
    public TableColumn<Object, Object> lastUpdatedByColumn;
    public TableColumn<Object, Object> contactColumn;
    public TableColumn<Object, Object> customerColumn;
    public TableColumn<Object, Object> endTimeColumn;
    public TableColumn<Object, Object> userColumn;
    public TableColumn<Object, Object> startTimeColumn;
    public TableColumn<Object, Object> typeColumn;
    public TableColumn<Object, Object> locationColumn;
    public TableColumn<Object, Object> descriptionColumn;
    public TableColumn<Object, Object> titleColumn;
    public TableView<Appointments> appointmentTableview;
    public TableColumn<Object, Object> appointmentIdColumn;
    public RadioButton sortByMonth;
    public RadioButton sortByWeek;
    public RadioButton none;


    /**
     * backToDashboard is a mouse event for to go back to the main appointment window
     * @param event
     */
    @FXML
    private void dashboardWindow(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/schedulingApp/Dashboard.fxml"));
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
     * addAppointment button event that goes to the add appointment UI
     * @param event
     * @throws IOException
     */
    

    @FXML
    private void addAppointment(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/appointmentWindows/AddAppointment.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * modifyAppointment button event that goes to the modify appointment UI
     * @param event
     * @throws IOException
     */

    @FXML
    private void modifyAppointment(MouseEvent event) throws IOException{
        Appointments selectedAppointment = appointmentTableview.getSelectionModel().getSelectedItem();
        if(selectedAppointment==null){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nothing has been selected");
            alert.setHeaderText("Invalid:");
            alert.setContentText("Make a selection");
            alert.showAndWait();
        }else{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("ModifyAppointment.fxml"));
            ModifyAppointmentController controller = new ModifyAppointmentController(selectedAppointment);

            loader.setController(controller);
            Parent root=loader.load();
            Scene scene=new Scene(root);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
    }

    /**
     * * ******LAMBDA EXPRESSION*******
     *      *The delAppoint contains a lambda expression used to pass the final selected appointment on demand.
     *      *The variable finalSelectAppoint is used to call back the selected customer from the tableview to be deleted.
     *      *This makes for an easy stream line instead of making a new variable.
     *      *
     *      *
     * delAppointment is the delete button and deletes the appointment
     * @param event
     */
    @FXML
    private void delAppointment(MouseEvent event) {
        Appointments selectedAppoint;
        if (appointmentTableview.getSelectionModel().getSelectedItem() != null) {
            selectedAppoint = appointmentTableview.getSelectionModel().getSelectedItem();
        } else {
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Canceling Appointment");
        alert.setHeaderText("Are you sure you want to cancel");
        alert.setContentText("Appointment: ID: " + selectedAppoint.getAppointmentId() + " Type: " + selectedAppoint.getType() + " ?");
        Appointments finalSelectedAppoint = selectedAppoint;
        Appointments finalSelectedAppoint1 = selectedAppoint;
        alert.showAndWait().ifPresent((response -> {
            /**
             * ******LAMBDA EXPRESSION*******
             * finalSelectedAppoint as variable
             */
            if (response == ButtonType.OK) {
                DBAppointments.deleteAppointment(finalSelectedAppoint.getAppointmentId());
                appointmentTableview.setItems(DBAppointments.getAllAppointments());
            }else{
                return;
            }
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Canceled");
            alert1.setHeaderText("Appointment:");
            alert1.setContentText("ID: " + finalSelectedAppoint1.getAppointmentId() + " Type: " + finalSelectedAppoint1.getType() + " has been deleted.");
            alert1.showAndWait();
        }));

    }

    /**
     * initializer loads the information for the UI
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentTableview.refresh();
        ObservableList<Appointments> appointmentsList = DBAppointments.getAllAppointments();
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        createdDateColumn.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        CreatedByColumn.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        LastUpdatedColumn.setCellValueFactory(new PropertyValueFactory<>("lastUpdated"));
        lastUpdatedByColumn.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        appointmentTableview.setItems(appointmentsList);
        //appointmentTableview.refresh();

        none.setSelected(true);
    }

    /**
     * sortByMonth is the radiobutton for the month sort
     * @param mouseEvent
     */
    public void sortByMonth(MouseEvent mouseEvent) {
        none.setSelected(false);
        sortByWeek.setSelected(false);
        appointmentTableview.setItems(DBAppointments.getAppointmentsByMonth());

    }
    /**
     * sortByWeek is the radiobutton for the week sort
     * @param mouseEvent
     */
    public void sortByWeek(MouseEvent mouseEvent) {
        none.setSelected(false);
        sortByMonth.setSelected(false);
        appointmentTableview.setItems(DBAppointments.getAppointmentsByWeek());
    }
    /**
     * none is the radiobutton for no sort
     * @param mouseEvent
     */
    public void none(MouseEvent mouseEvent) {
        sortByWeek.setSelected(false);
        sortByMonth.setSelected(false);
        appointmentTableview.refresh();
        appointmentTableview.setItems(DBAppointments.getAllAppointments());
    }
}
