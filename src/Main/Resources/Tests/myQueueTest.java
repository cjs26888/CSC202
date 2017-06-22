package Main.Resources.Tests;

import Main.Java.Classes.Person;
import Main.Java.Classes.User;
import Main.Java.Structures.myQueue;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Biggus on 6/21/2017.
 */
public class myQueueTest
{
    /*
    @Test
    public void toString() throws Exception
    {
    }
    */
    
    @Test
    public void enqueue() throws Exception
    {
        myQueue<User> List = new myQueue<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User   u = new User(p,"U", "E", "P", "W");
        
        List.enqueue(u);
        u = (User) List.dequeue();
        
        assertEquals("Password should equal W", "W", u.getpWord());
    }
    
    @Test
    public void dequeue() throws Exception
    {
        myQueue<User> List = new myQueue<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User   u = new User(p,"U", "E", "P", "W");
    
        List.enqueue(u);
        
        u = null;
        
        u = (User) List.dequeue();
        
        assertEquals("u should have a password W", "W", u.getpWord());
    }
    
    @Test
    public void isEmpty() throws Exception
    {
        myQueue<User> List = new myQueue<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User   u = new User(p,"U", "E", "P", "W");
    
        List.enqueue(u);
        boolean flag = List.isEmpty();
        
        assertEquals("Flag will be false with u in the List", false, List.isEmpty());
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
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User   u = new User(p,"U", "E", "P", "W");
    
        List.enqueue(u);
        int s = List.size();
        
        assertEquals("s should be 1", 1, List.size());
    }
    
}