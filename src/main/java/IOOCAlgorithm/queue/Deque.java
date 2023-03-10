package IOOCAlgorithm.queue;

public class Deque<E> {

    private E[] data;
    private int front,tail;
    private int size;

    public Deque(int capacity){
        data = (E[])new Object[capacity];
        front = 0;
        tail = 0;
        size = 0;
    }

    public Deque(){
        this(10);
    }

    public int getCapacity(){
        return data.length;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addLast(E e) {

//        if((tail == front) && size >0){
        if(size == getCapacity()){
            resize(getCapacity()*2);
        }

        data[tail] = e;
        tail = (tail + 1 ) % data.length;
        size ++;
    }

    public void addFront(E e) {

//        if((tail == front) && size >0){
        if(size == getCapacity()){
            resize(getCapacity()*2);
        }
        front = front ==  0 ? data.length - 1 : front - 1;
        data[front] = e;
        size ++;
    }

    public E removeFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("Can not deque from an empty queue");
        }

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size -- ;

        if(size == getCapacity() / 4 && getCapacity() / 2 != 0){
            resize(getCapacity() / 2 );
        }

        return ret;
    }

    public E removeLast() {
        if(isEmpty()){
            throw new IllegalArgumentException("Can not deque from an empty queue");
        }

        E ret = data[tail - 1];
        data[tail - 1] = null;
        tail = tail == 0 ? data.length - 1 : tail - 1;
        size -- ;

        if(size == getCapacity() / 4 && getCapacity() / 2 != 0){
            resize(getCapacity() / 2 );
        }

        return ret;
    }

    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("Can not deque from an empty queue");
        }

        return data[front];
    }

    public E getLast(){
        if(isEmpty())
            throw new IllegalArgumentException("Queue is empty.");

        // ?????? tail ????????????????????????????????????????????????????????????????????????????????????????????????
        int index = tail == 0 ? data.length - 1 : tail - 1;
        return data[index];
    }

    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];

        for(int i = 0 ; i <size ; i ++){
            newData[i] = data[(front+i)%data.length];
        }

        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {

        //???resize ????????????????????? toString ???????????????tail????????????????????????????????????
        //resize??????????????????size??????????????????

        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d, capacity = %d\n", size, getCapacity()));
        res.append("front [");


         //???????????????????????????????????????????????????????????????????????? :-)
        for(int i = 0; i < size; i ++){
            res.append(data[(front + i) % data.length]);
            if((i + front + 1) % data.length != tail)
                res.append(", ");
        }

        res.append("] tail");
        return res.toString();
    }


    public static void main(String[] args){

        // ????????????????????????????????????????????????????????????????????????????????????
        Deque<Integer> dq = new Deque<>();
        for(int i = 0 ; i < 16 ; i ++){
            if(i % 2 == 0) dq.addLast(i);
            else dq.addFront(i);
            System.out.println(dq);
        }

        // ?????????????????????????????????????????????????????????
        System.out.println();
        for(int i = 0; !dq.isEmpty(); i ++){
            if(i % 2 == 0) dq.removeFront();
            else dq.removeLast();
            System.out.println(dq);
        }
    }


}

