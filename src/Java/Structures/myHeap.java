package Java.Structures;

import Resources.Exceptions.OverflowException;
import Resources.Exceptions.UnderflowException;
import Resources.Interfaces.I_PriorityQueue;

/**
 * Created by Biggus on 7/19/2017.
 */
public class myHeap<E extends Comparable<E>> implements I_PriorityQueue
{
    private E[] elements;
    private int lastIndex;
    private int maxIndex;
    
    // Constructor
    public myHeap(int maxSize)
    {
        elements = (E[]) new Comparable[maxSize];
        lastIndex = -1;
        maxIndex = maxSize - 1;
    }
    
    @Override
    public String toString()
    {
        String tmpStr = "";
        
        for(int i = 0; i <= maxIndex; i++)
        {
            if(elements[i] != null)
            {
                tmpStr += ("{{" + elements[i].toString() + "}}\n");
            }
            else
            {
                tmpStr += "{{" + ((maxIndex + 1) - i) + " empty slot(s)}}";
                i = maxIndex;
            }
        }
        
        return "Heap of size " + (maxIndex + 1) + ": " + "\n" + tmpStr + "\n";
    }
    
    //Member Methods
    public boolean isEmpty()
    {
        return (lastIndex == -1);
    }
    
    public boolean isFull()
    {
        return (lastIndex == maxIndex);
    }
    
    @Override
    public void enqueue(Object item) throws OverflowException
    {
            if(lastIndex == maxIndex)
            {
                throw new OverflowException("Priority queue is full");
            }
            else
            {
                lastIndex = lastIndex + 1;
                reheapUp((Comparable) item);
            }
    }
    
    @Override
    public Comparable dequeue() throws UnderflowException
    {
        Comparable hold;
        Comparable toMove;
        if (lastIndex == -1)
        {
            throw new UnderflowException("Priority queue is empty");
        }
        else
        {
            hold = elements[0];
            toMove = elements[lastIndex];
            lastIndex = lastIndex - 1;
            reheapDown(toMove);
            return hold;
        }
    }
    
    private void reheapUp(Comparable item)
    {
        int hole = lastIndex;
        while ((hole > 0) && item.compareTo(elements[(hole - 1) / 2]) > 0)
        {
            elements[hole] = elements[(hole - 1) / 2];
            hole = (hole - 1) / 2;
        }
        elements[hole] = (E) item;
    }
    
    private int newHole(int hole, Comparable item)
    {
        int left = (hole * 2) + 1;
        int right = (hole * 2) + 2;
        if (left > lastIndex)
        {
            return hole;
        }
        else if (left == lastIndex)
        {
            if (item.compareTo(elements[left]) < 0)
            {
                return left;
            }
            else
            {
                return hole;
            }
        }
        else if (elements[left].compareTo(elements[right]) < 0)
        {
            if (elements[right].compareTo((E) item) <= 0)
            {
                return hole;
            }
            else
            {
                return right;
            }
        }
        else if (elements[left].compareTo((E) item) <= 0)
        {
            return hole;
        }
        else
        {
            return left;
        }
    }
    
    private void reheapDown(Comparable item)
    {
        int hole = 0;
        int newhole;
        newhole = newHole(hole, item);
        while (newhole != hole)
        {
            elements[hole] = elements[newhole];
            hole = newhole;
            newhole = newHole(hole, item);
        }
        elements[hole] = (E) item;
    }
    
    public int sizeOf()
    {
        int count = 0;
        
        for(int i = 0; i < maxIndex; i++)
        {
            if(elements[i] != null || elements[i] != "")
            {
                count++;
            }
        }
        
        return count;
    }
}
