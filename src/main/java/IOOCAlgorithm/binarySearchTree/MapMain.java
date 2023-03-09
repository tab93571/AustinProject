package IOOCAlgorithm.binarySearchTree;

import java.util.ArrayList;

public class MapMain {

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        FileOperation.readFile("pride-and-prejudice.txt",words);
        System.out.println("Total words: "+ words.size());

        LinkedListMap<String,Integer> map = new LinkedListMap<>();
        for(String word : words){
            if(map.contains(word)){
                map.set(word,map.get(word) +1);
            }
            else{
                map.add(word,1);
            }
        }

        System.out.println("Frequency of Pride" + map.get("pride"));

        BSTMap<String,Integer> map2 = new BSTMap<>();

        for(String word : words){

            if(map2.contains(word)){
                map2.set(word,map2.get(word) +1);
            }
            else{
                map2.add(word,1);
            }

        }

        System.out.println("Frequency of Pride" + map2.get("pride"));
    }
}
