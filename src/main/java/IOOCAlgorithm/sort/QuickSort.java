package IOOCAlgorithm.sort;

import IOOCAlgorithm.util.ArrayGenerator;
import IOOCAlgorithm.util.SortingHelper;

import java.util.Random;

public class QuickSort {

    private static Random random = new Random();


    public static void main(String[] args) {
//        Integer[] arr = {4,6,5,2,3,8,4,1};
//        QuickSort s = new QuickSort();
//        s.sort(arr);
//        for(Integer one : arr){
//            System.out.println(one);
//        }
        int n = 10000;

//        Integer [] arr3 = ArrayGenerator.generateRandomArray(n,n);
//        SortingHelper.sortTest("quickSort",arr3);
//
//        Integer [] arr5 = ArrayGenerator.generateRandomArray(n,n);
//        SortingHelper.sortTest("quickSort2",arr5);
////
//        Integer [] arr4 = ArrayGenerator.generateRandomArray(n,n);
//        SortingHelper.sortTest("mergeSort4",arr4);


//        Integer [] arr5 = ArrayGenerator.generateOrderedArray(n);
//        SortingHelper.sortTest("quickSort2",arr5);
////
//        Integer [] arr4 = ArrayGenerator.generateOrderedArray(n);
//        SortingHelper.sortTest("mergeSort4",arr4);

//        Integer [] arr6 = ArrayGenerator.generateRandomArray(n,1);//0到0
//        SortingHelper.sortTest("quickSort2",arr6);

        Integer [] arr7 = ArrayGenerator.generateRandomArray(n,1);//0到0
        SortingHelper.sortTest("quickSort2Ways",arr7);

        Integer [] arr8 = ArrayGenerator.generateRandomArray(n,1);//0到0
        SortingHelper.sortTest("quickSort3Ways",arr8);


    }

    public  static <E extends Comparable> void sort(E[] arr){
        sort(arr,0,arr.length-1);

    }
    public  static <E extends Comparable> void sort2(E[] arr){
        sort2(arr,0,arr.length-1);

    }

    public  static <E extends Comparable> void sort2Ways(E[] arr){
        sort2Ways(arr,0,arr.length-1);

    }
    public  static <E extends Comparable> void sort3Ways(E[] arr){
        Random rnd = new Random();
        sort3Ways(arr,0,arr.length-1, rnd);

    }

    public  static <E extends Comparable> void sort(E[] arr, int l, int r){

        if(l >= r){
            return;
        }
        int p = partition(arr, l, r);

        sort(arr,l, p - 1);
        sort(arr, p + 1 ,r);
    }

    public  static <E extends Comparable> void sort2(E[] arr, int l, int r){

        if(r-l <= 15){
            InsertionSort.sort(arr,l,r);
            return;
        }

        int p = partition(arr, l, r);

        sort(arr,l, p - 1);
        sort(arr, p + 1 ,r);
    }


    public  static <E extends Comparable> void sort2Ways(E[] arr, int l, int r){

        if(r-l <= 15){
            InsertionSort.sort(arr,l,r);
            return;
        }

        int p = partition2(arr, l, r);

        sort(arr,l, p - 1);
        sort(arr, p + 1 ,r);
    }
    public  static <E extends Comparable> void sort3Ways(E[] arr, int l, int r, Random rnd){

        if(l >= r) return;

        //生成[l,r]之間的隨機索引
        int p = l+(rnd).nextInt(r-l+1);
        swap(arr,l,p);

        //arr[l + 1, lt] < v , arr[lt + 1, i - 1] == v, arr[gt, r] > v
        int lt = l, i = l + 1, gt = r + 1;
        while(i < gt){

            if(arr[i].compareTo(arr[l]) < 0){
                lt ++;
                swap(arr, i, lt);
                i++;
            }
            if(arr[i].compareTo(arr[l]) > 0){
                gt --;
                swap(arr, i, gt);
            }
            if(arr[i].compareTo(arr[l]) == 0){
                i++;
            }

        }

        swap(arr, l, lt);
        //arr[l + 1, lt-  1] < v , arr[lt, i - 1] == v, arr[gt, r] > v

        sort3Ways(arr,l,lt - 1, rnd);
        sort3Ways(arr,gt,r,rnd);

    }




    //如果partition可以開額外的空間的話

    private static <E extends Comparable> int partition(E[] arr, int l, int r){

//        生成[l,r]之間的隨機索引
        int p = l+(random).nextInt(r-l+1);
        swap(arr,l,p);
        //取中間的
//        swap(arr,l,(l+r)/2);

        E value = arr[l];

        int j = l;

        // arr[l + 1 .... j] < v 包含
        //arr[j + 1 ... i-1 ] >=v
        //跑完下面的迴圈會得到上面的結果
        for(int i = l + 1 ; i <= r ; i++){
            if(arr[i].compareTo(value) < 0){
                j++;
                swap(arr,i,j);
            }

        }
        swap(arr,l,j);
        return j;

    }


    private static <E extends Comparable> int partition2(E[] arr, int l, int r){

        //生成[l,r]之間的隨機索引
        int p = l+(random).nextInt(r-l+1);
        swap(arr,l,p);
        //取中間的
//        swap(arr,l,(l+r)/2);

        int i = l + 1, j = r;
        while(true){

            while(i <= j && arr[i].compareTo(arr[l]) < 0){
                i ++;
            }

            while(j >= i && arr[j].compareTo(arr[l]) > 0){
                j --;
            }

            if(i >= j){
                break;
            }

            swap(arr ,i,j);
            i ++;
            j --;
        }
        swap(arr,l,j);

        return j;
    }

    private static <E extends Comparable> void swap(E[] arr, int i, int j){
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }


}
