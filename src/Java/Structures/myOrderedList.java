package Java.Structures;

import Resources.Exceptions.DuplicateException;
import Resources.Exceptions.UnderflowException;
import Resources.Interfaces.IOrdered;

/**
 * Purpose: To create an myOrderedList that accepts generics
 * Order: add(), remove(), contains(), isEmpty(), size(), get(), reset(), getNext(), toString(), equals()
 * @author Cameron on 6/8/2017.
 */
public class myOrderedList<E extends Comparable<E>> implements IOrdered
{
    //Constructor
    public myOrderedList()
    {
        currentNode = null;
        topNode = null;
    }
    
    //Member Variables
    Java.Structures.Node topNode     = new Java.Structures.Node();
    Java.Structures.Node currentNode = new Java.Structures.Node();
    
    //Override Member Functions
    @Override
    /**
     * Adds the specified element to the List
     * Specified By: add() in interface IOrdered
     * @param Object element - The element the user wishes to add to the List
     * @return Void
     * @throws DuplicateException - If the element is already in the List
     */
    public void add(Object element)
    {
        //NEEDS TO ADD THE ELEMENT IN THE PROPER ORDER
        //sets element equal to the newNode's Info
        //if the List is not empty set the Link to the currentNode
        //if it is empty or if the newNode's link is assigned make the currentNode be the newNode
        reset();
        Java.Structures.Node tmp = new Java.Structures.Node();
        tmp.setInfo((Comparable) element);
        if(!isEmpty())
        {
            if (contains(element))
            {
                throw new DuplicateException("You already have that entry in this list");
            }
            else
            {
                reset();
                while (tmp.compareTo(currentNode) == 1)
                {
                    currentNode = currentNode.getLink();
                    //System.out.println(currentNode.compareTo(currentNode.getLink()));
                }
                tmp.setLink(currentNode.getLink());
            }
        }
        else
        {
            topNode = tmp;
        }
    }
    
    @Override
    /**
     * Removes the specified element from the List
     * Specified By:
     * @param Object element - The element the user wishes to remove from the List
     * @return Boolean - Return true or false determined by if the removal was successful or not
     */
    public boolean remove(Object element)
    {
        //Find a node (if it exists) that contains the element in Info
        //Set the link of the parent node to the link of the aforementioned node
        //return a boolean value on whether it was removed or not
        reset();
        if(contains(element))
        {
            currentNode.setLink(currentNode.getLink().getLink());
        }
        return contains(element);
    }
    
    @Override
    /**
     * Searches through the List to find the passed element
     * Specified By: contains() in interface IOrdered
     * @param Object element - The element to be found in the List
     * @return Boolean - Returns true or false based on if the method found the requested element
     * @throws UnderflowException - If the method was attempted and found the List was empty
     */
    public boolean contains(Object element)
    {
        //returns boolean saying whether the list has a Node with Info equal() to element
        reset();
        boolean contains = false;
        
        if(!isEmpty())
        {
            if(currentNode.getInfo().compareTo(element) != 0) {
                for (int i = 0; i < size(); i++)
                {
                    if (currentNode.getLink().getInfo().compareTo(element) == 0)
                    {
                        contains = true;
                        break;
                    }
                    else
                    {
                        currentNode = currentNode.getLink();
                    }
                }
            }
            else
            {
                contains = true;
            }
        }
        else
        {
            throw new UnderflowException("contains() was attempted on an empty List");
        }
        
        return contains;
    }
    
    @Override
    /**
     * Determines if the List ahas anything in it
     * Specified By: isEmpty(0 in interface IOrdered
     * @param None
     * @return Boolean - Returns true or false dependent on if the List has anything in it
     */
    public boolean isEmpty()
    {
        if(topNode == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    @Override
    /**
     * Determines the number of elements in the List
     * Specified By: size() in interface IOrdered
     * @param None
     * @return int - The number of elements in the List
     * @throws UnderflowException - If the method is attempted on an empty List
     */
    public int size()
    {
        //Returns the number of elements
        //takes currentNode's link and increments size (starting from 1) until the Link == null;
        reset();
        if(!isEmpty())
        {
            int size = 1;
            while (currentNode.getLink() != null)
            {
                currentNode = currentNode.getLink();
                size++;
            }
            return size;
        }
        else
        {
            throw new UnderflowException("size() was attempted on an empty OrderedList");
        }
    }
    
    @Override
    /**
     * Fetches the Node containing Info that matches the passed element
     * Specified By: get() in interface IOrdered
     * @param Object element - The element the user wishes to be returned in order to manipulate it
     * @return E - The Node that has a matching info to the passed element
     */
    public Object get(Object element)
    {
        //element is NOT a Node. The return Object IS a Node
        //Run through List to find A node in the list with Info matching element
        //if true Return that Node(Link and Info) else return null
        reset();
        if (!contains(element))
        {
            return null;
        }
        else
        {
            return currentNode;
        }
    }
    
    public Object get()
    {
        reset();
        boolean flag = false;
        System.out.println(topNode.getInfo());
        if(!isEmpty())
        {
            flag = true;
        }
        if(flag)
        {
            return topNode.getInfo();
        }
        else
        {
            return null;
        }
    }
    
    @Override
    /**
     * Returns the currentNode variable to the beginning of the List so the List can be stepped through
     * Specified By: reset() in interface IOrdered
     * @param None
     * @return Void
     */
    public void reset()
    {
        //Initializes list (sets the currentNode to null)
        //Sets the position of the next element to be processed to the first element
        currentNode = topNode;
    }
    
    @Override
    /**
     * Returns the node next to the current Node for manipulation
     * Specified By: getNext() in interface IOrdered
     * @param None
     * @return E - The Node with its Info and Link member variables
     * @throws UnderflowException - If the method was attempted on an empty List
     */
    public Object getNext()
    {
        reset();
        if(!isEmpty())
        {
            currentNode = currentNode.getLink();
        }
        else
        {
            throw new UnderflowException("Method was attempted on an empty Structure");
        }
        return currentNode;
    }
    
    @Override
    /**
     * Turns the List into a String detailing the Info of each Node
     * Specified By: toString() in interface IOrdered
     * @param None
     * @return String - The List's Info in String form
     * @throws UnderflowException - If the method is attempted on an empty List
     */
    public String toString()
    {
        reset();
        String s = null;
        
        if(!isEmpty())
        {
            for (int i = 0; i < size(); i++)
            {
                s = currentNode.getInfo().toString() + " ";
                currentNode = currentNode.getLink();
            }
        }
        else
        {
            throw new UnderflowException("Method was attempted on an empty Structure");
        }
        
        return s;
    }
    
    /**
     * Determines if the 2 passed elements are equal
     * @param element1
     * @param element2
     * @return Boolean - Returns true or false dependent on if the 2 passed values are equal to each other
     */
    public boolean equals(Object element1, Object element2)
    {
        return ((element1 == null) ? (element2 == null) : element1.equals(element2));
    }
}
