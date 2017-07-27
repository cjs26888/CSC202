package Java.Controllers;

import Java.Classes.User;
import Java.Structures.myIndexedList;
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
public class Start extends Application
{
    private static final String FILE_NAME = "../UserInfo.dat";
    static File f = new File(FILE_NAME);
    Stage stage = new Stage();
    Scene act;
    @FXML
    Button signIn;
    
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        readFile();
        
        switchViews("/Resources/View/Main.fxml", null);
    }
    
    public void UpChoice() throws IOException
    {
        switchViews("/Resources/View/SignUp.fxml", signIn);
    }
    
    public void InChoice() throws IOException
    {
        switchViews("/Resources/View/SignIn.fxml", signIn);
    }

    /**
     * @return String - The UserName or null depending on if the file was successfully found
     * @throws IOException - if the file is not found
     */
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

    /**
     * @param Noob - The User used to store the value pulled from the file
     * @return void
     * @throws IOException - if the file is not found
     */
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

    /**
     * @param u - The UserName used for speeding up the process
     * @return User - Returns the found user
     * @throws IOException - if the file is not found
     */
    public static User getUser(String u)
    {
        FileInputStream   fis;
        ObjectInputStream ois;
        myIndexedList<User>   UserList   = new myIndexedList<>();
        User              tmpUserOne = new User();
        
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

    /**
     * @param fxmlFile - The String value of the fxml file to be opened
     * @param o - control object that will help close the current scene
     * @return void
     * @throws IOException - if the file is not found
     */
    public void switchViews(String fxmlFile, Object o) throws IOException
    {
        if(o != null)
        {
            Button btn = (Button) o;
            Stage temp = (Stage) btn.getScene().getWindow();
            temp.close();
        }
        
        Parent Mom = FXMLLoader.load(getClass().getResource(fxmlFile));
        act = new Scene(Mom);
        stage.setScene(act);
        stage.show();
    }
    
    public static void main(String args[])
    {
        launch(args);
    }
}
