package main;

import DBConnection.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;


/**
 * Main class starts UI - the logon.fxml
 */
public class Main extends Application {

    static Locale locale = Locale.getDefault();
    static Locale currentLocale = getLocale();
    public static Locale getLocale() {
        if (locale == null) {
            locale = new Locale(System.getProperty("user.language"), System.getProperty("user.country"));
        }
        return locale;
    }

    /**
     * Start displays the first UI
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception{

        FXMLLoader loader=new FXMLLoader(getClass().getResource("/schedulingApp/Logon.fxml"));
        Parent root=loader.load();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

/**
 * ******LAMBDA EXPRESSION*******
 *Main contains a lambda expression used to display the users Country/Language info in the command line.
 *The variable userInfo streams theInfo variable instead of setting the variable then calling it.
 *
 */
    public static void main(String[] args) {

        userInfo u = new userInfo() {
            @Override
            public void theInfo() {
                System.out.println(currentLocale.getDisplayLanguage());
            }
        };

        userInfo u1 = () -> System.out.println(currentLocale.getDisplayCountry());
        u.theInfo();
        u1.theInfo();

        DBConnection.startConnection();

        launch(args);

        DBConnection.closeConnection();


    }
/**
 * ******LAMBDA EXPRESSION*******
 * The method call to display the information in the command line
 */

    interface userInfo {
        void theInfo();
    }

}
