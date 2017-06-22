package Main.Resources.Tests;

import Main.Java.Classes.Person;
import Main.Java.Classes.User;
import Main.Java.Structures.myStack;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    
        assertEquals("This should be null", null, List.top());
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
    
        assertEquals("False if the List pushed", false, List.isEmpty());
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
    
        assertEquals("s is 1 with an element in the List", 1, List.size());
    }
    
    /*
    @Test
    public void toString() throws Exception
    {
    }
    */
    
}