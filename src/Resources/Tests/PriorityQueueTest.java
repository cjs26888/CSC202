package Resources.Tests;

import Java.Structures.PriorityQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Biggus on 7/18/2017.
 */
class PriorityQueueTest
{
    @Test
    void enqueue()
    {
        PriorityQueue<String> newGuy = new PriorityQueue<>();
        String A,B,C;
        A = "A";
        B = "B";
        C = "C";
        
        newGuy.enqueue(A);
        newGuy.enqueue(C);
        newGuy.enqueue(B);
        
        //Even though they are enqueued not in order the priority queue should display them in order
        assertEquals("Priority Queue: \n[Node (A)]---->LINK: [Node (B)]---->LINK: [Node (C)]---->LINK: null", newGuy.toString());
    }
    
    @Test
    void dequeue()
    {
    }
    
    @Test
    void testToString()
    {
    }
    
    @Test
    void size()
    {
    }
    
}