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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

/**
 * CustomersController controls the add appointment UI
 */
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
    protected TableView<Customers> customerTableview;


    /**
     * backToDashboard is a mouse event for to go back to the main appointment window
     * @param event
     */
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
    /**
     * addContact button event that goes to the add customer UI
     * @param event
     * @throws IOException
     */
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

    private final ObservableList<Customers> customersSearch = FXCollections.observableArrayList();

    /**
     * searchCustomer controls the search bar to search for customers
     * @param keyEvent
     */

    public void searchCustomers(KeyEvent keyEvent){
        if(!customerSearch.getText().isEmpty()){
            customersSearch.clear();
            for(Customers customers : DBCustomers.getAllCustomers()){
                if(customers.getCustomerName().contains(customerSearch.getText())||((String.valueOf(customers.getCustomerId()).contains(customerSearch.getText())))){
                    customersSearch.add(customers);
                }
            }
            customerTableview.setItems(customersSearch);
            customerTableview.refresh();
            if (customersSearch.isEmpty()){
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Notice:");
                alert.setHeaderText("Customer not found");
                alert.setContentText("Search for another customer");
                alert.showAndWait();
            }
        }
        if(customerSearch.getText().isEmpty()){
            customerTableview.setItems(DBCustomers.getAllCustomers());
        }
    }
    /**
     * initializer loads the information for the UI
     * @param url
     * @param resourceBundle
     */
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

    /**
     * ******LAMBDA EXPRESSION*******
     *The del customer contains a lambda expression used to pass the final selected customer on demand.
     *The variable finalSelectCust is used to call back the selected customer from the tableview to be deleted.
     *This makes for an easy stream line instead of making a new variable.
     *
     *
     * delCustomer is the delete button and deletes the customer
     * @param event
     */
    @FXML
    private void delCustomer(MouseEvent event){
        Customers selectedCust;
        if(customerTableview.getSelectionModel().getSelectedItem() != null) {
            selectedCust = customerTableview.getSelectionModel().getSelectedItem();
        } else {
            return;
        }
        AtomicReference<Alert> alert = new AtomicReference<>(new Alert(Alert.AlertType.CONFIRMATION));
        alert.get().setTitle("Delete");
        alert.get().setHeaderText("Delete Customer Record");
        alert.get().setContentText("Delete Customer: " + selectedCust.getCustomerName() + " ?");
        Customers finalSelectedCust = selectedCust;
        alert.get().showAndWait().ifPresent((response -> {
            try {
                /**
                 * ******LAMBDA EXPRESSION*******
                 * finalSelectedCust as variable
                 */

                if(DBCustomers.appointmentsCustomer(finalSelectedCust.getCustomerId())) {
                    alert.set(new Alert(Alert.AlertType.WARNING));
                    alert.get().setTitle("Deletion cannot be completed");
                    alert.get().setHeaderText("Customer cannot be deleted");
                    alert.get().setContentText("Customer has associated appointments. Delete all associated appointments first.");
                    alert.get().showAndWait();
                    return;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if(response == ButtonType.OK) {
                DBCustomers.deleteCustomer(finalSelectedCust.getCustomerId());
                customerTableview.setItems(DBCustomers.getAllCustomers());
            } else {
            return;
        }
            alert.set(new Alert(Alert.AlertType.WARNING));
            alert.get().setTitle("Deleted");
            alert.get().setHeaderText("Customer has been deleted");
            alert.get().setContentText("Customer data has been removed from the database");
            alert.get().showAndWait();
        }));
    }
    /**
     * modifyCustomer button event that goes to the modify customer UI
     * @param event
     * @throws IOException
     */
    @FXML
    private void modifyCustomer(MouseEvent event) throws IOException{
        Customers selectedCust = customerTableview.getSelectionModel().getSelectedItem();
        if(selectedCust==null){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nothing has been selected");
            alert.setHeaderText("Invalid:");
            alert.setContentText("Make a selection");
            alert.showAndWait();
        }else{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("ModifyCustomer.fxml"));
            ModifyCustomerController controller = new ModifyCustomerController(selectedCust);

            loader.setController(controller);
            Parent root=loader.load();
            Scene scene=new Scene(root);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
    }

}
