package Main.Java.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Biggus on 6/7/2017.
 */
public class PopUp implements Initializable
{
    @FXML
    Label   lblSuccess;
    @FXML
    Button btnOK;
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        lblSuccess.setText("Login Successful");
    }
    
    public void PopUp() throws IOException
    {
        Stage stage = (Stage) btnOK.getScene().getWindow();
        stage.close();
        
        Parent mainMenu = FXMLLoader.load(getClass().getResource("/Main/Resources/View/Main.fxml"));
        Scene main = new Scene(mainMenu);
        stage.setScene(main);
        stage.show();
    }
}
