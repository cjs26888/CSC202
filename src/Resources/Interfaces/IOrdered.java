package Resources.Interfaces;

/**
 * Created by Biggus on 6/8/2017.
 */
public interface IOrdered<E>
{
    void add(E element);
    boolean remove(E element);
    boolean contains(E element);
    boolean isEmpty();
    int size();
    E get(E element);
    E get();
    void reset();
    E getNext();
    @Override
    String toString();
}
