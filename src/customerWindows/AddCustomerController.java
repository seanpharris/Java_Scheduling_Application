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
import model.Countries;
import model.Customers;
import model.FirstLevelDivisons;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;



public class AddCustomerController implements Initializable {


    public ChoiceBox<Countries> country;


    public TextField custIdTxt;
    public TextField custNameTxt;
    public TextField custPostalCodeTxt;
    public TextField custAddressTxt;
    public TextField createDate;
    public TextField custPhoneTxt;
    public TextField createBy;
    public TextField lastUpdateBy;
    public TextField lastUpdate;
    public ChoiceBox<FirstLevelDivisons> stateProvince;

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

    private static long customerIdCounter = 1;

    public static synchronized String createCustomerId() {
        return String.valueOf(customerIdCounter++);
    }

    Users users;


    @FXML
    private void saveNewCustomer(MouseEvent event) {
        /*if(relPartList.isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid input:");
            alert.setHeaderText("No part was Associated to the product");
            alert.showAndWait();
            return;
        }
        if(Integer.parseInt(stockTxt.getText())<Integer.parseInt(minTxt.getText())||(Integer.parseInt(stockTxt.getText())>Integer.parseInt(maxTxt.getText()))){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid input:");
            alert.setHeaderText("inventory cannot have less then min or more than max");
            alert.showAndWait();
            return;
        }
        if(Integer.parseInt(minTxt.getText())<=0||Integer.parseInt(minTxt.getText())>=Integer.parseInt(maxTxt.getText())){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid input:");
            alert.setHeaderText("min cannot be less than zero and must be less than max");
            alert.showAndWait();
            return;
        }*/


        Integer custId = Integer.parseInt(custIdTxt.getText());
        String custName = custNameTxt.getText();
        String custAddress = custAddressTxt.getText();
        String custPost = String.valueOf(Integer.parseInt(custPostalCodeTxt.getText()));
        String custPhone = String.valueOf(Integer.parseInt(custPhoneTxt.getText()));
        Timestamp created = Timestamp.valueOf(createDate.getText());
        String cBy = createBy.getText();
        Timestamp updated = Timestamp.valueOf(lastUpdate.getText());
        String lBy = lastUpdateBy.getText();
        String div = String.valueOf(Integer.parseInt(String.valueOf(stateProvince.getValue())));

        Customers cust = new Customers(custId, custName, custAddress, custPost, custPhone, created, cBy, updated, lBy, div);

        try {
            String sql = "INSERT INTO customers (Customer_Id, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By,Last_Updated, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?,?);";
            PreparedStatement ps = DBConnection.conn.prepareStatement(sql);

            ps.setInt(1, cust.getCustomerId());
            ps.setString(2, cust.getCustomerName());
            ps.setString(3, cust.getCustomerAddress());
            ps.setString(4, cust.getPostalCode());
            ps.setString(5, cust.getPhoneNumber());
            ps.setTimestamp(7, cust.getCreatedDate());
            ps.setString(8, cust.getCreatedBy());
            ps.setTimestamp(9, cust.getLastUpdated());
            ps.setString(10,cust.getLastUpdatedBy());
            ps.setInt(11, firstLevelDivisons.getDivisionId());

            ps.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/customerWindows/CustomersWindow.fxml"));
            CustomersController controller = new CustomersController();

            loader.setController(controller);
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
    FirstLevelDivisons firstLevelDivisons;
    @FXML
    private void countries(ActionEvent event) {
        Countries selected = country.getValue();
        stateProvince.setDisable(false);
        stateProvince.setItems(firstLevelDivisons.getDivisionByCountry(selected));
    }


    LocalDateTime dateTime = LocalDateTime.now();

    public static final DateTimeFormatter dateFormatter
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){
        custIdTxt.setText(createCustomerId());
        createDate.setText(dateFormatter.format(dateTime));
        lastUpdate.setText(dateFormatter.format(dateTime));
        country.setItems(DBCountries.getAvailableCountries());
        stateProvince.setDisable(true);
    }
}

