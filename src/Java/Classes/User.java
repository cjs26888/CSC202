package Java.Classes;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * Created by Biggus on 6/1/2017.
 */
public class User extends Person implements Serializable, Comparable<User>
{
    //Declarations
    private String uName;
    private String Email;
    private String Phone;
    private String pWord;
    
    public User(Person pete, String username, String email, String phone, String pword)
    {
        this.setfName(pete.getfName());
        this.setlName(pete.getlName());
        this.setSSN(pete.getSSN());
        this.setDOB(pete.getDOB());
        this.setGender(pete.getGender());
        this.setuName(username);
        this.setEmail(email);
        this.setPhone(phone);
        this.setpWord(pword);
    }
    
    public User(String first, String last, String ssn, String dob, String gender, String username, String email, String phone, String pword)
    {
        this.setfName(first);
        this.setlName(last);
        this.setSSN(ssn);
        this.setDOB(dob);
        this.setGender(gender);
        this.setuName(username);
        this.setEmail(email);
        this.setPhone(phone);
        this.setpWord(pword);
    }
    
    //Default Constructor
    public User(){}
    
    //Getters
    public String getuName() {
        return uName;
    }
    public String getPhone() {return Phone;}
    public String getEmail() {
        return Email;
    }
    public String getpWord() {
        return pWord;
    }
    
    //Setters
    public void setuName(String u)
    {
        this.uName = u;
    }
    public void setEmail(String e)
    {
        this.Email = e;
    }
    public void setPhone(String phone)
    {
        if(phone.length() == 10)
        {
            this.Phone = phone;
        }
        else
        {
            this.Phone = null;
            setErrorMessage("Phone");
        }
    }
    public void setpWord(String pw)
    {
        //Check Password
        if(Pattern.matches("^[a-zA-Z\\d\\W]+", pw))
        {
            this.pWord = pw;
        }
        else
        {
            this.pWord = null;
            setErrorMessage("Password");
        }
    }
    
    public int compareTo(User u)
    {
        int cmp = u.getuName().compareTo(this.uName);
        if(cmp == 0)
        {
            cmp = u.getGender().compareTo(this.getGender());
            if(cmp == 0)
            {
                cmp = u.getDOB().compareTo(this.getDOB());
            }
        }
        return cmp;
    }
    
    
    
    @Override
    public String toString()
    {
        return "{FIRST NAME: " + this.getfName() + "} " +
               "{LAST NAME: " + this.getlName() + "} " +
               "{USERNAME: " + this.getuName() + "} " +
               "{PASSWORD: " + this.getpWord() + "} " +
               "{EMAIL: " + this.getEmail() + "} " +
               "{PHONE: " + this.getPhone() + "} " +
               "{DATE OF BIRTH: " + this.getDOB() + "} " +
               "{GENDER: " + this.getGender() + "} " +
               "{SOCIAL SECURITY NUMBER: " + this.getSSN() + "} ";
    }
}
