package IOOCAlgorithm.binarySearchTree;

import IOOCAlgorithm.linkedList.DummyHeadLinkedList;

public class LinkedListSet <E> implements Set<E>{

    private DummyHeadLinkedList<E> list;

    public LinkedListSet(){
        list = new DummyHeadLinkedList<>();
    }

    @Override
    public void add(E e) {
        if(!list.contains(e)){
            list.addFirst(e);
        }

    }

    @Override
    public void remove(E e) {

    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
