package Main.Java.Structures;

/**
 * Created by Biggus on 6/13/2017.
 */
class Node<E extends Comparable<E>>
{
    E Info;
    Node Link;
    int Index;
    
    public Node(E i, Node l)
    {
        this.setInfo(i);
        this.setLink(l);
    }
    
    public Node(Node l, E i)
    {
        this.setInfo(i);
        this.setLink(l);
    }
    
    public Node(E i)
    {
        this.setInfo(i);
    }
    
    public Node(Node l)
    {
        this.setLink(l);
    }
    
    public Node()
    {
        this.setLink(null);
        this.setInfo(null);
    }
    
    int getIndex() {return Index;}
    E getInfo(){return Info;}
    Node getLink(){return Link;}
    
    @Override
    public String toString()
    {
        return "Node{" +
               "Info=" + Info +
               ", Link=" + Link +
               ", Index=" + Index +
               '}';
    }
    
    void setIndex(int n) {this.Index = n;}
    void setInfo(E i){this.Info = i;}
    void setLink(Node n){this.Link = n;}
    
    public int compareTo(Node cmpNode)
    {
        int compare = cmpNode.getInfo().compareTo(this.Info);
        return ((compare != 0) ? compare : this.Link.compareTo(cmpNode.getLink()));
    }
    
}
