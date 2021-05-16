package schedulingApp;

import DBAccess.DBAppointments;
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
import model.Customers;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    public TableColumn<Object, Object> locationColumn;
    public TableColumn<Object, Object> startTimeColumn;
    public TableColumn<Object, Object> appointmentIdColumn;
    public TableView<Appointments> appointmentTableview;
    public TextField locationTxt;
    public TextField userTxt;
    private ZoneId userZoneId;
    Customers customers;

    @FXML
    private void logOut(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logging out");
        alert.setHeaderText("You're about to log out");
        alert.setContentText("Are you sure?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK)
            try{
                FXMLLoader loader=new FXMLLoader(getClass().getResource("Logon.fxml"));
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

    @FXML
    private void customersWindow(MouseEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../customerWindows/CustomersWindow.fxml"));
        Parent root=loader.load();
        Scene scene=new Scene(root);
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
    @FXML
    private void appointmentsWindow(MouseEvent event){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("../appointmentWindows/Appointments.fxml"));
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


    Users users;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userZoneId = ZoneId.systemDefault();
        ObservableList<Appointments> appointmentsList = DBAppointments.getAllAppointments();
        
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        
        appointmentTableview.setItems(appointmentsList);
        appointmentTableview.refresh();



        locationTxt.setText(userZoneId.getDisplayName(TextStyle.FULL, Locale.getDefault()));
        //userTxt.setText(users.getUserName());
    }
}
