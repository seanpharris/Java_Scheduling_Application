package customerWindows;

import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBFirstLevelDivisions;
import DBConnection.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.Countries;
import model.Customers;
import model.FirstLevelDivisons;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
/**
 * ModifyCustomerController controls the add appointment UI
 */
public class ModifyCustomerController implements Initializable {


    public TextField custIdTxt;
    public TextField custNameTxt;
    public TextField custAddressTxt;
    public TextField custPostalCodeTxt;
    public TextField custPhoneTxt;
    public TextField createDate;
    public TextField createBy;
    public TextField lastUpdateBy;
    public TextField lastUpdate;
    public ChoiceBox<String> country;
    public ChoiceBox<String> stateProvince;

public ModifyCustomerController(Customers customers) {
    this.customers = customers;
}

Customers customers;


    /**
     * backToDashboard is a mouse event for to go back to the main appointment window
     * @param event
     */
    public void backToDashboard (MouseEvent event){
        boolean cancel;
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Click ok to confirm");
        Optional<ButtonType> result=alert.showAndWait();
        if(result.isPresent()&&result.get()==ButtonType.OK) try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/customerWindows/CustomersWindow.fxml"));

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
     * date gets the local time for the moment
     */
    LocalDateTime dateTime = LocalDateTime.now();


    /**
     * dateFormatter formats the date
     */
    public static final DateTimeFormatter dateFormatter
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * saveCustomer is the mouseevent for the save button
     * @param event
     */

    @FXML
    private void saveCustomer(MouseEvent event) {
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

            String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?;";
            PreparedStatement ps = DBConnection.conn.prepareStatement(sql);


            ps.setString(1, cust.getCustomerName());
            ps.setString(2, cust.getCustomerAddress());
            ps.setInt(3, Integer.parseInt(String.valueOf(cust.getPostalCode())));
            ps.setInt(4, Integer.parseInt(String.valueOf(cust.getPhoneNumber())));
            ps.setTimestamp(5, cust.getCreatedDate());
            ps.setString(6, cust.getCreatedBy());
            ps.setTimestamp(7, cust.getLastUpdated());
            ps.setString(8,cust.getLastUpdatedBy());
            ps.setInt(9, cust.getDivisionId());
            ps.setInt(10, cust.getCustomerId());

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
     * initializer loads the information for the UI
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (customers != null) {

            custIdTxt.setText(Integer.toString(customers.getCustomerId()));
            createDate.setText(String.valueOf(customers.getCreatedDate()));
            lastUpdate.setText(dateFormatter.format(dateTime));
            stateProvince.setItems(DBFirstLevelDivisions.getAllFirstLevelDivisons());
            stateProvince.setValue(DBFirstLevelDivisions.getDivisionName(customers.getDivisionId()));

            country.setItems(DBCountries.getAvailableCountries());
            country.setValue(DBCountries.getChosenCountryName(DBCountries.getChosenCountry(customers.getDivisionId())));


            custNameTxt.setText(customers.getCustomerName());
            custPostalCodeTxt.setText(Integer.toString(customers.getPostalCode()));
            custAddressTxt.setText(customers.getCustomerAddress());
            custPhoneTxt.setText(Integer.toString(customers.getPhoneNumber()));
            createBy.setText(customers.getCreatedBy());
            lastUpdateBy.setText(customers.getLastUpdatedBy());
        }
    }
}
