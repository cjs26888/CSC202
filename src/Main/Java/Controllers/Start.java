package Main.Java.Controllers;

import Main.Java.Classes.User;
import Main.Java.Structures.myIndexedList;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.*;

/**
 * Created by Biggus on 6/6/2017.
 */
public class Start extends Application{
    static final String FILE_NAME = "../UserInfo.dat";
    static File f;
    
    Stage stage1 = new Stage();
    Scene act1, act2, act3;
    @FXML
    Button signIn;
    
    @Override
    public void start(Stage stage) throws IOException
    {
        f = new File(FILE_NAME);
        
        Parent mainMenu = FXMLLoader.load(getClass().getResource("/Main/Resources/View/Main.fxml"));
        act1 = new Scene(mainMenu);
        stage1.setScene(act1);
        stage1.show();
    }
    
    public void UpChoice() throws IOException {
        Stage temp = (Stage) signIn.getScene().getWindow();
        temp.close();
        Parent signup = FXMLLoader.load(getClass().getResource("/Main/Resources/View/SignUp.fxml"));
        act2 = new Scene(signup);
        stage1.setScene(act2);
        stage1.show();
    }
    
    public void InChoice() throws IOException
    {
        Stage temp = (Stage) signIn.getScene().getWindow();
        temp.close();
        Parent signin = FXMLLoader.load(getClass().getResource("/Main/Resources/View/SignIn.fxml"));
        act3 = new Scene(signin);
        stage1.setScene(act3);
        stage1.show();
    }
    
    public static String readFile()
    {
        try
        {
            FileInputStream fis = new FileInputStream(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            User Fred = (User) ois.readObject();
            ois.close();
            return Fred.getuName();
        } catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void writeFile(User Noob) throws IOException
    {
        //System.out.println("User to be written: " + "\n" + Noob.toString());
        //System.out.println("f existence?: " + f.exists());
        try
        {
            if (!f.exists())
            {
                new FileOutputStream(FILE_NAME);
            }
            FileOutputStream FileOut = new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(FileOut);
            oos.reset();
            oos.writeObject(Noob);
            oos.flush();
            oos.close();
        }
        catch (IOException e)
        {
            System.err.println("Already Exists: " + e.getMessage());
        }
    }
    
    public static User getUser(String u)
    {
        FileInputStream     fis;
        ObjectInputStream   ois;
        myIndexedList<User> UserList   = new myIndexedList<>();
        User                tmpUserOne = new User();
        
        try
        {
            fis = new FileInputStream(FILE_NAME);
            while(true)
            {
                //System.out.println("Ran");
                ois = new ObjectInputStream(fis);
                UserList.add(ois.readObject());
            }
        }
        catch(EOFException e)
        {
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    
        //System.out.println("Size in getUser(): \n" + UserList.size());
        
        for(int i = 0; i < UserList.size(); i++)
        {
            tmpUserOne = UserList.get(i);
            //System.out.println(tmpUserOne);
            if(u.equals(tmpUserOne.getuName()))
            {
                break;
            }
            /*
            else
            {
                UserList.getNext();
            }
            */
        }
        //System.out.println(tmpUserOne.toString());
        return tmpUserOne;
    }
    
    public static void main(String args[])
    {
        launch(args);
    }
}
