package IOOCAlgorithm.binarySearch;

public class LowerUpperFloor {

    public static void main(String[] args) {
        Integer[] data = {1,1,3,3,5,5};
//        for(int i = 0 ; i <= 6 ; i ++){
//            System.out.println(search(data,i)+ " ");
//        }

    }

    private LowerUpperFloor(){}

    public static <E extends Comparable<E>> int lowerFloor(E[] data, E target){
        int l = -1, r = data.length - 1;

        //在data [l, r]中尋找解

        //在 data[l, r] 的範圍內查找 target
        while(l < r){
            System.out.println(l + " " + r);
            int mid = l + (r-l+1) / 2;

            if(data[mid].compareTo(target) < 0){
                l = mid; //往右側找，但mid有可能是解
            }

            if(data[mid].compareTo(target) >= 0){
                r = mid -1;
            }

        }
        if(l+1 < data.length && data[l+1].compareTo(target) == 0){
            return l + 1;
        }

        return l;

    }


    public static <E extends Comparable<E>> int upperFloor(E[] data, E target){
        int l = -1, r = data.length - 1;

        //在data [l, r]中尋找解

        //在 data[l, r] 的範圍內查找 target
        while(l < r){
            System.out.println(l + " " + r);
            int mid = l + (r-l+1) / 2;

            if(data[mid].compareTo(target) <= 0){
                l = mid; //往右側找，但mid有可能是解
            }

            if(data[mid].compareTo(target) > 0){
                r = mid -1;
            }

        }
        return l;

    }




}
