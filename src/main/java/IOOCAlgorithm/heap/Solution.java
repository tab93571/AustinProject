package IOOCAlgorithm.heap;

public class Solution {

    public static int[] getLeastNumbers(int[] arr, int k){

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0 ; i< k ; i ++){
            pq.enqueue(arr[i]);
        }

        for(int i = k; i< arr.length; i ++){
            if(!pq.isEmpty() && arr[i] < pq.getFront()){
                pq.dequeue();
                pq.enqueue(arr[i]);
            }
        }

        int [] res = new int[k];
        for(int i = 0; i < k; i ++){
            res[i] = pq.dequeue();
        }
        return res;
    }

    public static int findKthLargest(int[] arr, int k){
        MinHeap<Integer>minHeap = new MinHeap<>();
        for(int i = 0 ; i < k ; i ++){
            minHeap.add(arr[i]);
        }

        for(int i = k ; i < arr.length; i ++){
            if(!minHeap.isEmpty() && minHeap.findMin() < arr[i]){
                minHeap.replace(arr[i]);
            }
        }
        return minHeap.findMin();
    }


    public static void main(String[] args) {

        int [] arr = {4,5,1,6,2,7,3,8};
        int[] res = getLeastNumbers(arr,4);

        System.out.println(findKthLargest(arr,4));

    }
}
