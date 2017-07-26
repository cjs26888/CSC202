package Resources.Interfaces;

/**
 * Created by Biggus on 6/8/2017.
 */
public interface I_Queue<E>{
    void enqueue(E element);
    E dequeue();
    boolean isEmpty();
    boolean isFull(/*Array*/);
    int size();
    @Override
    String toString();
}
