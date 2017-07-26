package Resources.Tests;

import Java.Structures.myHeap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Biggus on 7/20/2017.
 */
class myHeapTest
{
    @Test
    void testToString()
    {
        final int SIZE = 20;
        myHeap test = new myHeap(SIZE);
    
        test.enqueue("A");
        test.enqueue("B");
        test.enqueue("C");
        test.enqueue("D");
    
        assertEquals("Heap of size " + SIZE + ": \n{{D}}\n{{C}}\n{{B}}\n{{A}}\n{{16 empty slot(s)}}\n", test.toString());
    }
    
    @Test
    void isEmpty()
    {
    }
    
    @Test
    void isFull()
    {
    }
    
    @Test
    void enqueue()
    {
    }
    
    @Test
    void dequeue()
    {
    }
    
    @Test
    void sizeOf()
    {
    }
    
}