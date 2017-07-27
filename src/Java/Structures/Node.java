package Java.Structures;

/**
 * Created by Biggus on 6/13/2017.
 */
class Node<E extends Comparable<E>>
{
    E                    Info;
    Java.Structures.Node Link;
    int                  Index;
    
    public Node(E i, Java.Structures.Node l)
    {
        this.setInfo(i);
        this.setLink(l);
    }
    
    public Node(Java.Structures.Node l, E i)
    {
        this.setInfo(i);
        this.setLink(l);
    }
    
    public Node(E i)
    {
        this.setInfo(i);
    }
    
    public Node(Java.Structures.Node l)
    {
        this.setLink(l);
    }
    
    public Node()
    {
        this.setLink(null);
        this.setInfo(null);
    }

    //Getters
    /**
     * @return int - The index of the node calling this function
     */
    int getIndex() {return Index;}
    /**
     * @return E - The information held by the node calling this function
     */
    E getInfo(){return Info;}
    /**
     * @return Node - The node that the node which calls this function is connected to
     */
    Node getLink(){return Link;}

    //Setters
    /**
     * @param n - Changes the index of the node that calls this function
     * @return void
     */
    void setIndex(int n) {this.Index = n;}
    /**
     * @param i - The info that the Node calling this function needs to be changed to
     * @return void
     */
    void setInfo(E i){this.Info = i;}
    /**
     * @param n - the node that the node calling this function is to be linked to
     * @return void
     */
    void setLink(Node n){this.Link = n;}

    /**
     * @return String - A visual representation of the Node calling this function
     */
    @Override
    public String toString()
    {
        return "[Node (" + Info + ")]---->LINK: " + Link;
    }

    /**
     * @param cmpNode - The node to be compared to the Node that calls this function
     * @return int - The integer returned by a compareTo() of an Object
     */
    public int compareTo(Node cmpNode)
    {
        int compare = cmpNode.getInfo().compareTo(this.Info);
        return ((compare != 0) ? compare : this.Link.compareTo(cmpNode.getLink()));
    }
    
}
