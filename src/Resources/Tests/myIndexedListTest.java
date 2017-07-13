package Resources.Tests;

import Java.Classes.Person;
import Java.Classes.User;
import Java.Structures.myIndexedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Biggus on 6/21/2017.
 */
public class myIndexedListTest
{
    @Test
    public void add() throws Exception
    {
        myIndexedList<User> List = new myIndexedList<>();
    
        User   u = new User(new Person("F", "L", "113212458", "03 24 1994", "Male"),"U", "E", "3144828497", "!1Password");
        
        List.add(u);
        
        //Since u is gathered from the remove function and that was the only thing in the list u.getpWord() yields !1Password
        assertEquals(u, List.remove(0));
    }
    
    @Test
    public void add1() throws Exception
    {
        myIndexedList<User> List = new myIndexedList<>();
    
        User   u = new User(new Person("F", "L", "113212458", "03 24 1994", "Male"),"U", "E", "3144828497", "!1Password");
    
        List.add(0, u);
        
        //if remove() doesn't throw an exception it returns the only user in List, u
        assertEquals(u, List.remove(0));
    }
    
    @Test
    public void testToString()
    {
        myIndexedList<String> List = new myIndexedList<>();
        
        String A = "ASTRING";
        String B = "BSTRING";
        
        List.add(A);
        List.add(B);
        
        //All the information on the inner workings of the List are printed
        assertEquals("myIndexedList: \nTop Node: \nNode:     INFO: ASTRING    LINK: null\n\nCurrent Node: \nNode:     INFO: ASTRING    LINK: null\n\nSize: \n2\n",List.toString());
    }
    
    @Test
    public void get() throws Exception
    {
        myIndexedList<User> List = new myIndexedList<>();
    
        User   u = new User(new Person("F", "L", "113212458", "03 24 1994", "Male"),"U", "E", "3144828497", "!1Password");
    
        List.add(u);
        
        //The user, u, is at index 0 as it is the first added. Calling get(0) returns u
        assertEquals(u, List.get(0));
    }
    
    @Test
    public void remove() throws Exception
    {
        myIndexedList<User> List = new myIndexedList<>();
    
        User   u = new User(new Person("F", "L", "113212458", "03 24 1994", "Male"),"U", "E", "3144828497", "!1Password");
    
        List.add(u);
        
        //remove() function returns a user, since u is the only user in List remove() should return u
        assertEquals("Node:     INFO: " + u + "    LINK: null\n", List.remove(0).toString());
    }
    
    @Test
    public void contains() throws Exception
    {
        myIndexedList<User> List = new myIndexedList<>();
    
        User   u = new User(new Person("F", "L", "113212458", "03 24 1994", "Male"),"U", "E", "3144828497", "!1Password");
    
        List.add(u);
        
        //u is the only user in List so contains must return true
        assertEquals(true, List.contains(u));
    }
    
    @Test
    public void isEmpty() throws Exception
    {
        myIndexedList<User> List = new myIndexedList<>();
    
        User   u = new User(new Person("F", "L", "113212458", "03 24 1994", "Male"),"U", "E", "3144828497", "!1Password");
    
        List.add(u);
    
        //isEmpty() will be false if u is in the List
        assertEquals(false, List.isEmpty());
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
    
        User   u = new User(new Person("F", "L", "113212458", "03 24 1994", "Male"),"U", "E", "3144828497", "!1Password");
    
        List.add(u);
        u = List.getNext();
        
        //u should be null
        assertEquals(null, List.getNext());
    }
    
    @Test
    public void size() throws Exception
    {
        myIndexedList<User> List = new myIndexedList<>();
    
        User   u = new User(new Person("F", "L", "113212458", "03 24 1994", "Male"),"U", "E", "3144828497", "!1Password");
    
        List.add(u);
        int s = List.size();
        
        //Size is 1 with u in the List
        assertEquals(1, List.size());
    }
    
    @Test
    public void set() throws Exception
    {
        myIndexedList<User> List = new myIndexedList<>();
    
        User   u = new User(new Person("F", "L", "113212458", "03 24 1994", "Male"),"U", "E", "3144828497", "!1Password");
        User   t = new User(new Person("F", "L", "113212458", "03 24 1994", "Male"),"T", "F", "5555555555", "!1Password");
    
        List.add(u);
        List.set(0,t);
        
        u = List.get(0);
        
        assertEquals("U should have password T", "T", u.getpWord());
    }
    
    @Test
    public void indexOf() throws Exception
    {
        myIndexedList<User> List = new myIndexedList<>();
    
        User   u = new User(new Person("F", "L", "113212458", "03 24 1994", "Male"),"U", "E", "3144828497", "!1Password");
        User   t = new User(new Person("F", "L", "113212458", "03 24 1994", "Male"),"T", "F", "5555555555", "!1Password");
    
        List.add(u);
        int i = List.indexOf(u);
    
        //i is 0 when with u in the first position
        assertEquals(0, List.indexOf(u));
    }
    
}