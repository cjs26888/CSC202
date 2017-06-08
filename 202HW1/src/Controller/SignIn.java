package Controller;

import Main.Main;
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

    public void signin() throws IOException
    {
        Stage stage = new Stage();
        boolean passU;
        boolean passP;
        String InputUsername = User.getText();
        String InputPassword = Pass.getText();
        String userPa;
        String userUs;
        Paint red = Color.RED;

        for (int j = 0; j < Main.UserList.size(); j++)
        {
            userPa = Main.UserList.get(j).getpWord();
            userUs = Main.UserList.get(j).getuName();

            passU = InputUsername.equals(userUs);
            if (!passU)
            {
                passLBL.setVisible(true);
                passLBL.setText("Your Username does not match our system");
                passLBL.setTextFill(red);
            }
            passP = InputPassword.equals(userPa);
            if (!passP)
            {
                passLBL.setVisible(true);
                passLBL.setText("Your password is incorrect");
                passLBL.setTextFill(red);
            }
            if (passP && passU) {
                Stage temp = (Stage) Pass.getScene().getWindow();
                temp.close();

                Parent message = FXMLLoader.load(getClass().getResource("../Main/PopUp.fxml"));
                Scene Message = new Scene(message);
                stage.setScene(Message);
                stage.show();
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
