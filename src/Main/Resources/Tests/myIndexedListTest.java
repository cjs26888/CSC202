package Main.Resources.Tests;

import Main.Java.Classes.Person;
import Main.Java.Classes.User;
import Main.Java.Structures.myIndexedList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Biggus on 6/21/2017.
 */
public class myIndexedListTest
{
    @Test
    public void add() throws Exception
    {
        myIndexedList<User> List = new myIndexedList<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User   u = new User(p,"U", "E", "P", "W");
        
        List.add(u);
        
        u = null;
        
         u = (User) List.remove(0);
        
        assertEquals("u should have password W", "W", u.getpWord());
    }
    
    @Test
    public void add1() throws Exception
    {
        myIndexedList<User> List = new myIndexedList<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User   u = new User(p,"U", "E", "P", "W");
    
        List.add(0, u);
        
        u = null;
        
        u = (User) List.remove(0);
    }
    
    /*
    @Test
    public void toString() throws Exception
    {
    }
    */
    
    @Test
    public void get() throws Exception
    {
        myIndexedList<User> List = new myIndexedList<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User   u = new User(p,"U", "E", "P", "W");
    
        List.add(u);
        
        u = null;
        
        u = List.get(0);
        
        assertEquals("U should have password W", "W", u.getpWord());
    }
    
    @Test
    public void remove() throws Exception
    {
        myIndexedList<User> List = new myIndexedList<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User   u = new User(p,"U", "E", "P", "W");
    
        List.add(u);
        
        u = null;
        
        u = (User) List.remove(0);
        
        assertEquals("u should have password W", "W", u.getpWord());
    }
    
    @Test
    public void contains() throws Exception
    {
        myIndexedList<User> List = new myIndexedList<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User   u = new User(p,"U", "E", "P", "W");
    
        List.add(u);
        boolean flag = List.contains(u);
        
        assertEquals("Flag will be true if the element is in the list", true, List.contains(u));
    }
    
    @Test
    public void isEmpty() throws Exception
    {
        myIndexedList<User> List = new myIndexedList<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User   u = new User(p,"U", "E", "P", "W");
    
        List.add(u);
        boolean flag = List.isEmpty();
        
        assertEquals("Flag will be false if u is in the List", false, List.isEmpty());
    }
    
    /*
    @Test
    public void reset() throws Exception
    {
    
    }
    */
    
    @Test
    public void getNext() throws Exception
    {
        myIndexedList<User> List = new myIndexedList<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User   u = new User(p,"U", "E", "P", "W");
    
        List.add(u);
        u = List.getNext();
        
        assertEquals("u should be null", null, List.getNext());
    }
    
    @Test
    public void size() throws Exception
    {
        myIndexedList<User> List = new myIndexedList<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User   u = new User(p,"U", "E", "P", "W");
    
        List.add(u);
        int s = List.size();
        
        assertEquals("Size is 1 with u in the List", 1, List.size());
    }
    
    @Test
    public void set() throws Exception
    {
        myIndexedList<User> List = new myIndexedList<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User   u = new User(p,"U", "E", "P", "W");
        User   t = new User(p,"U", "E", "P", "T");
    
        List.add(u);
        List.set(0,t);
        
        u = List.get(0);
        
        assertEquals("U should have password T", "T", u.getpWord());
    }
    
    @Test
    public void indexOf() throws Exception
    {
        myIndexedList<User> List = new myIndexedList<>();
    
        Person p = new Person("F", "L", "1132", "03", "Male");
        User   u = new User(p,"U", "E", "P", "W");
        User   t = new User(p,"U", "E", "P", "T");
    
        List.add(u);
        int i = List.indexOf(u);
        
        assertEquals("i is 0 when with u in the first position", 0, List.indexOf(u));
        
    }
    
}