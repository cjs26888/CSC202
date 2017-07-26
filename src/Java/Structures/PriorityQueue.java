package Java.Structures;

import Resources.Exceptions.DuplicateException;
import Resources.Exceptions.UnderflowException;
import Resources.Interfaces.I_PriorityQueue;

/**
 * Created by Biggus on 7/13/2017.
 */
public class PriorityQueue<E extends Comparable<E>> implements I_PriorityQueue
{
    //Constructor
    public PriorityQueue(E item){this.enqueue(item);}
    public PriorityQueue(){front = null; rear = null;}
    
    private Node front = new Node();
    private Node rear  = new Node();
    
    @Override
    public boolean isEmpty() { return (front == null); }
    
    @Override
    public boolean isFull() { return false; }
    
    @Override
    public void enqueue(Object item)
    {
        Node New     = new Node((E) item);
        Node temp = null;
        int size = size();
        
        if(!isEmpty())
        {
            temp = front;
            for (int i = 0; i < size; i++)
            {
                try
                {
                    if (item != temp.getInfo() && item != temp.getLink().getInfo())
                    {
                        temp = temp.getLink();
                    }
                    else
                    {
                        throw new DuplicateException("That element already exists in the myQueue");
                    }
                }
                catch (NullPointerException e)
                {
                    i++;
                }
            }
    
            temp = front;
            if (temp.getInfo().compareTo(New.getInfo()) > 0)
            {
                New.setLink(temp);
                front = New;
            }
            else
            {
                for (int i = 0; i < size; i++)
                {
                    try
                    {
                        if (temp.getLink().getInfo().compareTo(New.getInfo()) > 0)
                        {
                            New.setLink(temp.getLink());
                            temp.setLink(New);
                            break;
                        }
                        else
                        {
                            temp = temp.getLink();
                        }
                    }
                    catch(NullPointerException x)
                    {
                        temp.setLink(New);
                    }
                }
            }
        }
        else
        {
            front = rear = New;
        }
    }
    
    @Override
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
        }
        else
        {
            throw new UnderflowException("dequeue() attempted on an empty myQueue");
        }
        
        return item;
    }
    
    @Override
    public String toString()
    {
        return "Priority Queue: " + "\n" + front;
    }
    
    public int size()
    {
        int  count = 1;
        
        if(!isEmpty())
        {
            Node tmp   = front;
            while (tmp.getLink() != null)
            {
                tmp = tmp.getLink();
                count++;
            }
        }
        else
        {
            count = 0;
        }
        return count;
    }
}
