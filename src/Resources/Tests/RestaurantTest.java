package Resources.Tests;

import Java.Classes.Restaurant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Biggus on 7/11/2017.
 */
class RestaurantTest
{
    @Test
    void compareTo()
    {
        Restaurant A = new Restaurant("Ok","123 A St.","10, 10","1234567890","123.jpg");
        Restaurant B = new Restaurant("Ok","123 A St.","10, 10","1234567890","123.jpg");
        
        //Explanation
        //assertEquals(/*Expected*/,/*Actual*/);
        
        //Both Restaurants are identical, the compareTo function needs to return 0
        assertEquals(0, A.compareTo(B));
        
            //Location
    
        //Change the Location to a greater value than A's
        B.setLocation("11, 10");
        //B has a Location that comes after A's Location, compareTo needs to return -1
        assertEquals(-1, A.compareTo(B));
        //Change the Location back
        B.setLocation(A.getLocation());
        
            //Name
        
        //Change the Name to a greater value than A's
        B.setName("Pie");
        //B has a Name that comes after A's Name, compareTo needs to return -1
        assertEquals(-1, A.compareTo(B));
        //Change the Name back
        B.setName(A.getName());
        
            //Address
        
        //Change the Address to something greater than A's
        B.setAddress("223 A St.");
        //B has an Address that comes after A's Address, compareTo needs to return -1
        assertEquals(-1, A.compareTo(B));
        //Change the Address back
        B.setAddress(A.getAddress());
        
            //Number
        
        //Change the Number to something greater than A's
        B.setNumber("2234567890");
        //B has a Number that comes after A's Number, compareTo needs to return -1
        assertEquals(-1, A.compareTo(B));
    }
    
}