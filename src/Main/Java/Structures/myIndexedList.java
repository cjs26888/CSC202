package Main.Java.Structures;

import Main.Resources.Exceptions.DuplicateException;
import Main.Resources.Exceptions.InvalidInputException;
import Main.Resources.Exceptions.UnderflowException;
import Main.Resources.Interfaces.IIndex;

/**
 * Purpose: To create an myIndexedList that takes generics
 * Order: add(), add(), get(), remove(), contains(), isEmpty(), reset(), getNext(), size(), set(), indexOf()
 * @author Cameron on 6/8/2017.
 */

public class myIndexedList<E extends Comparable<E>> implements IIndex
{
    //Constructor
    public myIndexedList()
    {
        topNode = null;
        currentNode = null;
    }
    
    //Member Variables
    private Node topNode     = new Node();
    private Node currentNode = new Node();
    private int Size;
    
    //Override Member Functions
    
    //add() places a given element in the given index spot, then moves
    //the index of all the Nodes behind that index up 1 spot
    @Override
    /**
     * Adds the given element to the myIndexedList in the given index and moves all the elements after that index up 1 spot
     * Specified By: add) in interface IIndexedList
     * @param index, element - index is the desired place for the Object element to be inserted in the myIndexedList
     * @return Void
     * @throws DuplicateException - If the element passed to it already exists in the myIndexedList
     */
    public void add(int index, Object element)
    {
        //Checks to see if the list already has element
        //if not it inserts it in the provided index
        //insert entails taking the link of the node before,
        //setting it to the link-to-be node,
        // and setting the link-to-be node's link to the previous link
        //increments all indices after that index
        Node tmpNode = new Node();
        tmpNode.setInfo((E) element);
        if(!isEmpty())
        {
            if (contains(element))
            {
                throw new DuplicateException("You already have that entry in this list");
            }
            else
            {
                reset();
                tmpNode.setIndex(index);
                tmpNode.setLink(currentNode.getLink());
                currentNode.setLink(tmpNode);
                for (int i = index; i < size(); i++)
                {
                    currentNode.setIndex(currentNode.getIndex() + 1);
                    currentNode = currentNode.getLink();
                }
                Size++;
            }
            if (!contains(index))
            {
                throw new InvalidInputException("That element does not exist in this List");
            }
        }
        else
        {
            topNode = tmpNode;
        }
        return;
    }
    
    public void add(Object element)
    {
        //System.out.println("Entered");
        reset();
        Node tmpNode = new Node();
        tmpNode.setInfo((Comparable) element);
        //System.out.println(tmpNode.getInfo().toString());
        if(!isEmpty())
        {
            //System.out.println("Not empty");
            if (contains(element))
            {
                throw new DuplicateException("You already have that entry in this list");
            }
            else
            {
                reset();
                //System.out.println(size());
                tmpNode.setIndex(0);
                tmpNode.setLink(currentNode);
                for (int i = 1; i < size(); i++)
                {
                    currentNode.setIndex(currentNode.getIndex() + 1);
                    currentNode = currentNode.getLink();
                }
                Size++;
            }
        }
        else
        {
            topNode = tmpNode;
            //System.out.println("topNode in myIndexedList(): \n" + topNode.getInfo().toString());
            Size++;
        }
    }
    
    @Override
    public String toString()
    {
        return "myIndexedList{" +
               "topNode=" + topNode +
               ", currentNode=" + currentNode +
               ", Size=" + Size +
               '}';
    }
    
    //get() returns the element(or Info) of the Node at the given index
    @Override
    /**
     * Fetches the specified element to be manipulated
     * Specified By: get() in interface IIndexedList
     * @param int index - The index location of the element the user wishes to have returned
     * @return E - The Node with its Info and Link member variables
     * @throws InvalidInputException - If the index doesn't exist in the List
     */
    public E get(int index)
    {
        reset();
        
        //System.out.println("Index in Index.get(): \n" + index);
        
        if(!isEmpty())
        {
            if (contains(index))
            {
                return (E) currentNode.getInfo();
            }
            else
            {
                throw new InvalidInputException("That element does not exist in this List");
            }
        }
        else
        {
            throw new UnderflowException("get(index) attempted on an empty List");
        }
    }
    
    //remove() finds the Node at the given index(found), looks on the Node before and after then sets
    //the after Node equal to the before Node's Link eliminating anything Linked to the indexed Node
    @Override
    /**
     * Finds the element at the specified index
     * Specified By: remove() in interface IIndexedList
     * @param int index - The specified index the user wishes to remove
     * @return Object - Returns the removed Object to be manipulated
     * @throws InvalidInputException - If the passed index value doesn't exist in the List
     * @throws UnderflowException - If the method is attempted on an empty List
     */
    public Object remove(int index)
    {
        reset();
        if(! isEmpty())
        {
            if (contains(index))
            {
                currentNode.setLink(currentNode.getLink().getLink());
                reset();
            }
            else
            {
                currentNode = null;
                throw new InvalidInputException("That element does not exist in this List");
            }
        }
        else
        {
            throw new UnderflowException("remove() attempted on an empty myIndexedList");
        }
        
        return false;
    }
    
    //contains will determine whether the Object you passed it is an int or any other object
    //if the element it is passed is an int it will find the object at that index
    //if the element is not an int it will find the Object that has the Info matching element
    //contains is also used to step through the list node by node and stop when it finds the element
    //this essentially sets the currentNode object to the correct spot for manipulation
    @Override
    /**
     * Determines whether the passed Object is in the List
     * Specified By:
     * @param Object element - The element the user wishes to find the List
     * @return Boolean - Return true or false dependent on the List having an element matching the one passed
     * @throws UnderflowException - If the method is attempted on an empty List
     */
    public boolean contains(Object element)
    {
        //returns boolean saying whether the list has a Node with Info equal() to element
        reset();
        boolean contains = false;
        
        //System.out.println("Element in Index.contains(): " + element.getClass().getName() + " " + element);
        //System.out.println("isEmpty(): \n" + isEmpty());
        if((element.getClass().getName().equals("java.lang.Integer")))
        {
            //System.out.println("in");
            if (!isEmpty())
            {
                //System.out.println("CurrentNode in Index.contains(): \n" + currentNode.getIndex());
                if(currentNode.getIndex() != (Integer) element)
                {
                    //System.out.println("size: " + size());
                    while(currentNode.getLink() != null)
                    {
                        if(currentNode.getLink().getIndex() == (int) element)
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
                throw new UnderflowException("contains() attempted on an empty List");
            }
        }
        else
        {
            if (!isEmpty())
            {
                if (!currentNode.getInfo().equals(element))
                {
                    for (int i = 1; i < size(); i++)
                    {
                        if (currentNode.getLink().getInfo().equals(element))
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
                throw new UnderflowException("contains() attempted on an empty List");
            }
        }
        return contains;
    }
    
    //Checks to see if the topNode is set to anything since the topNode is permanently
    //pointing to the first element in the List if it equals null then the List hasn't
    //had anything added to it
    @Override
    /**
     * Checks if the List has had anything written to it
     * Specified By: isEmpty() in interface IIndexedList
     * @param None
     * @return Boolean - Returns true or false dependent on the List having something in it
     */
    public boolean isEmpty() throws UnderflowException
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
    
    //sets the currentNode to the topNode so currentNode can be stepped through the List again
    @Override
    /**
     * Returns the currentNode member variable to the top of the List so the List can be walked through again
     * Specified By: reset() in interface IIndexedList
     * @param None
     * @return Void
     */
    public void reset()
    {
        //Initializes list (sets the currentNode to null)
        //Sets the position of the next element to be processed to the first element
        currentNode = topNode;
    }
    
    //returns the Link of the currentNode. essentially taking one step through the list
    @Override
    /**
     * Fetches the next node with respect to the current node
     * Specified By: getNext() in interface IIndexedList
     * @param None
     * @return E - The Node at the next spot, with its Info and Link member variables
     * @throws UnderflowException - If the method is attempted on an empty List
     */
    public E getNext()
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
        return (E) currentNode;
    }
    
    //size() determines how many elements are in the List based on the fact that the last Node
    //has a Link that equals null
    @Override
    /**
     * Determines the number of elements in the List
     * Specified By:
     * @param None
     * @return int - The amount of elements in the List
     * @throws UnderflowException - If the method was attempted on an empty List
     */
    public int size()
    {
        return Size;
    }
    
    //finds the object at the given index and changes its Info to the given element
    @Override
    /**
     * Finds the given index and replaces its information with the one passed to it
     * Specified By: set() in interface IIndexedList
     * @param int index - The index the user wants to change
     * @param Object element - The value that the user wants the found index's information to be changed to
     * @return E - The Node that the user changed with its Info and Link member variables
     * @throws InvalidInputException - If the index passed in not in the List
     */
    public E set(int index, Object element)
    {
        reset();
        
        if(contains(index) && contains(element))
        {
            currentNode.setInfo((E) element);
        }
        else
        {
            currentNode = null;
            throw new InvalidInputException("That element does not exist in this List");
        }
        
        return (E) currentNode;
    }
    
    //steps through the List until it finds an object with a matching element
    //indexOf() then return the Index member variable of the Node at the given index
    @Override
    /**
     * Determines the index of the Node that has an Info member matching that of the passed value
     * Specified By: indexOf() in interface IIndex
     * @param Object element - The value with which the List is tested against to find the index of a matching element
     * @return int - the index number of the Node that has the same Info value as the passed value
     * @throws InvalidInputException - If the passed element is not in the List
     */
    public int indexOf(Object element)
    {
        if(!contains(element))
        {
            currentNode = null;
            throw new InvalidInputException("That element does not exist in this List");
        }
        else
        {
            currentNode = null;
            contains(element);
        }
        return currentNode.getIndex();
    }
}
