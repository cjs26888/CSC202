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
    public int getMaxVertices()
    {
        return maxVertices;
    }
    
    public void setMaxVertices(int maxVertices)
    {
        this.maxVertices = maxVertices;
    }
    
    public Object getVertices(int i)
    {
        return vertices[i];
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
    
    public int getNumVertices()
    {
        return numVertices;
    }
    
    public void setNumVertices(int numVertices)
    {
        this.numVertices = numVertices;
    }
    
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
    
    //Member Methods
    private boolean depthFirstSearch(WeightedGraph graph, E startVertex, E endVertex)
    {
        myStack stack       = new myStack();
        myQueue vertexQueue = new myQueue();
    
        boolean found = false;
        E       vertex;
        E       item;
        
        if(!isEmpty())
        {
            graph.clearMarks();
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
                    if (!graph.isMarked(vertex))
                    {
                        graph.markVertex(vertex);
                        vertexQueue = graph.getToVertices(vertex);
                    }
                    while (!vertexQueue.isEmpty())
                    {
                        item = (E) vertexQueue.dequeue();
                        if (!graph.isMarked(item))
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
    
    private boolean breadthFirstSearch(WeightedGraph graph, E startVertex, E endVertex)
    {
        myQueue queue = new myQueue();
        myQueue vertexQueue = new myQueue();
        
        boolean found = false;
        E vertex;
        E item;
        
        if(!isEmpty())
        {
            graph.clearMarks();
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
                    if (!graph.isMarked(vertex))
                    {
                        graph.markVertex(vertex);
                        vertexQueue = graph.getToVertices(vertex);
    
                        while (!vertexQueue.isEmpty())
                        {
                            item = (E) vertexQueue.dequeue();
                            if (!graph.isMarked(item))
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
    
//    todo Return Queue of objects in order
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
    
    @Override
    public boolean isEmpty() { return numVertices == 0; }
    
    @Override
    public boolean isFull() { return numVertices == maxVertices; }
    
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
    
    @Override
    public boolean isMarked(Object vertex)
    {
        return marks[indexIs(vertex)];
    }
    
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
