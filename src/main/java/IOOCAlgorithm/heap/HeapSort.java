package IOOCAlgorithm.heap;

public class HeapSort {
    private HeapSort(){}

    public static <E extends Comparable<E>> void sort(E[] data){
        MaxHeap<E> maxHeap = new MaxHeap<>();
        for(E e : data){
            maxHeap.add(e);
        }

        for(int i = data.length - 1; i >= 0; i --){
            data[i] = maxHeap.extractMax();
        }


    }


    public static <E extends Comparable<E>> void sortRefine(E[] data){

        if(data.length <= 1){
            return;
        }

        for(int i = (data.length - 2) / 2 ; i >= 0 ; i --){
            siftDown(data,i, data.length);
        }

        for(int i = data.length - 1 ; i >= 0 ; i --){
            swap(data, 0, i);
            siftDown(data,0, i);
        }




    }
    //對 data[0, n) 所形成的最大堆中，索引K的元素，執行siftDown
    private static<E extends Comparable<E>> void siftDown(E[] data, int k, int n){

        while(2 * k + 1 < n){

            int j = 2 * k + 1;
            if(j + 1 < n &&
                    data[j + 1].compareTo(data[j]) > 0){
                j = 2 * k + 2;
            }
            //data[j] 是leftChild和rightChild中的最大值
            if(data[k].compareTo(data[j]) >= 0){
                break;
            }
            swap(data,k,j);
            k = j;
        }
    }

    public static <E> void swap(E[] arr,int i, int j){


        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;

    }


}
