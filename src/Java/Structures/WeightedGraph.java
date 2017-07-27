package Java.Structures;

import Resources.Exceptions.InvalidInputException;
import Resources.Exceptions.OverflowException;
import Resources.Exceptions.UnderflowException;
import Resources.Interfaces.I_WeightedGraph;

/**
 * Created by Biggus on 7/19/2017.
 */
//    todo Look into combining hasVertices and indexOf
public class WeightedGraph<E extends Comparable<E>> implements I_WeightedGraph
{
    public static int NULL_EDGE = 0;
    private int numVertices;
    private int maxVertices;
    private Object[] vertices;
    private int[][] edges;
    private boolean[] marks;
    
    //Accessors and Mutators
    /*

    public int getMaxVertices()
    {
        return maxVertices;
    }
    
    public void setMaxVertices(int maxVertices)
    {
        this.maxVertices = maxVertices;
    }
    
    public Object[] getVertices() {return vertices;}
    
    public void setVertices(Object[] vertices)
    {
        this.vertices = vertices;
    }
    
    public int[][] getEdges()
    {
        return edges;
    }
    
    public int getEdges(int i, int j) {return edges[i][j];}
    
    public void setEdges(int[][] edges)
    {
        this.edges = edges;
    }
    
    public boolean[] getMarks()
    {
        return marks;
    }
    
    public void setMarks(boolean[] marks)
    {
        this.marks = marks;
    }

    public void setNumVertices(int numVertices)
    {
        this.numVertices = numVertices;
    }

    */

    //Constructors
    public WeightedGraph()
    {
        numVertices = 0;
        maxVertices = 101;
        vertices = new Object[101];
        marks = new boolean[101];
        edges = new int[101][101];
    }
    public WeightedGraph(int maxV)
    {
        numVertices = 0;
        maxVertices = maxV;
        vertices = new Object[maxV];
        marks = new boolean[maxV];
        edges = new int[maxV][maxV];
    }

    /**
     * @return - int the Number of vectors added to the graph
     */
    public int getNumVertices()
    {
        return numVertices;
    }

    /**
     *
     * @param i - the index at which to get the object in the graph
     * @return Object - the object at the index in the graph
     */
    public Object getVertices(int i)
    {
        return vertices[i];
    }
    
    //Member Methods

    /**
     *
     * @param startVertex - The vertex to start the search at
     * @param endVertex - the vertex that the search is targeted at
     * @return boolean - whether the object, endVertex, was found in the graph or not
     */
    private boolean depthFirstSearch(E startVertex, E endVertex)
    {
        myStack stack       = new myStack();
        myQueue vertexQueue = new myQueue();
    
        boolean found = false;
        E       vertex;
        E       item;
        
        if(!isEmpty())
        {
            clearMarks();
            stack.push(startVertex);
            do
            {
                vertex = (E) stack.top();
                stack.pop();
                if (vertex == endVertex)
                {
                    found = true;
                }
                else
                {
                    if (!isMarked(vertex))
                    {
                        markVertex(vertex);
                        vertexQueue = getToVertices(vertex);
                    }
                    while (!vertexQueue.isEmpty())
                    {
                        item = (E) vertexQueue.dequeue();
                        if (!isMarked(item))
                        {
                            stack.push(item);
                        }
                    }
                }
            }
            while (!stack.isEmpty() && !found);
        }
        else
        {
            throw new UnderflowException("The graph is empty");
        }
        return found;
    }

    /**
     *
     * @param startVertex - the vertex to start the search at
     * @param endVertex - what the function is looking for in this search
     * @return boolean - whether or not the object was found
     */
    private boolean breadthFirstSearch(E startVertex, E endVertex)
    {
        myQueue queue = new myQueue();
        myQueue vertexQueue = new myQueue();
        
        boolean found = false;
        E vertex;
        E item;
        
        if(!isEmpty())
        {
            clearMarks();
            queue.enqueue(startVertex);
            do
            {
                vertex = (E) queue.dequeue();
                if (vertex == endVertex)
                {
                    found = true;
                }
                else
                {
                    if (!isMarked(vertex))
                    {
                        markVertex(vertex);
                        vertexQueue = getToVertices(vertex);
    
                        while (!vertexQueue.isEmpty())
                        {
                            item = (E) vertexQueue.dequeue();
                            if (!isMarked(item))
                            {
                                queue.enqueue(item);
                            }
                        }
                    }
                }
            }
            while (!queue.isEmpty() && !found);
        }
        else
        {
            throw new UnderflowException("Graph is empty");
        }
        
        return found;
    }

    /**
     *
     * @param vertex - The object that is to be found in the graph
     * @return int - the index of the passed object in the graph
     */
    public int indexIs(Object vertex)
    {
        int index = 0;
        if(!isEmpty())
        {
            if (hasVertex(vertex))
            {
                while (vertex != vertices[index])
                {
                    index++;
                }
            }
            else
            {
                throw new InvalidInputException("That element doesn't exist");
            }
        }
        else
        {
            throw new UnderflowException("Graph is empty");
        }
        return index;
    }

    /**
     *
     * @param startVertex - The start of the search for the shortest path
     * @return myOrderedList - the list of the objects in order of shortest to longest path
     */
    public myOrderedList shortestPath(E startVertex)
    {
        class Roads implements Comparable
        {
            private Object fromVertex;
            private Object toVertex;
            private int distance;
            public int compareTo(Object otherFlights)
            {
                Roads other = (Roads)otherFlights;
                return (other.distance - this.distance);
            }
    
            @Override
            public String toString()
            {
                String str = "";
    
                str += "\n[From], {Distance}, (To)";
                str += "\n============";
                for(int i = 0; i < Integer.toString(distance).length() + 3; i++)
                {
                    str += "=";
                }
                str += "============\\\\" + "\n";
    
                str += "---[" + fromVertex.toString() + "]-----{" + distance + " km}-----(" + toVertex.toString() + ")--->>" + "\n";
                
                str += "============";
                for(int i = 0; i < Integer.toString(distance).length() + 3; i++)
                {
                    str += "=";
                }
                str += "============//" + "\n";
                
                
                return str;
            }
        }
        
        Roads   roadItem;
        int     minDistance;
        myHeap  pq         = new myHeap(maxVertices * maxVertices + 1);
        Object  vertex;
        myQueue vertexQueue;
        myOrderedList pathHolder = new myOrderedList();
        Roads   saveItem   = new Roads();
        
        saveItem.fromVertex = startVertex;
        saveItem.toVertex = startVertex;
        saveItem.distance = 0;
        
        clearMarks();
        pq.enqueue(saveItem);
        
        do
        {
            roadItem = (Roads)pq.dequeue();
            if (!isMarked(roadItem.toVertex))
            {
                if(roadItem.fromVertex != roadItem.toVertex)
                {
                    pathHolder.add(startVertex);
                    pathHolder.add(roadItem.toVertex);
                    pathHolder.add(roadItem.distance);
                }
                markVertex(roadItem.toVertex);
                roadItem.fromVertex = roadItem.toVertex;
                minDistance = roadItem.distance;
                vertexQueue = getToVertices(roadItem.fromVertex);
                while (!vertexQueue.isEmpty())
                {
                    vertex = vertexQueue.dequeue();
                    if (!isMarked(vertex))
                    {
                        saveItem = new Roads();
                        saveItem.fromVertex = roadItem.fromVertex;
                        saveItem.toVertex = vertex;
                        saveItem.distance = minDistance + weightIs(roadItem.fromVertex, vertex);
                        pq.enqueue(saveItem);
                    }
                }
            }
        } while (!pq.isEmpty());
        
        return pathHolder;
    }

    /**
     *
     * @return - boolean - whether the graph is empty or not
     */
    @Override
    public boolean isEmpty() { return numVertices == 0; }

    /**
     *
     * @return - boolean - whether the graph is empty or not
     */
    @Override
    public boolean isFull() { return numVertices == maxVertices; }

    /**
     *
     * @param vertex - The vertex to be added to the graph
     */
    @Override
    public void addVertex(Object vertex)
    {
        if(!isFull())
        {
            vertices[numVertices] = vertex;
            for (int i = 0; i < (numVertices + 1); i++)
            {
                edges[numVertices][i] = NULL_EDGE;
                edges[i][numVertices] = NULL_EDGE;
            }
            numVertices++;
        }
        else
        {
            throw new OverflowException("This Graph is full");
        }
        
        //fromvert is vertex in the spot in the array before this one
        //tovert is the vertex here
        //weight is the distance calulated
    }

    /**
     *
     * @param fromVertex - The vertex that could be considered the beginning of the edge
     * @param toVertex - the destination of the edge, when reaching this vertex the weight is the "distance" of the edge
     * @param weight - The given "length" of the edge.
     * @throws OverflowException - if the graph is full
     */
    @Override
    public void addEdge(Object fromVertex, Object toVertex, int weight)
    {
        int row;
        int column;
        if(!isFull())
        {
            row = indexIs(fromVertex);
            column = indexIs(toVertex);
            edges[row][column] = weight;
        }
        else
        {
            throw new OverflowException("This graph is full");
        }
    }

    /**
     *
     * @param fromVertex - The start of the edge
     * @param toVertex - The end of edge that is to given weight
     * @return int - The calculated weight
     */
    @Override
    public int weightIs(Object fromVertex, Object toVertex)
    {
        int row;
        int column;
        if(!isEmpty())
        {
            row = indexIs(fromVertex);
            column = indexIs(toVertex);
        }
        else
        {
            throw new UnderflowException("This graph is empty");
        }
        return edges[row][column];
    }

    /**
     *
     * @param vertex - The vertex that is to have its vertices evaluated
     * @return myQueue - The queue of vertices coming from the passed vertices
     * @throws UnderflowException - if the graph is empty
     */
    @Override
    public myQueue getToVertices(Object vertex)
    {
        myQueue adjVertices = new myQueue();
        int fromIndex, toIndex;
        if(!isEmpty())
        {
            fromIndex = indexIs(vertex);
            for (toIndex = 0; toIndex < numVertices; toIndex++)
            {
                if (edges[fromIndex][toIndex] != NULL_EDGE)
                {
                    adjVertices.enqueue(vertices[toIndex]);
                }
            }
        }
        else
        {
            throw new UnderflowException("This graph is empty");
        }
        return adjVertices;
    }

    /**
     * @throws UnderflowException - if the graph is empty
     */
    @Override
    public void clearMarks()
    {
        if(!isEmpty())
        {
            for(int i = 0; i < maxVertices; i++)
            {
                marks[i] = false;
            }
        }
        else
        {
            throw new UnderflowException("This graph is empty");
        }
    }

    /**
     *
     * @param vertex - The vertex to mark in an equivalent spot in the marks array
     * @throws UnderflowException - If the graph is empty
     */
    @Override
    public void markVertex(Object vertex)
    {
        if(!isEmpty())
        {
            marks[indexIs(vertex)] = true;
        }
        else
        {
            throw new UnderflowException("This graph is empty");
        }
    }

    /**
     *
     * @param vertex - The vertex to determine if it is marked
     * @return boolean - the value of whether the vertex is marked
     */
    @Override
    public boolean isMarked(Object vertex)
    {
        return marks[indexIs(vertex)];
    }

    /**
     *
     * @param vertex - The vertex to be looked for in the graph
     * @return boolean - Whether the graph has the vertex passed to it
     * @throws UnderflowException - If the graph is empty
     */
    @Override
    public boolean hasVertex(Object vertex)
    {
        boolean flag = false;
        if(!isEmpty())
        {
            for (int i = 0; i < numVertices; i++)
            {
                if (vertices[i] == vertex)
                {
                    flag = true;
                    break;
                }
            }
        }
        else
        {
            throw new UnderflowException("This graph is empty");
        }
        return flag;
    }
}
