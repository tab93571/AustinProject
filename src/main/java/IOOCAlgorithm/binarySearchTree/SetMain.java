package IOOCAlgorithm.binarySearchTree;

import java.util.ArrayList;

public class SetMain {
    public static void main(String[] args) {
        ArrayList<String> words1 = new ArrayList<>();
        FileOperation.readFile("pride-and-prejudice.txt",words1);
        System.out.println("Total words: "+ words1.size());

        BSTSet<String> set1 = new BSTSet<>();
        for(String word : words1){
            set1.add(word);
        }

        System.out.println("Total words without duplicate: "+ set1.getSize());

//
//        LinkedListSet<String> set2 = new LinkedListSet<>();
//        for(String word : words1){
//            set2.add(word);
//        }
//        System.out.println("Total words without duplicate: "+ set2.getSize());

        System.out.println("z".charAt(0) -'a');
    }
}
