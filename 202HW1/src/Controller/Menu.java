package Controller;

import Main.Main;
import Users.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Menu extends Application {
    Stage stage1 = new Stage();
    Scene act1, act2, act3;
    @FXML
    Button signIn;

    User u;

    private static final java.lang.String USE_IN_FILE_NAME = "../UserInfo.dat";
    FileInputStream UseFile;
    ObjectInputStream UseInStream;


    @Override
    public void start(Stage stage) throws IOException
    {
        try
        {
            UseFile = new FileInputStream(USE_IN_FILE_NAME);
            UseInStream = new ObjectInputStream(UseFile);
            do
            {
                u = (User) UseInStream.readObject();
                Main.UserList.add(u);
            } while (true);
        }
        catch (ClassNotFoundException | NullPointerException | EOFException e) {}

        if (UseInStream != null) {
            UseInStream.close();
        }

        Parent mainMenu = FXMLLoader.load(getClass().getResource("../Main/Menu.fxml"));
        act1 = new Scene(mainMenu);
        stage1.setScene(act1);
        stage1.show();
    }

    public void UpChoice() throws IOException {
        Stage temp = (Stage) signIn.getScene().getWindow();
        temp.close();
        Parent signup = FXMLLoader.load(getClass().getResource("../Main/SignUp.fxml"));
        act2 = new Scene(signup);
        stage1.setScene(act2);
        stage1.show();
    }

    public void InChoice() throws IOException
    {
        Stage temp = (Stage) signIn.getScene().getWindow();
        temp.close();
        Parent signin = FXMLLoader.load(getClass().getResource("../Main/SignIn.fxml"));
        act3 = new Scene(signin);
        stage1.setScene(act3);
        stage1.show();
    }
}
