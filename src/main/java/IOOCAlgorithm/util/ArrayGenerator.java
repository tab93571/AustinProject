package IOOCAlgorithm.util;

import java.util.Random;

public class ArrayGenerator {

    private ArrayGenerator(){}

    public static Integer[] generateOrderedArray(int n){
        Integer[] arr = new Integer[n];
        for(int i=0; i<n ; i++){
            arr[i] = i;
        }
        return arr;

    }

    public static Integer[] generateRandomArray(int n,int bound){

        Integer[] arr = new Integer[n];
        Random rnd = new Random();
        for(int i=0;i<n;i++){
            arr[i] =rnd.nextInt(bound);
        }
        return arr;
    }

    //產出一個特殊的array，目的是讓快速排序法取中間值作為標定點會退化

    //目的:要把最小值放到中間的位子

    public static Integer[] generateSpecialArray(int n){
        Integer[] arr = new Integer[n];
        for(int i=0; i<n ; i++){
            arr[i] = i;
        }
        return arr;

    }

    
}
