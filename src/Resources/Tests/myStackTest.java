package Resources.Tests;

import Java.Classes.Person;
import Java.Classes.User;
import Java.Structures.myStack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Biggus on 6/21/2017.
 */
public class myStackTest
{
    @Test
    public void push() throws Exception
    {
        myStack<User> List = new myStack<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User   u = new User(p,"U", "E", "P", "W");
        
        List.push(u);
        
        u = null;
        
        u = (User) List.top();
        
        assertEquals("This should be an element's password", "W", u.getpWord());
        
    }
    
    @Test
    public void pop() throws Exception
    {
        myStack<User> List = new myStack<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User   u = new User(p,"U", "E", "P", "W");
    
        List.push(u);
        List.pop();
    
        //This should be null
        assertEquals(null, List.top());
    }
    
    @Test
    public void top() throws Exception
    {
        myStack<User> List = new myStack<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User   u = new User(p,"U", "E", "P", "W");
    
        List.push(u);
    
        u = null;
    
        u = (User) List.top();
    
        assertEquals("This should be an element's password", "W", u.getpWord());
    }
    
    @Test
    public void isEmpty() throws Exception
    {
        myStack<User> List = new myStack<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User   u = new User(p,"U", "E", "P", "W");
    
        List.push(u);
    
        boolean flag = List.isEmpty();
    
        //False if the List pushed
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
        myStack<User> List = new myStack<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User   u = new User(p,"U", "E", "P", "W");
    
        List.push(u);
        int s = List.size();
    
        //s is 1 with an element in the List
        assertEquals(1, List.size());
    }
    
    /*
    @Test
    public void toString() throws Exception
    {
    }
    */
    
}