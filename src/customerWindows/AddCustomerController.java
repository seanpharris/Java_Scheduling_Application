package customerWindows;

import DBAccess.DBCountries;
import DBAccess.DBFirstLevelDivisions;
import DBConnection.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Customers;
import model.Users;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;


/**
 * AddCustomerController controls the add appointment UI
 */
public class AddCustomerController implements Initializable {


    public ChoiceBox<String> country;


    public TextField custIdTxt;
    public TextField custNameTxt;
    public TextField custPostalCodeTxt;
    public TextField custAddressTxt;
    public TextField createDate;
    public TextField custPhoneTxt;
    public TextField createBy;
    public TextField lastUpdateBy;
    public TextField lastUpdate;
    public ChoiceBox<String> stateProvince;


    /**
     * backToDashboard is a mouse event for to go back to the main appointment window
     * @param event
     */
    public void backToDashboard(MouseEvent event) {
        boolean cancel;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Click ok to confirm");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/customerWindows/CustomersWindow.fxml"));

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



    public static String customerIdCounter() {
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString().substring(0, 10).replaceAll("[^0-9]", "");

        return uuidAsString;

    }


    /**
     * saveCustomer is the mouseevent for the save button
     * @param event
     */

    @FXML
    private void saveNewCustomer(MouseEvent event) {
        if(custNameTxt.getText().isEmpty()||custAddressTxt.getText().isEmpty()||custPostalCodeTxt.getText().isEmpty()||custPhoneTxt.getText().isEmpty()||createBy.getText().isEmpty()||lastUpdateBy.getText().isEmpty()||stateProvince.getValue().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing information");
            alert.setHeaderText("Please fill in all information");
            alert.showAndWait();
            return;
        }


        String post = custPostalCodeTxt.getText();
        String phone = custPhoneTxt.getText();
        int count = 0;

        for (int i = 0; i < phone.length(); i++) {
            if (phone.charAt(i) != ' ')
                count++;
        }

        if(count > 9) {

            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid input");
            alert.setHeaderText("Phone number cannot be more than 9 digits.");
            alert.showAndWait();
            return;
        }

        int count1 = 0;

        for (int i = 0; i < post.length(); i++) {
            if (post.charAt(i) != ' ')
                count1++;
        }

        if(count1 > 9) {

            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid input");
            alert.setHeaderText("Postal code cannot be more than 9 digits.");
            alert.showAndWait();
            return;
        }

        int custId = Integer.parseInt(custIdTxt.getText());
        String custName = custNameTxt.getText();
        String custAddress = custAddressTxt.getText();
        int custPost = (Integer.parseInt(custPostalCodeTxt.getText()));
        int custPhone = (Integer.parseInt(custPhoneTxt.getText()));
        Timestamp created = Timestamp.valueOf(createDate.getText());
        String cBy = createBy.getText();
        Timestamp updated = Timestamp.valueOf(lastUpdate.getText());
        String lBy = lastUpdateBy.getText();
        int div = DBFirstLevelDivisions.getDivisionId(stateProvince.getValue());

        Customers cust = new Customers(custId, custName, custAddress, custPost, custPhone, created, cBy, updated, lBy, div);

        try {
            String sql = "INSERT INTO customers (Customer_Id, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By,Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?);";
            PreparedStatement ps = DBConnection.conn.prepareStatement(sql);

            ps.setInt(1, cust.getCustomerId());
            ps.setString(2, cust.getCustomerName());
            ps.setString(3, cust.getCustomerAddress());
            ps.setInt(4, Integer.parseInt(String.valueOf(cust.getPostalCode())));
            ps.setInt(5, Integer.parseInt(String.valueOf(cust.getPhoneNumber())));
            ps.setTimestamp(6, cust.getCreatedDate());
            ps.setString(7, cust.getCreatedBy());
            ps.setTimestamp(8, cust.getLastUpdated());
            ps.setString(9,cust.getLastUpdatedBy());
            ps.setInt(10, cust.getDivisionId());

            ps.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/customerWindows/CustomersWindow.fxml"));
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
     * countries is the choice box controller for country selection
     * @param event
     */
    @FXML
    private void countries(ActionEvent event) {
        String selectedCountry = String.valueOf(country.getValue());


        stateProvince.setDisable(false);
        stateProvince.setItems(DBFirstLevelDivisions.findDiv(selectedCountry));
    }


    /**
     * dateTime gets the local time for the moment
     */
    LocalDateTime dateTime = LocalDateTime.now();
    /**
     * dateFormatter formats the date
     */
    public static final DateTimeFormatter dateFormatter
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    /**
     * initializer loads the information for the UI
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){
        custIdTxt.setText(customerIdCounter());
        createDate.setText(dateFormatter.format(dateTime));
        lastUpdate.setText(dateFormatter.format(dateTime));
        country.setItems(DBCountries.getAvailableCountries());
        stateProvince.setDisable(true);
        createBy.setText(Users.getUserName());
        lastUpdateBy.setText(Users.getUserName());
    }
}

