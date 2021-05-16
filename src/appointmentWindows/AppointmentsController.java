package appointmentWindows;

import DBAccess.DBAppointments;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Appointments;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    public TableView appointmentTableview;
    public TableColumn<Object, Object> appointmentIdColumn;

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

    /*@FXML
    private void modAppointment (MouseEvent event) throws IOException{
        Appointment selected=appointmentTableview.getSelectionModel().getSelectedItem();
        if(scheduledAppointments.isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Notice:");
            alert.setHeaderText("No appointments scheduled");
            alert.setContentText("Schedule an appointment");
            alert.showAndWait();
            return;
        }
        if(selected==null){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nothing has been selected");
            alert.setHeaderText("Invalid:");
            alert.setContentText("Make a selection");
            alert.showAndWait();
        }else{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("ModifyAppointment.fxml"));
            modifyAppointmentController controller=new modifyAppointmentController(schedule,selected);

            loader.setController(controller);
            Parent root=loader.load();
            Scene scene=new Scene(root);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
    }*/

    /*@FXML
    private void delPart(MouseEvent event){
        Part remPart=partsTable.getSelectionModel().getSelectedItem();
        if(partInv.isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Notice:");
            alert.setHeaderText("Nothing in stock");
            alert.setContentText("Add a part");
            alert.showAndWait();
            return;
        }
        if(!partInv.isEmpty()&&remPart==null){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nothing has been selected");
            alert.setHeaderText("Invalid:");
            alert.setContentText("Make a selection");
            alert.showAndWait();
            return;
        }else{
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleting part");
            alert.setHeaderText("Deleting:");
            alert.setContentText("Are you sure?");
            Optional<ButtonType> result=alert.showAndWait();
            if(result.isPresent()&&result.get()==ButtonType.OK){
                inv.delPart(remPart);
                partInv.remove(remPart);
                partsTable.refresh();

            }
        }
    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Appointments> appointmentsList = DBAppointments.getAllAppointments();
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        createdDateColumn.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        CreatedByColumn.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        LastUpdatedColumn.setCellValueFactory(new PropertyValueFactory<>("lastUpdated"));
        lastUpdatedByColumn.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        appointmentTableview.setItems(appointmentsList);
        appointmentTableview.refresh();

        System.out.println(appointmentsList);
    }
}
