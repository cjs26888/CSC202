package Resources.Interfaces;

/**
 * Created by Biggus on 6/10/2017.
 */
public interface IIndex<E>
{
    int size();
    boolean contains(E element);
    void reset();
    boolean isEmpty();
    E getNext();
    void add(int index, E element);
    E set(int index, E element);
    E get(int index);
    int indexOf(E element);
    E remove(int index);
    @Override
    String toString();
}
