package appointment;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class appointmentsController implements Initializable {

    @FXML
    private void dashboardWindow(MouseEvent event){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/schedulingApp/dashboard.fxml"));
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
    private void addAppointment(MouseEvent event) throws IOException{

        FXMLLoader loader=new FXMLLoader(getClass().getResource("/appointment/addAppointment.fxml"));
        Parent root=loader.load();
        Scene scene=new Scene(root);
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
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
            FXMLLoader loader=new FXMLLoader(getClass().getResource("modifyAppointment.fxml"));
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

    }
}
