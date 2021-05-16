package schedulingApp;

import DBAccess.DBUsers;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class LogonController implements Initializable {

    public TextField usernameTxt;
    public TextField locationTxt;
    public PasswordField passwordTxt;
    public Button signInButton;
    public Button exitButton;
    public Label usernameLabel;
    public Label passwordLabel;
    public Label locationLabel;
    public Label titleLabel;
    public ZoneId userZoneId;

    Locale locale = Locale.getDefault();

    public Locale getLocale() {
        if (locale == null) {
            locale = new Locale(System.getProperty("user.language"), System.getProperty("user.country"));
        }
        return locale;
    }

    @FXML
    private void exitProgram(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Application");
        alert.setHeaderText("You're about to close the application");
        alert.setContentText("Are you sure?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) Platform.exit();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userZoneId = ZoneId.systemDefault();

        if (getLocale().equals("fr")) {
            titleLabel.setText("Planificateur de rendez-vous client");

            usernameLabel.setText("Nom d'utilisateur:");

            passwordLabel.setText("Mot de passe:");

            locationLabel.setText("Emplacement:");

            signInButton.setText("s'identifier");

            exitButton.setText("sortir");
        }
        locationTxt.setText(userZoneId.getDisplayName(TextStyle.FULL, Locale.getDefault()));


    }


    Users users;


    @FXML
    private void signIn(MouseEvent event) throws SQLException, IOException {
        String userLogin = usernameTxt.getText();
        String passwordLogin = passwordTxt.getText();
        ObservableList<Users> usersList = DBUsers.getAllUsers();

        boolean validLogin = false;

        for (Users u : usersList)

            if (userLogin.isEmpty() || passwordLogin.isEmpty() || !u.isValidLogin(userLogin, passwordLogin)) {

                if (!u.isValidLogin(userLogin, passwordLogin))
                    if (getLocale().equals("fr")) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Identifiant ou mot de passe incorrect");
                        alert.setContentText("Réessayer");
                        alert.showAndWait();
                    }
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Incorrect user name or password");
                alert.setContentText("Try again");
                alert.showAndWait();

            } else {
                if (u.isValidLogin(userLogin, passwordLogin)) {
                    validLogin = true;
                }

                FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            }
    }
}