package Main.Java.Structures;

import Main.Resources.Exceptions.DuplicateException;
import Main.Resources.Exceptions.UnderflowException;
import Main.Resources.Interfaces.IQueue;

/**
 * Purpose: To create a myQueue structure that takes generics
 * Order: enqueue(), dequeue(), isEmpty(), isFull(), size()
 * @author Cameron on 6/8/17
 */
public class myQueue<E extends Comparable<E>> implements IQueue
{
    @Override
    public String toString()
    {
        return "myQueue{" +
               "front=" + front +
               ", rear=" + rear +
               ", Index=" + Index +
               '}';
    }
    
    //Constructor
    public myQueue()
    {
        Index = 0;
        front = null;
        rear = null;
    }

    //Member Variables
    private Node front = new Node();
    private Node rear  = new Node();
    private int Index;

    @Override
    /**
     * Inserts a new element into the myQueue at the rear
     * Specified By: enqueue() in interface IQueue
     * @param Object element - The element the user wants to insert into the myQueue
     * @return Void
     * @throws DuplicateException - If the element is already present in the myQueue
     */
    //Create a node for the new item, insert node at the rear, update reference to rear
    public void enqueue(Object element)
    {
        Node temp;
        Node newNode = new Node();
        temp = front;
        
        for (int i = 0; i < size(); i++)
        {
            if (element != temp.getInfo())
            {
                temp = temp.getLink();
            }
            else
            {
                throw new DuplicateException("That element already exists in the myQueue");
            }
        }

        newNode.setInfo((E) element);
        newNode.setLink(null);

        if (rear == null)
        {
            front = newNode;
        }
        else
        {
            rear.setLink(newNode);
        }
        rear = newNode;
        Index++;
        
        return;
    }

    @Override
    /**
     * Removes the front of the myQueue in accordance with the FIFO principle of Queues
     * Specified By: Dequeque() in interface IQueue
     * @param None
     * @return Object - Returns the dequeued Object for possible manipulation
     * @throws UnderflowException - If the method is attempted on an empty myQueue
     */
    public Object dequeue()
    {
        Object item;
        
        if(!isEmpty())
        {
            item = front.getInfo();
            front = front.getLink();
            if (front == null)
            {
                rear = null;
            }
            Index--;
        }
        else
        {
            throw new UnderflowException("dequeue() attempted on an empty myQueue");
        }
        
        return item;
    }

    @Override
    /**
     * Checks to see if the myQueue has any contents
     * Specified By: isEmpty() in interface IQueue
     * @param None
     * @return Boolean - Returns true or false dependent on if myQueue has any elements in it
     */
    public boolean isEmpty()
    {
        return (front == null);
    }

    @Override
    /**
     * This Linked List is unbounded and by that nature is never full
     * Specified By: isFull() in interface IQueue
     * @param None
     * @return Boolean - Returns true or false dependent on if the myQueue has reached its upper limit
     */
    public boolean isFull()
    {
        return false;
    }

    @Override
    /**
     * Determines the amount of elements in the myQueue
     * Specified By:
     * @param None
     * @return int - Returns the number of elements in the myQueue
     */
    public int size()
    {
        return Index;
    }
}
