package Main.Resources.Tests;

import Main.Java.Classes.Person;
import Main.Java.Classes.User;
import Main.Java.Structures.myOrderedList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        
        assertEquals("Flag will be set to true if the element was removed", true, flag);
    }
    
    @Test
    public void contains() throws Exception
    {
        myOrderedList<E> List = new myOrderedList<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User u = new User(p,"U", "E", "P", "W");
        
        List.add(u);
        boolean flag = List.contains(u);
        
        assertEquals("Flag is true if the element is in the List", true, flag);
    }
    
    @Test
    public void isEmpty() throws Exception
    {
        myOrderedList<E> List = new myOrderedList<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User u = new User(p,"U", "E", "P", "W");
    
        List.add(u);
        boolean flag = List.isEmpty();
        
        assertEquals("Flag is false if the List has elements in it", false, flag);
    }
    
    @Test
    public void size() throws Exception
    {
        myOrderedList<E> List = new myOrderedList<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User u = new User(p,"U", "E", "P", "W");
    
        List.add(u);
        int s = List.size();
        
        assertEquals("s should be 1 if the element is in the list", 1, s);
    }
    
    @Test
    public void get() throws Exception
    {
        myOrderedList<E> List = new myOrderedList<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User u = new User(p,"U", "E", "P", "W");
    
        List.add(u);
        List.get(u);
        
        
    }
    
    @Test
    public void get1() throws Exception
    {
        myOrderedList<E> List = new myOrderedList<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User u = new User(p,"U", "E", "P", "W");
    
        List.add(u);
        List.get();
        
        assertEquals("Thing should be u", u, List.get(u));
    }
    
    @Test
    public void reset() throws Exception
    {
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
        
        assertEquals("getNext() returns the link to the u node, which is null", null , List.getNext());
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
        
        assertEquals("The flag should be true as both lements are the same", true, List.equals(t,u));
    }
    
}