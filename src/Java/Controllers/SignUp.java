package Java.Controllers;

import Java.Classes.*;
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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static Java.Controllers.Start.writeFile;

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
    private TextField firstName,
            lastName,
            socialSec,
            userName,
            eMail,
            phoneNo,
            passWord,
            confirmPassword,
            mon,
            day,
            yr;
    @FXML
    ComboBox<String> genderBox;
    @FXML
    private Label errLocale;
    @FXML
    Button signUp;
    
    Calendar cal;
    Stage stage;
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        cal = Calendar.getInstance();
        
        genderBox.getItems().addAll(
                "Male",
                "Female",
                "Non-Binary",
                "Don't Wish to Answer"
                                   );
    }
    
    private boolean test(User p) throws IOException {
        //Declarations
        boolean       check = true;
        
        //Check Username
        if(p.getuName().equals(Start.readFile()))
        {
            check = false;
            p.setErrorMessage("Username");
        }
        
        //Check Email
        if ((Pattern.matches("(?<!^)@mail\\.(?!$)", p.getEmail())))
        {
            check = false;
            p.setErrorMessage("Email");
        }
        
        //Confirm the password is the same
        if (!(confirmPassword.getText().equals(p.getpWord())))
        {
            check = false;
            p.setErrorMessage("Confirm Password");
        }
        
        if(!check)
        {
            errLocale.setVisible(true);
            errLocale.setText(p.getErrorMessage());
        }
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
        String  u;
        String  e;
        String  p;
        String  w;
        String  l;
        String  s;
        String  m;
        String  d;
        String  y;
        String  g;
        String  f;
        String  dob;
        Person  Pete;
        User    Noob;
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
        
        dob = m + " " + d + " " + y;
        
        Pete = new Person(f,l,s,dob,g);
        Noob = new User(Pete,u,e,p,w);
        testResult = test(Noob);
        
        if(testResult)
        {
            writeFile(Noob);
            //Closing the current window so only one window is open at a time
            Stage temp = (Stage) signUp.getScene().getWindow();
            temp.close();
            
            //Redirecting to the Start Menu after a successful login
            Stage stage = new Stage();
            Parent newMenu = FXMLLoader.load(getClass().getResource("/Resources/View/Main.fxml"));
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
    
    public void back() throws IOException
    {
        Stage temp = (Stage) firstName.getScene().getWindow();
        temp.close();
        
        stage = new Stage();
        
        Parent message = FXMLLoader.load(getClass().getResource("/Main/Resources/View/Main.fxml"));
        Scene Message = new Scene(message);
        stage.setScene(Message);
        stage.show();
    }
}