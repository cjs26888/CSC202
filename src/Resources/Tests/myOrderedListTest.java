package Resources.Tests;

import Java.Classes.Person;
import Java.Classes.User;
import Java.Structures.myOrderedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Biggus on 6/21/2017.
 */
public class myOrderedListTest<E extends Comparable<E>>
{
    @Test
    public void add() throws Exception
    {
        myOrderedList<E> List = new myOrderedList<>();
        
        Person p = new Person("F", "L", "1132", "03", "Male");
        User u = new User(p,"U", "E", "P", "W");
        
        List.add(u);
        u = (User) List.get();
        
        assertEquals ("Password should be set to W", "W", u.getpWord());
    }
    
    @Test
    public void remove() throws Exception
    {
        myOrderedList<E> List = new myOrderedList<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User u = new User(p,"U", "E", "P", "W");
        
        List.add(u);
        boolean flag = List.remove(u);
    
        //Flag will be set to true if the element was removed
        assertEquals(true, flag);
    }
    
    @Test
    public void contains() throws Exception
    {
        myOrderedList<E> List = new myOrderedList<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User u = new User(p,"U", "E", "P", "W");
        
        List.add(u);
        boolean flag = List.contains(u);
        
        //Flag is true if the element is in the List
        assertEquals(true, flag);
    }
    
    @Test
    public void isEmpty() throws Exception
    {
        myOrderedList<E> List = new myOrderedList<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User u = new User(p,"U", "E", "P", "W");
    
        List.add(u);
        boolean flag = List.isEmpty();
        
        //Flag is false if the List has elements in it
        assertEquals(false, flag);
    }
    
    @Test
    public void size() throws Exception
    {
        myOrderedList<E> List = new myOrderedList<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User u = new User(p,"U", "E", "P", "W");
    
        List.add(u);
        int s = List.size();
        
        //s should be 1 if the element is in the list
        assertEquals(1, s);
    }
    
    @Test
    public void get() throws Exception
    {
        myOrderedList<E> List = new myOrderedList<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User u = new User(p,"U", "E", "P", "W");
    
        List.add(u);
        User newUser = (User) List.get(u);
        
        //With the get() function's return saved in newUser the email's of u and newUser should be the same
        assertEquals(newUser.getEmail(),u.getEmail());
    }
    
    @Test
    public void get1() throws Exception
    {
        myOrderedList<E> List = new myOrderedList<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User u = new User(p,"U", "E", "P", "W");
    
        List.add(u);
        List.get();
    
        //Thing should be u
        assertEquals(u, List.get(u));
    }
    
    @Test
    public void reset() throws Exception
    {
        //Due to the simplicity of this code it's not really worth testing
        //Also due to the private nature of the variables I couldn't access them anyways
    }
    
    @Test
    public void getNext() throws Exception
    {
        myOrderedList<E> List = new myOrderedList<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User u = new User(p,"U", "E", "P", "W");
    
        List.add(u);
        List.add(u);
        List.getNext();
        
        //getNext() returns the link to the u node, which is null
        assertEquals(null , List.getNext());
    }
    
    /*
    @Test
    public void toString() throws Exception
    {
        myOrderedList<E> List = new myOrderedList<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User u = new User(p,"U", "E", "P", "W");
    
        List.add(u);
        String s = List.toString();
        
        assertEquals("Should output u in string form", u.toString(), List.toString());
    }
    */
    
    @Test
    public void equals() throws Exception
    {
        myOrderedList<E> List = new myOrderedList<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User u = new User(p,"U", "E", "P", "W");
        User t = new User(p,"U", "E", "P", "W");
    
        List.add(u);
        List.add(t);
        boolean flag = List.equals(t,u);
        
        //The flag should be true as both lements are the same
        assertEquals(true, List.equals(t,u));
    }
    
}