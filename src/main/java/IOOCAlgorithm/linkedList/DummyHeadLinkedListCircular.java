package IOOCAlgorithm.linkedList;

public class DummyHeadLinkedListCircular<E> {

    private class Node{
        public E e;
        public Node next;

        public Node(E e , Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e ){
            this(e, null);
        }

        public Node(){
            this(null,null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public DummyHeadLinkedListCircular(){
        dummyHead = new Node(null,null);
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void addFirst(E e){

        add(0,e);
    }

    //在鏈表index位置添加元素
    //在鏈表中不是一個常用的操作，練習用
    public void add(int index, E e){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add failed. Illegal Index");
        }

        Node prev = dummyHead; // dummyHead為0前面的節點

        //所以遍歷index次 會到 index前面的那個節點
        for(int i=0 ; i<index ; i ++){
            prev = prev.next;
        }
        prev.next = new Node(e,prev.next);
        size ++;

    }

    public void addLast(E e){
        add(size, e);
    }

    public E get(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed. Illegal index");
        }
        Node cur = dummyHead.next;
        for(int i = 0 ; i < index ; i ++){
            cur = cur.next;
        }
        return cur.e;
    }

    public E getFirst(){

        return get(0);
    }

    public E getLast(){

        return get(size - 1);
    }

    public void set(int index, E e){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed. Illegal index");
        }
        Node cur = dummyHead.next;
        for(int i = 0 ; i < index ; i ++){
            cur = cur.next;
        }
        cur.e = e;
    }

    public boolean contains(E e){
        Node cur = dummyHead.next;
        while(cur != null){
            if(cur.e.equals(e)){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public E remove(int index){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add failed. Illegal Index");
        }

        Node prev = dummyHead; // dummyHead為0前面的節點

        //所以遍歷index次 會到 index前面的那個節點
        for(int i=0 ; i<index ; i ++){
            prev = prev.next;
        }
        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size --;

        return retNode.e;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }



    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

//        Node cur = dummyHead.next;
//        while(cur != null){
//            res.append(cur+"->");
//            cur = cur.next;
//        }

        for(Node cur = dummyHead.next ; cur != null; cur = cur.next){
            res.append(cur+"->");
        }

        res.append("NULL");

        return res.toString();
    }

    public static void main(String[] args) {

        DummyHeadLinkedListCircular<Integer> linkedList = new DummyHeadLinkedListCircular<>();
        for(int i = 0 ; i < 5 ; i ++){
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.add(2,666);
        System.out.println(linkedList);

        linkedList.remove(2);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);
    }
}
