package Controller;

import Main.Main;
import Users.User;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


/**
 * Created by Biggus on 6/2/2017.
 */
public class SignUp implements Initializable
{
    @FXML
    Pane pane;
    @FXML
    private ImageView im;
    @FXML
    private TextField firstName, lastName, socialSec, userName, eMail, phoneNo, passWord, confirmPassword, mon, day, yr;
    @FXML
    ComboBox<java.lang.String> genderBox;
    @FXML
    private Label errLocale;
    @FXML
    Button signUp;

    private static final java.lang.String USE_IN_FILE_NAME = "../UserInfo.dat";
    FileInputStream UseFile;
    ObjectInputStream UseInStream;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        genderBox.getItems().addAll(
                "Male",
                "Female",
                "Non-Binary",
                "Don't Wish to Answer"
        );
    }

    private boolean test(User p) throws IOException {
        //Declarations
        String locations = "";
        boolean pass = false,
                check,
                check_EMAIL,
                check_PASSWORD,
                check_UNAME = false,
                check_DOB = false,
                check_CONFIRM = false,
                check_SOC = false,
                check_PHONE = false;
        User u = new User();

        //Check DOB
        if ((Integer.parseInt(p.getMonth()) < 13) && (Integer.parseInt(p.getMonth()) > 0) && (Integer.parseInt(p.getDay()) < 31) && (Integer.parseInt(p.getDay()) > 0)) {
            check_DOB = true;
        }
        else
        {
            locations += "Date of Birth ";
        }

        //Check Username
        for(int i = 0; i < Main.UserList.size() ; i++)
        {
            if (!(Main.UserList.get(i).getuName().equals(p.getuName())))
            {
                check_UNAME = true;
                break;
            }
            else
            {
                locations += "User Name";
            }
        }

        //Check Password
        check_PASSWORD = Pattern.matches("^[a-zA-Z\\d\\W]+", p.getpWord());
        if(!check_PASSWORD)
        {
            locations += "Password";
        }

        //Check Email
        check_EMAIL = !(Pattern.matches("(?<!^)@mail\\.(?!$)", p.getEmail()));
        if(!check_EMAIL)
        {
            locations += "Email";
        }

        //Check Social Security Number
        if(!(Long.parseLong(p.getSSN()) > 999999999L) || !(Long.parseLong(p.getSSN()) < 100000000L))
        {
            check_SOC = true;
        }
        else
        {
            locations += "Social Security Number";
        }

        //Confirm the password is the same
        if(confirmPassword.getText().equals(p.getpWord()))
        {
            check_CONFIRM = true;
        }
        else
        {
            locations += "Confirm Password";
        }

        //Check Phone Number
        if(!(Long.parseLong(p.getPhone()) < 1000000000) || !(Long.parseLong(p.getPhone()) > 9999999999L))
        {
            check_PHONE = true;
        }
        else
        {
            locations += "Phone Number";
        }

        errLocale.setVisible(true);
        errLocale.setText(locations);

        check = check_DOB
                && check_EMAIL
                && check_PASSWORD
                && check_UNAME
                && check_CONFIRM
                && check_SOC
                && check_PHONE;

        return check;
    }

    public File chooseImage() {
        FileChooser chooser = new FileChooser();

        FileChooser.ExtensionFilter PNGFilter = new FileChooser.ExtensionFilter("PNG filter (*.png)", "*.PNG");
        FileChooser.ExtensionFilter JPGFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        chooser.getExtensionFilters().addAll(JPGFilter, PNGFilter);

        File file = chooser.showOpenDialog(null);
        Image image;
        try {
            BufferedImage BuffImage = ImageIO.read(file);
            image = SwingFXUtils.toFXImage(BuffImage, null);
            im.setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public void setValues() throws IOException {
        java.lang.String u;
        java.lang.String e;
        java.lang.String p;
        java.lang.String w;
        java.lang.String l;
        java.lang.String s;
        java.lang.String m;
        java.lang.String d;
        java.lang.String y;
        java.lang.String g;
        java.lang.String f;
        User Noob = null;
        boolean testResult;

        u = userName.getText();
        e = eMail.getText();
        p = phoneNo.getText();
        w = passWord.getText();
        f = firstName.getText();
        l = lastName.getText();
        s = socialSec.getText();
        m = mon.getText();
        d = day.getText();
        y = yr.getText();
        g = genderBox.getValue();

        Noob = new User(u,e,p,w,f,l,s,m,d,y,g);

        testResult = test(Noob);

        if(testResult)
        {
            //Create a stream and write to it
            createFile(Noob);

            //Closing the current window so only one window is open at a time
            Stage temp = (Stage) signUp.getScene().getWindow();
            temp.close();

            //Redirecting to the Main Menu after a successful login
            Stage stage = new Stage();
            Parent newMenu = FXMLLoader.load(getClass().getResource("../Main/Menu.fxml"));
            Scene inn = new Scene(newMenu);
            stage.setScene(inn);
            stage.show();
        }
        else
        {
            //A failed sign up attempt will clear the sensitive information
            socialSec.clear();
            userName.clear();
            eMail.clear();
            passWord.clear();
            confirmPassword.clear();
        }
    }

    public void createFile(User Noob) throws IOException {
        UseFile = new FileInputStream(USE_IN_FILE_NAME);
        BufferedInputStream buff = new BufferedInputStream(UseFile);
        UseInStream = new ObjectInputStream(buff);

        try
        {
            while (true)
            {
                Main.UserList.add((User) UseInStream.readObject());
            }
        }
        catch(ClassNotFoundException | IOException e)
        {
            Main.UserList.remove(Main.UserList.size() - 1);
        }

        Main.UserList.add(Noob);

        for(int i = 0; i < Main.UserList.size() ; i++)
        {
            FileOutputStream fout = new FileOutputStream("../UserInfo.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.reset();
            oos.writeObject(Main.UserList.get(i));
            oos.flush();
            oos.close();
        }
    }
}