package IOOCAlgorithm.binarySearch;

public class BinarySearchUpper {

    public static void main(String[] args) {
        Integer[] data = {23,56,65,69,72,89,96,99};
        int index = search(data,100);
        System.out.println(index);
    }

    private BinarySearchUpper(){}

    public static <E extends Comparable<E>> int search(E[] data, E target){
        int l =0, r = data.length;

        //在data [l, r]中尋找解

        //在 data[l, r] 的範圍內查找 target
        while(l < r){
            int mid = l + (r-l) / 2;

            if(data[mid].compareTo(target) > 0){
                r = mid;// 比target大的話 mid有可能是解
            }

            if(data[mid].compareTo(target) <= 0){
                l = mid +1;
            }

        }
        return l;

    }




}


