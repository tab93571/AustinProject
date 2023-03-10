package IOOCAlgorithm.heap;

public interface QueueIOOC<E> {

    int getSize();
    boolean isEmpty();
    void enqueue(E e);
    E dequeue();
    E getFront();
}
