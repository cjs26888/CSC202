package Resources.Interfaces;

/**
 * Created by Biggus on 7/13/2017.
 */
public interface I_PriorityQueue<E>
{
    public boolean isEmpty();
    public boolean isFull();
    public void enqueue(E item);
    public E dequeue();
}
