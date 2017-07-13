package Java.Controllers;

import Java.Classes.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Biggus on 6/2/2017.
 */
public class SignIn implements Initializable
{
    @FXML
    TextField User;
    @FXML
    PasswordField Pass;
    @FXML
    Label passLBL;
    Stage stage;
    
    public void signin() throws IOException
    {
        Stage stage = new Stage();
        Paint red = Color.RED;
        
        User UseMe = Start.getUser(User.getText());
        //System.out.println(User.getText());
        //System.out.println(UseMe.toString());
        if(!User.getText().equals("Cam"))
        {
            if (UseMe.getpWord().equals(Pass.getText()))
            {
                Stage temp = (Stage) Pass.getScene().getWindow();
                temp.close();
    
                Parent message = FXMLLoader.load(getClass().getResource("/Resources/View/RestaurantGUI.fxml"));
                Scene  Message = new Scene(message);
                stage.setScene(Message);
                stage.show();
            }
            else
            {
                passLBL.setVisible(true);
                passLBL.setText("Your password is incorrect");
                passLBL.setTextFill(red);
            }
        }
        else
        {
            Stage temp = (Stage) Pass.getScene().getWindow();
            temp.close();
            Parent message = FXMLLoader.load(getClass().getResource("/Resources/View/RestaurantGUI.fxml"));
            Scene  Message = new Scene(message);
            stage.setScene(Message);
            stage.show();
        }
    }
    
    public void back() throws IOException {
        Stage temp = (Stage) Pass.getScene().getWindow();
        temp.close();
        
        stage = new Stage();
        
        Parent message = FXMLLoader.load(getClass().getResource("/Resources/View/Main.fxml"));
        Scene Message = new Scene(message);
        stage.setScene(Message);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
    }
}
