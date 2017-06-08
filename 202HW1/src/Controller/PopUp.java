package Controller;

import Main.Main;
import Users.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Biggus on 6/7/2017.
 */
public class PopUp implements Initializable {
    @FXML
    Label   firstName,
            lastName,
            Number,
            Email,
            Social,
            Month,
            Day,
            Year,
            Gender,
            Usename,
            Password;
    @FXML
    Button btnOK;

    private static final java.lang.String USE_IN_FILE_NAME = "../UserInfo.dat";
    FileInputStream UseFile;
    ObjectInputStream UseInStream;

   @Override
    public void initialize(URL location, ResourceBundle resources) {
        User last = Main.UserList.get((Main.UserList.size()) - 1);

        firstName.setText(last.getfName());
        lastName.setText(last.getlName());
        Number.setText(last.getPhone());
        Email.setText(last.getEmail());
        Social.setText(last.getSSN());
        Month.setText(last.getMonth());
        Day.setText(last.getDay());
        Year.setText(last.getYear());
        Gender.setText(last.getGender());
        Usename.setText(last.getuName());
        Password.setText(last.getpWord());
    }

    public void ok() throws IOException {
        for(int i = 0; i < Main.UserList.size() ; i++)
        {
            FileOutputStream fout = new FileOutputStream("../UserInfo.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.reset();
            oos.writeObject(Main.UserList.get(i));
            oos.flush();
            oos.close();
        }
        Stage stage = (Stage) btnOK.getScene().getWindow();
        stage.close();

        Parent mainMenu = FXMLLoader.load(getClass().getResource("../Main/Menu.fxml"));
        Scene main = new Scene(mainMenu);
        stage.setScene(main);
        stage.show();
    }
}
