package Resources.Tests;

import Java.Classes.Restaurant;
import Java.Structures.BST;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Biggus on 6/29/2017.
 */
class BSTTest
//In case tests I don't use Restaurants in my tree. It didn't seem necessary and Strings are a tad easier to test
//All functions can and will run with restaurants in their trees
{
    @Test
    void isEmpty()
    {
        BST<Restaurant> r = new BST<>();

        //I Don't Add Anything and my Constructor creates a tree with a null root
        //I expect the root to equal null so isEmpty() returns true in that case
        assertEquals(true, r.isEmpty());
    }
    
    @Test
    void isFull()
    {
        //Since this is an unbounded tree and isFull() always returns false there is really no point in testing this
    }
    
    @Test
    void size()
    {
        Restaurant      rest = new Restaurant("Cam's Pizza", "123 Fake St.", "1, 2", "5555555555", "Fake Image URL");
        BST<Restaurant> r    = new BST<>(rest);
        
        //The BST r has only one item (Restaurant rest) in it so size() should return 1
        assertEquals(1 , r.size());
    }
    
    @Test
    void contains()
    {
        //Restaurant      rest = new Restaurant("Cam's Pizza", "123 Fake St.", "1, 2", "5555555555", "Fake Image URL");
        Restaurant      rest1 = new Restaurant("", "", "1, 2", "", "");
        Restaurant      rest2 = new Restaurant("", "", "3, 4", "", "");
        Restaurant      rest3 = new Restaurant("", "", "5, 6", "", "");
        BST<Restaurant> r    = new BST<>();

        r.add(rest1);
        r.add(rest3);
        r.add(rest2);

        //The BST Tree r has only one item (Restaurant rest) and contains checks if the item matches the passed value
        assertEquals(true, r.contains(rest2));
    }
    
    @Test
    void get()
    {
        Restaurant      rest = new Restaurant("Cam's Pizza", "123 Fake St.", "1, 2", "5555555555", "Fake Image URL");
        BST<Restaurant> r    = new BST<>(rest);
        
        //Since only rest is in BST r, get(rest) is our only option
        assertEquals(rest, r.get(rest));
    }
    
    @Test
    void add()
    {
        BST<String> r = new BST<>();
        
        String rest = "C";
        
        r.add("A");
        r.add(rest);
        r.add("B");
        
        r.reset(1);
        
        r.getNext(1);
        r.getNext(1);
        
        //getNext returns the third element in the tree base on in-order ordering. In this case it would be "C" or rest
        assertEquals(rest, r.getNext(1));
    }
    
    @Test
    void remove()
    {
        Restaurant      rest = new Restaurant("Cam's Pizza", "123 Fake St.", "1, 2", "5555555555", "Fake Image URL");
        BST<Restaurant> r    = new BST<>();
        
        r.add(rest);
        r.remove(rest);
        
        //Since adding and removing the only object in r the tree should be empty
        assertEquals(true, r.isEmpty());
    }
    
    @Test
    void reset()
    {
        String one = "A";
        String two = "B";
        String three = "C";
        BST<Restaurant> newR = new BST<>();
        
        
        //Add the Strings in a seemingly random order ot ensure I'm not just printing out the Objects in order
        newR.add(three);
        newR.add(one);
        newR.add(two);
        
        newR.reset(1);
        
        //This is a long and complicated looking Output here but it helps readability
        assertEquals("Queue: \nFront of Queue: \nNode: \nInfo: A\nLink: Node: \nInfo: B\nLink: Node: \nInfo: C\nLink: null\n\n\n\nBack of Queue: \nNode: \nInfo: C\nLink: null\n\n", newR.getInOrder().toString());
    }
    
    @Test
    void getNext()
    {
        String          one   = "A";
        String          two   = "B";
        String          three = "C";
        BST<Restaurant> r     = new BST<>();
        
        //Add the Strings in a seemingly random order ot ensure I'm not just printing out the Objects in order
        r.add(three);
        r.add(one);
        r.add(two);
        
        r.reset(1);
        
        //r.getNext() should return A if the tree nodes are in order
        assertEquals("A" , r.getNext(1));
    }
    
    @Test
    void testToString()
    {
        Restaurant rest = new Restaurant("Cam's Pizza", "123 Fake St.", "1, 2", "5555555555", "Fake Image URL");
        BST<Restaurant> r = new BST<>(rest);
        
        assertEquals("Name: Cam's Pizza\nAddress: 123 Fake St.\nLocation: 1, 2\nNumber: 5555555555\nImage: Fake Image URL\n", r.toString());
    }
}