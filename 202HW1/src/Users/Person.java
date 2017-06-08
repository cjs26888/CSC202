package Users;

/**
 * Created by Biggus on 6/1/2017.
 */
public class Person
{
    //Declarations
    private String fName;
    private String lName;
    private String SSN;
    private String Month;
    private String Day;
    private String Year;
    private String Gender;

    //Getters
    public String getfName() {return fName;}
    public String getlName() {
        return lName;
    }
    public String getSSN() {
        return SSN;
    }
    public String getMonth() {
        return Month;
    }
    public String getDay(){return Day;}
    public String getYear(){return Year;}
    public String getGender() {return Gender;}

    //Setters
    public void setfName(String f) {
        fName = f;
    }
    public void setlName(String lNAme) {
        lName = lNAme;
    }
    public void setSSN(String ssn) { SSN = ssn; }
    public void setMonth(String m) { Month = m; }
    public void setDay(String d){ Day = d; }
    public void setYear(String y){ Year = y; }
    public void setGender(String gender) {
        Gender = gender;
    }
}
