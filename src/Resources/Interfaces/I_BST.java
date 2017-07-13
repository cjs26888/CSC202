package Resources.Interfaces;

/**
 * Created by Biggus on 6/23/2017.
 */
public interface I_BST<T>
{
    //Traversal methods
    int INORDER = 1;
    int PREORDER = 2;
    int POSTORDER= 3;
    
    //In book
    boolean isEmpty();
    
    //In book
    boolean isFull();
    
    //Called numberOfNodes() in book
    int size();
    
    //Called isThere() in book
    boolean contains(T item);
    
    //Called retrieve() in book
    T get(T item);
    
    //Called insert() in book
    void add(T item);
    
    //Called delete() in book
    void remove(T item);
    
    //In book
    int reset(int orderType);
    
    //Called getNextItem() in book
    T getNext(int orderType);
    
    //Called printTree() in book
    //Needs to hava a file open and able to be written to before this is called
    String toString();
}
