package Resources.Tests;

import Java.Classes.Person;
import Java.Classes.User;
import Java.Structures.myQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Biggus on 6/21/2017.
 */
public class myQueueTest
{
    
    @Test
    public void testToString() throws Exception
    {
        String one = "One";
        String two = "Two";
    
        myQueue<String> List = new myQueue<>();
        List.enqueue(one);
        List.enqueue(two);
        
        //Since one is added to the queue first the first Node is pointing to one and the rear node would point to the
        //Most recently added object, two
        assertEquals("Queue: \nFront of Queue: \nNode:     INFO: One    LINK: Node:     INFO: Two    LINK: null\n\n\nBack of Queue: \nNode:     INFO: Two    LINK: null\n\n", List.toString());
    }
    
    @Test
    public void enqueue() throws Exception
    {
        myQueue<User> List = new myQueue<>();
        
        User   u = new User(new Person("F", "L", "113212458", "03 24 1994", "Male"),"U", "E", "3144828497", "!1Password");
        
        List.enqueue(u);
        u = (User) List.dequeue();
        
        //Password should equal !1Password
        assertEquals("!1Password", u.getpWord());
    }
    
    @Test
    public void dequeue() throws Exception
    {
        myQueue<User> List = new myQueue<>();
    
        User   u = new User(new Person("F", "L", "111111111", "03 24 1994", "Male"),"U", "E", "P", "!1Password");
    
        List.enqueue(u);
        
        //Clear out u so as to ensure I'm not cheating
        u = null;
        
        u = (User) List.dequeue();
        
        //When the List dequeues correctly the function returns the User object from the front of the List. The only user
        //In the list is u and their password is !1Password so the result of u.getpWord() should be !1Password
        assertEquals("!1Password", u.getpWord());
    }
    
    @Test
    public void isEmpty() throws Exception
    {
        myQueue<User> List = new myQueue<>();
        
        User   u = new User(new Person("F", "L", "111111111", "03 24 1994", "Male"),"U", "E", "5555555555", "!1Password");
    
        List.enqueue(u);
        
        //isEmpty() will be false if the List is occupied
        assertEquals(false, List.isEmpty());
    }
    
    /*
    @Test
    public void isFull() throws Exception
    {
    
    }
    */
    
    @Test
    public void size() throws Exception
    {
        myQueue<User> List = new myQueue<>();
    
        User   u = new User(new Person("F", "L", "111111111", "03 24 1994", "Male"),"U", "E", "P", "!1Password");
    
        List.enqueue(u);
        int s = List.size();
        
        //s should be 1
        assertEquals(1, List.size());
    }
    
}