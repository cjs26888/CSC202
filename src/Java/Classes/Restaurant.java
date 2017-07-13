package Java.Classes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Biggus on 6/23/2017.
 */
public class Restaurant implements Comparable<Restaurant>
{
    private StringProperty name;
    private StringProperty address;
    private StringProperty location;
    private StringProperty number;
    private StringProperty image;
    
    public Restaurant()
    {
        this.name = new SimpleStringProperty("");
        this.address = new SimpleStringProperty("");
        this.location = new SimpleStringProperty("");
        this.number = new SimpleStringProperty("");
        this.image = new SimpleStringProperty("");
    }
    
    public Restaurant(String Name)
    {
        this.name = new SimpleStringProperty(Name);
        this.address = new SimpleStringProperty("");
        this.location = new SimpleStringProperty("");
        this.number = new SimpleStringProperty("");
        this.image = new SimpleStringProperty("");
    }
    
    public Restaurant(String restaurantName, String restaurantAddress, String restaurantLocation, String restaurant_Phone_Number, String restaurantImage)
    {
        this.name = new SimpleStringProperty(restaurantName);
        this.address = new SimpleStringProperty(restaurantAddress);
        this.location = new SimpleStringProperty(restaurantLocation);
        this.number = new SimpleStringProperty(restaurant_Phone_Number);
        this.image = new SimpleStringProperty(restaurantImage);
    }
    
    
    //Getters
    public String getName() {return this.name.get();}
    public String getAddress(){return this.address.get();}
    public String getLocation(){return this.location.get();}
    public String getLatitude(){return this.getLocation().split(", ")[0];}
    public String getLongitude(){return this.getLocation().split(", ")[1];}
    public String getNumber(){return this.number.get();}
    public String getImage(){return this.image.get();}
    
    //Setters
    public void setName(String restaurantName){this.name.set(restaurantName);}
    public void setAddress(String restaurantAddress){this.address.set(restaurantAddress);}
    public void setLocation(String restaurantLocation){this.location.set(restaurantLocation);}
    public void setNumber(String restaurant_Phone_Number){this.number.set(restaurant_Phone_Number);}
    public void setImage(String restaurantImage){this.image.set(restaurantImage);}
    //public void setLatitude(String restaurantLatitude){this.setLocation(restaurantLatitude + ", " + getLongitude());}
    //public void setLongitude(String restaurantLongitude){this.setLocation(restaurantLongitude + ", " + getLatitude());}
    
    @Override
    public String toString()
    {
        return "Name: " + name + "\n" +
               "Address: " + address + "\n" +
               "Location: " + location + "\n" +
               //"Latitude: " + this.getLatitude() + "\n" +
               //"Longitude: " + this.getLongitude() + "\n" +
               "Number: " + number + "\n" +
               "Image: " + image + "\n";
    }
    
    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p>
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     * <p>
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     * <p>
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     * <p>
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     * <p>
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    public int compareTo(Restaurant o)
    {
        int cmp, cmp1, cmp2, cmp3, cmp4;
        cmp1 = this.getLocation().compareTo(o.getLocation());
        cmp2 = this.getName().compareTo(o.getName());
        cmp3 = this.getAddress().compareTo(o.getAddress());
        cmp4 = this.getNumber().compareTo(o.getNumber());
        
        if(cmp1 != 0 && cmp2 != 0 && cmp3 != 0 && cmp4 != 0)
        {
            if(cmp1 > 0 || cmp2 > 0 || cmp3 > 0 || cmp4 > 0)
            {
                cmp = 1;
            }
            else
            {
                cmp = -1;
            }
        }
        else
        {
            cmp = 0;
        }
        return cmp;
    }
    
    public StringProperty nameProperty()
    {
//        todo for all these
        /*
        if(this.name == null)
        {
        this.name = new SimpleStringProperty(this, "name");
        }
        */
        return this.name;
    }
    
    public StringProperty addressProperty()
    {
        /*
        if(this.address == null)
        {
            this.address = new SimpleStringProperty(this, "address");
        }
        */
        return this.address;
    }
    
    /*
    public StringProperty latitudeProperty()
    {
        /*
        if(this.latitude == null)
        {
            this.latitude = new SimpleStringProperty(this, "latitude");
        }
        */
    /*    return this.latitude;
    }
    */
    
    /*
    public StringProperty longitudeProperty()
    {
        /*
        if(this.longitude == null)
        {
            this.longitude = new SimpleStringProperty(this, "longitude");
        }
        */
    /*    return this.longitude;
    }
    */
    
    public StringProperty locationProperty()
    {
        /*
        if(this.number == null)
        {
            this.number = new SimpleStringProperty(this, "number");
        }
        */
        return this.location;
    }
    
    public StringProperty numberProperty()
    {
        /*
        if(this.number == null)
        {
            this.number = new SimpleStringProperty(this, "number");
        }
        */
        return this.number;
    }
    
    public StringProperty imageProperty()
    {
        /*
        if(this.image == null)
        {
            this.image = new SimpleStringProperty(this, "image");
        }
        */
        return this.image;
    }
}