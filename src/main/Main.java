package main;

import DBConnection.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        FXMLLoader loader=new FXMLLoader(getClass().getResource("/schedulingApp/Logon.fxml"));
        Parent root=loader.load();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {

        DBConnection.startConnection();

        launch(args);

        DBConnection.closeConnection();
    }
}
