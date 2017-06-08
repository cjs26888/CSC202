package Users;

import java.io.Serializable;

/**
 * Created by Biggus on 6/1/2017.
 */
public class User extends Person implements Serializable
{
    //Declarations
    private String uName;
    private String Email;
    private long Phone;
    private String pWord;

    //Constructor
    public User(String username, String email, String phone, String pword, String first, String last, String soc, String mon, String day, String yr, String gend)
    {
        this.setuName(username);
        this.setEmail(email);
        this.setPhone(phone);
        this.setpWord(pword);
        this.setfName(first);
        this.setlName(last);
        this.setSSN(soc);
        this.setMonth(mon);
        this.setDay(day);
        this.setYear(yr);
        this.setGender(gend);
    }

    //Default Constructor
    public User(){setuName(" ");}

    //Getters
    public String getuName() {
        return uName;
    }
    public String getPhone() {return Long.toString(Phone);}
    public String getEmail() {
        return Email;
    }
    public String getpWord() {
        return pWord;
    }

    //Setters
    public void setuName(String u) {this.uName = u;}
    public void setEmail(String e){this.Email = e;}
    public void setPhone(String phone) {this.Phone = Long.parseLong(phone);}
    public void setpWord(String pw){this.pWord = pw;}
}
