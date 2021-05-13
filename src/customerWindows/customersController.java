package customerWindows;

import DBAccess.DBCustomers;
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
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class customersController implements Initializable {

    @FXML
    private TableColumn<Customers, String> customerNameColumn;
    @FXML
    private TableColumn<Customers, String> customerAddressColumn;
    @FXML
    private TableColumn<Customers, String> customerPostalCodeColumn;
    @FXML
    private TableColumn<Customers, String> customerPhoneColumn;
    @FXML
    private TableColumn<Customers, Timestamp> createdDateColumn;
    @FXML
    private TableColumn<Customers, String> createdByColumn;
    @FXML
    private TableColumn<Customers, Timestamp> updatedTimeColumn;
    @FXML
    private TableColumn<Customers, String> updatedByColumn;
    @FXML
    private TableColumn<Customers, Integer> divisionIdColumn;
    @FXML
    private TableColumn<Customers, Integer> customerIdColumn;
    @FXML
    private TableView<Customers> customerTableview;

    @FXML
    private void dashboardWindow(MouseEvent event){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/mainWindows/dashboard.fxml"));
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
    private void addContact(MouseEvent event){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/customerWindows/addCustomer.fxml"));
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



    /*public customersController(Customers customers){
        this.customers = customers;
    }*/

    Customers customers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Customers> customersList = DBCustomers.getAllCustomers();
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customerPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        createdDateColumn.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
        createdByColumn.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        updatedTimeColumn.setCellValueFactory(new PropertyValueFactory<>("lastUpdated"));
        updatedByColumn.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        divisionIdColumn.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
        customerTableview.setItems(customersList);
        customerTableview.refresh();

        System.out.println(customersList);

    }
}
