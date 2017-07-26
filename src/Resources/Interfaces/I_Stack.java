package Resources.Interfaces;

/**
 * Created by Biggus on 6/8/2017.
 */
public interface I_Stack<E>{
    void push(E element);
    void pop();
    E top();
    boolean isEmpty();
    boolean isFull(/*Array*/);
    int size();
    @Override
    String toString();
}
