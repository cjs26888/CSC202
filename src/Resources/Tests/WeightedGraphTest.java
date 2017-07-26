package Resources.Tests;

import Java.Structures.WeightedGraph;
import org.junit.jupiter.api.Test;

/**
 * Created by Biggus on 7/19/2017.
 */
class WeightedGraphTest
{
    @Test
    void shortestPath()
    {
        WeightedGraph<String> wg = new WeightedGraph<>();
        String a,b,c,d,e,f,g,h,i;
        
        a = "A";
        b = "B";
        c = "C";
        d = "D";
        e = "E";
        f = "F";
        g = "G";
        h = "H";
        i = "I";
    
        wg.addVertex(a);
        wg.addVertex(c);
        wg.addVertex(b);
        wg.addVertex(i);
        wg.addVertex(f);
        wg.addVertex(e);
        wg.addVertex(g);
        wg.addVertex(d);
        wg.addVertex(h);
        
        wg.addEdge(a, b, 1);
        wg.addEdge(a, c, 2);
        wg.addEdge(a, d, 3);
        wg.addEdge(b, e, 2);
        wg.addEdge(b, f, 3);
        wg.addEdge(c, f, 5);
        wg.addEdge(c, d, 8);
        wg.addEdge(c, g, 10);
        wg.addEdge(g, h, 2);
        wg.addEdge(g, i, 3);
        
        System.out.println(wg.shortestPath(a));
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
    void addVertex()
    {
    }
    
    @Test
    void addEdge()
    {
    }
    
    @Test
    void weightIs()
    {
    }
    
    @Test
    void getToVertices()
    {
    }
    
    @Test
    void clearMarks()
    {
    }
    
    @Test
    void markVertex()
    {
    }
    
    @Test
    void isMarked()
    {
    }
    
    @Test
    void hasVertex()
    {
    }
    
}