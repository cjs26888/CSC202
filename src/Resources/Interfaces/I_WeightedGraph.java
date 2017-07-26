package Resources.Interfaces;

import Java.Structures.myQueue;

/**
 * Created by Biggus on 7/19/2017.
 */
public interface I_WeightedGraph<E>
{
    public boolean isEmpty();
    public boolean isFull();
    public void addVertex(E vertex);
    public void addEdge(E fromVertex, E toVertex, int weight);
    public int weightIs(E fromVertex, E toVertex);
    public myQueue getToVertices(E vertex);
    public void clearMarks();
    public void markVertex(E vertex);
    public boolean isMarked(E vertex);
    public boolean hasVertex(E vertex);
}
