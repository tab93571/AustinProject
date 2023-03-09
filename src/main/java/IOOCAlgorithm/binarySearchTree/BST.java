package IOOCAlgorithm.binarySearchTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {

    private class Node{
        public E e;
        public Node left, right;
        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(E e){

        if(root == null){
            root = new Node(e);
            size ++;
        }
        else{
            add(root, e);
        }
    }
    public boolean contains(E e){
        return contains(root, e);
    }

    //看以node為根的二分搜索樹中是否包含元素e,遞歸算法
    public boolean contains(Node node,E e){

        if(node == null){
            return false;
        }

        if(node.e.compareTo(e) == 0){
            return true;
        }else if(node.e.compareTo(e) > 0){
            contains(node.right.e);
        }else{
            contains(node.left,e);
        }
        return false;
    }



    //向以node為根的二分搜索樹中插入元素E,遞歸算法
    private void add(Node node, E e){

        if(e.equals(node.e)){
            return;
        }
        else if(node.e.compareTo(e) > 0 && node.left == null){

            node.left = new Node(e);
            size ++;
            return;

        }else if(node.e.compareTo(e) < 0 && node.right == null){

            node.right = new Node(e);
            size ++;
            return;

        }  else if(node.e.compareTo(e) > 0){
            add(node.left,e);
        }else if(node.e.compareTo(e) < 0 ){
            add(node.right,e);
        }
    }

    public void addRefine(E e){
        root = addRefine(root, e);
    }

    //向以node為根的二分搜索樹中插入元素E,遞歸算法 優化
    //返回插入新節點後二分搜索樹的根
    private Node addRefine(Node node, E e){

        if(node == null){
            size ++;
            return new Node(e);
        }
        if(node.e.compareTo(e) > 0){
            node.left = addRefine(node.left,e);
        } if(node.e.compareTo(e) < 0 ){
            node.right = addRefine(node.right,e);
        }
        return node;
    }

    public void addWithoutRecursion(E e){
        if(root == null){
            root = new Node(e);
            size ++;
            return;
        }

        Node p = root;

        while(p != null){

            if(p.e.compareTo(e) < 0){
                if(p.left == null){
                    p.left = new Node(e);
                    size++;
                    return;
                }
                p = p.left;
            }

            else if(p.e.compareTo(e) > 0){
                if(p.right == null){
                    p.right = new Node(e);
                    size++;
                    return;
                }

                p = p.right;
            }
            else{
                return;
            }
        }
    }
    //二分搜索樹前序遍歷
    public void preOrder(){
        preOrder(root);
    }
    //前序遍歷以node為根的二分搜索樹
    private void preOrder(Node node){
        if(node == null){
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);

    }
    //二分搜索樹前序遍歷不用recursion
    public void preOrderWithoutRecursion(){
        Stack <Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);
            if(cur.right != null){
                stack.push(cur.right);
            }
            if(cur.left != null){
                stack.push(cur.left);
            }
        }
    }

    //二分搜索樹前序遍歷
    public void inOrder(){
        inOrder(root);
    }
    //前序遍歷以node為根的二分搜索樹
    private void inOrder(Node node){
        if(node == null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);

    }

    //二分搜索樹前序遍歷
    public void postOrder(){
        postOrder(root);
    }
    //前序遍歷以node為根的二分搜索樹
    private void postOrder(Node node){
        if(node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);

    }

    //廣搜索

    public void levelOrder(){

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node cur = q.remove();
            System.out.println(cur.e);

            if(cur.left != null){
                q.add(cur.left);
            }
            if(cur.right != null){
                q.add(cur.right);
            }
        }

    }

    //尋找二分搜索樹的最小元素
    public E minimum(){
        if(size == 0){
            throw new IllegalArgumentException("BST is empty");
        }
        return minimum(root).e;
    }
    private Node minimum(Node node){
       if(node.left == null){
           return node;
       }else{
           return minimum(node.left);
       }
    }

    //從二分搜索樹刪除最小值所在節點，返回最小值
    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;

    }

    //刪除以node為根的二分搜索樹中的最小節點
    //返回刪除節點後二分搜索樹的根
    private Node removeMin(Node node) {

        if (node.left == null) {

            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }
    //從二分搜索樹中刪除元素為e的節點
    public void remove(E e){
        root = remove(root,e);
    }
    //刪除以node為根的二分搜索樹中值為e的節點，遞歸算法
    //返回刪除節點後新的二分搜索樹的根
    private Node remove(Node node, E e){

        if(node == null){
            return null;
        }

        if(e.compareTo(node.e) < 0){
            node.left = remove(node.left, e);
            return node;
        }
        else if(e.compareTo(node.e) > 0){
            node.right = remove(node.right, e);
            return node;
        }
        else{
            if (node.left == null) {

                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if (node.right == null) {

                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            //待刪除的節點左右子樹均不為空的情況
            //找到比待刪除節點大的最小節點，即待刪除節點右子樹的最小節點
            //用這個節點頂替待刪除節點的位子

            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;

        }

    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root,0,res);
        return res.toString();
    }

    //生成以node為根節點，深度為depth的描述二叉樹的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res){

        if(node == null){
            res.append(generateDepthString(depth)+"null\n");
            return;
        }
        res.append(generateDepthString(depth)+node.e +"\n");
        generateBSTString(node.left,depth+1, res);
        generateBSTString(node.right,depth+1, res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i = 0 ; i < depth ; i ++){
            res.append("--");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        BST<Integer>bst = new BST<>();
        int[] nums = {5,3,6,8,4,2};
        for(int num: nums){
            bst.add(num);
        }
//        bst.preOrder();
//        bst.inOrder();
        bst.postOrder();

//        System.out.println(bst);

    }


}
