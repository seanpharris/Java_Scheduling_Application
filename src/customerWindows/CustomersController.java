package customerWindows;

import DBAccess.DBCustomers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class CustomersController implements Initializable {

    public TextField customerSearch;
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
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/schedulingApp/Dashboard.fxml"));
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
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/customerWindows/AddCustomer.fxml"));
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

    private final ObservableList<Customers> customersSearch=FXCollections.observableArrayList();

    Customers customers;

    public void searchCustomers(KeyEvent keyEvent){
        if(!customerSearch.getText().isEmpty()){
            customersSearch.clear();
            for(Customers customers : customers.getAllCustomers()){
                if(customers.getCustomerName().contains(customerSearch.getText())||((String.valueOf(customers.getCustomerId()).contains(customerSearch.getText())))){
                    customersSearch.add(customers);
                }
            }
            customerTableview.setItems(customersSearch);
            customerTableview.refresh();
            if (customersSearch.isEmpty()){
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Notice:");
                alert.setHeaderText("Search not found");
                alert.setContentText("Search for another product");
                alert.showAndWait();
            }
        }
        if(customerSearch.getText().isEmpty()){
            customerTableview.setItems(customers.getAllCustomers());
        }
    }
    
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

    }
}
