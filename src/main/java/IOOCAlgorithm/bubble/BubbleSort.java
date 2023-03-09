package IOOCAlgorithm.bubble;

public class BubbleSort {

    private BubbleSort(){};

    public static <E extends Comparable<E>> void sort(E[] data){

        for(int i = 0 ; i < data.length - 1 ;){

          int lastSwappedIndex = 0;
            for(int j = 0 ; j < data.length -1 -i; j ++){

                if(data[j].compareTo(data[j + 1]) > 0){
                    swap(data,j , j + 1);
                    lastSwappedIndex = j + 1;
                }
            }
//            if(lastSwappedIndex == 0){
//                break;
//            }
            //i 除了代表輪數以外，也可以代表有幾個位置已經排好序了
            i= data.length - lastSwappedIndex;
        }
    }


    public static <E extends Comparable<E>> void sort2(E[] data){

        for(int i = 0 ; i < data.length - 1 ; i ++){

            boolean isSwapped = false;
            for(int j = 0 ; j < data.length -1 -i; j ++){

                if(data[j].compareTo(data[j + 1]) > 0){
                    swap(data,j , j + 1);
                    isSwapped = true;
                }
            }
            if(isSwapped){
                break;
            }
        }
    }


    public static <E extends Comparable<E>> void sort3(E[] data){

        for(int i = 0 ; i < data.length - 1 ; i ++){

            for(int j = data.length -1 ; j > i ;j --){

                if(data[j].compareTo(data[j - 1]) < 0){
                    swap(data,j, j - 1);
                }

            }

        }
    }


    public static <E extends Comparable<E>> void sort4(E[] data){

        for(int i = 0 ; i < data.length - 1 ; ){

            int lastSwappedIndex = data.length - 1;

            for(int j = data.length -1 ; j > i ;j --){

                if(data[j].compareTo(data[j - 1]) < 0){
                    swap(data,j, j - 1);
                    lastSwappedIndex = j - 1;
                }

            }


            // data[0...lastSwappedIndex]范围里有多少元素
            i = lastSwappedIndex + 1;

        }
    }

    private static<E> void swap(E[] arr,int i, int j){

        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
