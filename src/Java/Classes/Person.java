package Java.Classes;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Biggus on 6/1/2017.
 */
public class Person implements Serializable
{
    //Declarations
    private String fName;
    private String lName;
    private String SSN;
    private String DOB;
    private String Gender;
    private String ErrorMessage;

    //Constructors
    public Person()
    {
        this.ErrorMessage = "";
        this.fName = null;
        this.lName = null;
        this.SSN = null;
        this.DOB = null;
        this.Gender = null;
    }
    
    public Person(String f, String l, String s, String d, String g)
    {
        this.ErrorMessage = "";
        this.setfName(f);
        this.setlName(l);
        this.setSSN(s);
        this.setDOB(d);
        this.setGender(g);
    }
    
    //Getters
    public String getfName() {return fName;}
    public String getlName() {return lName;}
    public String getSSN() {
        return SSN;
    }
    public String getDOB() {return DOB;}
    public String getGender() {return Gender;}
    public String getErrorMessage(){return ErrorMessage;}

    //Setters
    public void setfName(String f) {
        fName = f;
    }
    public void setlName(String lNAme) {
        lName = lNAme;
    }
    public void setSSN(String ssn)
    {
        if(ssn.length() == 9)
        {
            this.SSN = ssn;
        }
        else
        {
            this.SSN = null;
            setErrorMessage("SSN");
        }
        SSN = ssn;
    }
    public void setGender(String gender) {
        Gender = gender;
    }
    public void setDOB(String dob)
    {
        Calendar cal = Calendar.getInstance();
        
        if(dob.length() == 10)
        {
            if ((Integer.parseInt(dob.substring(0,2)) < 13) && (Integer.parseInt(dob.substring(0,2)) > 0))
            {
                if ((Integer.parseInt(dob.substring(3,5)) < 31) && (Integer.parseInt(dob.substring(3,5)) > 0))
                {
                    //Oldest Person to ever live was 122
                    if ((cal.get(Calendar.YEAR) - 122) > Integer.parseInt(dob.substring(6,9)))
                    {
                        this.DOB = dob;
                    }
                }
            }
            else
            {
                setErrorMessage("DOB");
                this.DOB = null;
            }
        }
        else
        {
            setErrorMessage("DOB");
            this.DOB = null;
        }
    }
    public void setErrorMessage(String err)
    {
        ErrorMessage += (err + "\n");
        return;
    }
}
