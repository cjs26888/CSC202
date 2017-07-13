package Resources.Tests;

import Java.Classes.Restaurant;
import Java.Structures.BST;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Biggus on 7/11/2017.
 */
class RestaurantPopUpTest
{
    @Test
    void showResults()
    {
        //Need to test if showResults() evaluates contains(s) as true and tree.get(s).toString prints the proper Restaurant
        
        //Explanation
        //assertEquals(/*Expected*/,/*Actual*/);
        
        String s = "No";
        Restaurant A = new Restaurant("Ok","123 A St.","10, 10","1234567890","123.jpg");
        Restaurant B = new Restaurant("No","456 B Ave.","20, 20","9876543210","456.png");
        Restaurant C = new Restaurant(s);
        BST<Restaurant> t = new BST<>(A, B);
        
        if(t.contains(C))
        {
            //In the showResults() function I call [Tree Name].get([Restaurant Name]).toString(). To test that, that works I've called a similar thing here
            C = (Restaurant) t.get(C);
            //In substitute for toString() I called getName(). The result helps me determine the same thing, if the restaurant from get() is the correct one thus fulfilling the purpose of the method
            assertEquals("No", C.getName());
        }
        else
        {
            //If the contains(s) portion fails then I purposely tank the assertEquals method to see where my error might be
            assertEquals(true, false);
        }
    }
}