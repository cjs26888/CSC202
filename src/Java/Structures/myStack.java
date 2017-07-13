package Java.Structures;

import Resources.Exceptions.DuplicateException;
import Resources.Exceptions.UnderflowException;
import Resources.Interfaces.IStack;

/**
 * Purpose: To create a myStack Object that uses any type in its structure.
 * Order: push(), pop(), top(), isEmpty(), isFull(), size()
 * @author Cameron on 6/8/2017.
 */
public class myStack<T extends Comparable<T>> implements IStack
{
    public myStack() {
        Index = 0;
        top = null;
    }
    
    //Member Variables
    Java.Structures.Node top = new Java.Structures.Node();
    int Index;


    //Member Functions
    @Override

    /**
     * The element added to the array. Set the element reference to an item, Set the link reference to the previous top of stack, Set the top of the stack to the new stack element
     * Specified By: push() in interface IStack
     * @param Object element - An object that the user wishes to have pushed on top of the myStack
     * @return Void
     * @throws DuplicateException - If the element already exists in the myStack
     */
    public void push(Object element)
    {
        Java.Structures.Node temp;
        Java.Structures.Node newNode = new Java.Structures.Node();
        
        temp = top;
        
        for (int i = 0; i < size(); i++)
        {
            if (element != temp.getInfo())
            {
                temp = temp.getLink();
            }
            else
            {
                throw new DuplicateException("That element already exists in the myStack");
            }
        }

        newNode.setInfo((T) element);
        newNode.setLink(top);
        top = newNode;
        Index++;
        
        return;
    }

    @Override
    /**
     * Removes the element at the top index, assigning top to the next lowest node
     * Specified By: pop() in interface IStack
     * @param None
     * @return Void
     * @throws UnderflowException - If the method is trying to remove something from an empty List
     */
    public void pop() {
        if(!isEmpty())
        {
            top = top.getLink();
            Index--;
        }
        else
        {
            throw new UnderflowException("pop() was attempted on an empty Structure");
        }
    }

    @Override
    /**
     * Gets the Object on the top of the myStack
     * Specified By: top() in interface IStack
     * @param None
     * @return Object - The Object on the top of the myStack
     * @throws UnderflowException - If the method is trying to remove something from an empty List
     */
    public Object top()
    {
        if(!isEmpty())
        {
            return top.getInfo();
        }
        else
        {
            throw new UnderflowException("top() was attempted on an empty Structure");
        }
    }

    @Override
    /**
     * Determines of the List contains anything in it
     * Specified By: isEmpty in interface IStack
     * @param None
     * @return Boolean - The value, true or false, depending on the status of the List
     */
    //checks if top values == null
    public boolean isEmpty()
    {
        return (top == null);
    }

    @Override
    /**
     * This Linked List called myStack is unbounded and thus is never Full
     * Specified By: isFull() in interface IStack
     * @param None
     * @return Boolean - The value, true or false, based on the upper limit of the List
     */
    //Allocated Linked lists don't have an upper limit
    public boolean isFull()
    {
        return false;
    }

    @Override
    /**
     * The amount of elements in the List. Determined through incrementing an index
     * Specified By: size() in interface IStack
     * @param None
     * @return int - The number of elements in the List
     */
    //the amount of elements in the list. Determined through incrementing an decrementing Index
    public int size()
    {
        return Index;
    }
    
    @Override
    public String toString()
    {
        return "myStack{" +
               "top=" + top +
               ", Index=" + Index +
               '}';
    }
}
