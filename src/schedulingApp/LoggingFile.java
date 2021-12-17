package schedulingApp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
/**
 * LoggingFile class documents the logging in attempts and successes
 */

public class LoggingFile {

    /**
     * defines txtdoc
     */
    private static final String txtDoc = "login_activity.txt";

    /**
     * creates txt doc unless one is already created
     */
    public static void createLog(){
        try {
            File file = new File(txtDoc);
            if (file.createNewFile()) {
                System.out.println("Text doc created");
            } else {
                System.out.println("Text doc exists.");
            }
        } catch (IOException e) {
            System.out.println("Error " + e);
        }
    }

    /**
     * writes to txt doc for failure or success
     * @param string
     */
    public static void toTxtDoc(String string){
        string = LocalDateTime.now() + ": " + string;

        try {
            FileWriter myWriter = new FileWriter(txtDoc, true);
            myWriter.append(string).append("\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Error " + e);
        }
    }

}