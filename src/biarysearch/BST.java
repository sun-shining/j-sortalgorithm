package biarysearch;

import java.util.LinkedList;

//二分搜索树
//左孩子小于父亲，右孩子大于父亲，存在深度和广度优先遍历
public class BST<Key extends Comparable<Key>, Value> {

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;

        public Node(Key key, Value value){
            this.key = key;
            this.value = value;
            left = right = null;
        }

        public Node(Node node){
            this.key = node.key;
            this.value = node.value;
            this.left = node.left;
            this.right = node.right;
        }
    }

    private Node root;//根节点
    private int count;//二分查找树中节点个数

    public BST(){
        root = null;
        count = 0;
    }

    public int size(){
        return count;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public void insert(Key key, Value value) {
        root = insert(root, key, value);
    }

    // 向以node为根的二分搜索树中, 插入节点(key, value), 使用递归算法
    // 返回插入新节点后的二分搜索树的根
    private Node insert(Node node, Key key, Value value) {
        if (node == null) {
            count++;
            return new Node(key, value);
        }

        if (key == node.key){
            node.value = value;
        } else if (key.compareTo(node.key) < 0) {//有个小疑问？这儿为什么不count++？ count不是元素个数，是节点个数
            node.left = insert(node.left, key ,value);
        } else {
            node.right = insert(node.right, key, value);
        }
        return node;
    }

    //是否包含某key
    boolean contain(Key key){
        return contain(root, key);
    }

    //某个根节点为node的树是否包含某key
    private boolean contain(Node node, Key key) {
        if (node == null){
            return false;
        }

        if (node.key == key){
            return true;
        } else if(node.key.compareTo(key) < 0) {
            return contain(node.left, key);
        } else {
            return contain(node.right, key);
        }
    }

    Value search(Key key) {
        return search(root, key);
    }

    private Value search(Node root, Key key) {
        if (root == null)
            return null;

        if (root.key == key)
            return root.value;
        else if (root.key.compareTo(key) < 0) {
            return search(root.right, key);
        } else
            return search(root.left, key);

    }

    //获取最小值
    public Node getMinNode(){
        return getMinNode(root);
    }

    //获取最大值
    public Node getMaxNode(){
        return getMaxNode(root);
    }

    //删除最小的节点
    public void removeMinNode(){
        root  = removeMinNode(root);
    }

    //删除最大的节点
    public void removeMaxNode() {
        root = removeMaxNode(root);
    }

    // 删除二分搜索树中键为key的节点
    public void removeKeyNode(Key key){
        root = removeKeyNode(root, key);
    }

    //#****移除指定key的节点
    private Node removeKeyNode(Node node, Key key) {
        //1，node为null，直接返回
        if (node == null )
            return null;


        if (node.key.compareTo(key) > 0) {//2 如果node.key大于key的值，则去左子树继续寻找
            //node.key > k 去左子树寻找
            return removeKeyNode(node.left, key);
        } else if (node.key.compareTo(key) < 0){//3 如果node.key小于key的值，则去右子树继续寻找
            //node.key < k 去右子树寻找
            return removeKeyNode(node.right, key);
        } else { //4如果node.key等于key的值，则分下列情况讨论


            if (node.left == null){ //1 node.left == null,就需要去右边找key并删除
                Node rightNode = node.right;
                node.right = null;
                count --;
                return rightNode;
            } else  if (node.right == null){//2 node.right == null,就需要去左边找key并删除
                Node leftNode = node.left;
                node.left = null;
                count --;
                return leftNode;
            } else {//2 左右子树均不为空，则用bibbard deletion算法来删除
            /*
                这儿是我写的，唯一的区别就是没有对k进行封装，暂未验证有没有问题 TODO
                //4，node.key==key,则需要使用Hibbard Deletion算法来删除
                //4.1 找到当前节点node的右孩子的最小节点k(根据二分搜索树的性质，node的左孩子的最大节点k也可以)，复制一份k并将k从原树中删除
                Node k = getMinNode(node.right);
                node = removeMinNode(node.right);

                //4.2 将复制的节点k的右孩子指向n的右孩子，左孩子指向n的左孩子
                k.right = node.right;
                k.left = node.left;

                //4.3 将node删除，将k返回
                node.left = null;
                node.right = null;
                return k;*/

                Node successor = new Node(getMinNode(node.right));
                count ++;

                successor.right = removeMinNode(node.right);//右子树是指向删除了自己右子树最小值的节点
                successor.left = node.left;

                node.left = node.right = null;
                count --;
                return successor;
            }

        }

    }

    private Node getMinNode(Node node) {
        if (node.left == null) {
            return node;
        }

        return getMinNode(node.left);
    }

    private Node getMaxNode(Node node) {
        if (node.right == null)
            return node;

        return getMaxNode(node.right);
    }

    //删除左孩子，假如左孩子！=null，继续递归；
    // 假如左孩子等于null，那就将右边的子树返回即可
    private Node removeMinNode(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            count --;
            return rightNode;
        }

        node.left = removeMinNode(node.left);
        return node;
    }

    private Node removeMaxNode(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            count --;
            return leftNode;
        }

        node.right = removeMaxNode(node.right);
        return node;
    }

    //前、中、后序遍历每一次都是遍历到底，所以也称为深度优先遍历
    //而与之对应的每次都先把没一层的元素遍历出来的操作就是广度优先遍历
    //对二叉搜索树进行前序遍历，顺便打印出结果
    public void preOrder(){
        preOrder(root);
    }
    //对二叉搜索树进行中序遍历，顺便打印出结果
    //因为二叉查找树有左子树元素大于右子树元素的性质，所以，中序遍历可以用左二分查找树的排序
    public void inOrder(){
        inOrder(root);
    }
    //对二叉搜索树进行后序遍历，顺便打印出结果
    public void afterOrder(){
        afterOrder(root);
    }

    //层优先遍历，也叫做广度优先遍历
    public void levelOrder(){

        LinkedList<Node> linkedList = new LinkedList<Node>();

        linkedList.add(root);
        //循环操作，每次操作的都是被linkedList移除的操作，不是root哦
        while (!linkedList.isEmpty()) {
            Node node = linkedList.remove();
            System.err.println(node.key);

            if (node.left != null) {
                linkedList.add(node.left);
            }

            if (node.right != null) {
                linkedList.add(node.right);
            }
        }
    }

    private void afterOrder(Node node) {
        if (node != null) {
            afterOrder(node.left);
            afterOrder(node.right);
            System.err.print(node.value);
            System.err.println(" ");

        }
    }


    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.err.print(node.value);
            System.err.println(" ");
            inOrder(node.right);
        }
    }

    private void preOrder(Node node) {
        if (root != null) {
            System.err.print(node.value);
            System.err.println(" ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // 测试二分搜索树
    public static void main(String[] args) {

        int N = 7;

        // 创建一个数组，包含[0...N)的所有元素
        Integer[] arr = new Integer[N];
        for(int i = 0 ; i < N ; i ++)
            arr[i] = new Integer(i);

        // 打乱数组顺序
        for(int i = 0 ; i < N ; i ++){
            int pos = (int) (Math.random() * (i+1));
            Integer t = arr[pos];
            arr[pos] = arr[i];
            arr[i] = arr[pos];
        }
        // 由于我们实现的二分搜索树不是平衡二叉树，
        // 所以如果按照顺序插入一组数据，我们的二分搜索树会退化成为一个链表
        // 平衡二叉树的实现，我们在这个课程中没有涉及，
        // 有兴趣的同学可以查看资料自学诸如红黑树的实现
        // 以后有机会，我会在别的课程里向大家介绍平衡二叉树的实现的：）


        // 我们测试用的的二分搜索树的键类型为Integer，值类型为String
        // 键值的对应关系为每个整型对应代表这个整型的字符串
        BST<Integer,String> bst = new BST<Integer,String>();
        for(int i = 0 ; i < N ; i ++)
            bst.insert(new Integer(arr[i]), Integer.toString(arr[i]));

        // 对[0...2*N)的所有整型测试在二分搜索树中查找
        // 若i在[0...N)之间，则能查找到整型所对应的字符串
        // 若i在[N...2*N)之间，则结果为null
        /*for(int i = 0 ; i < 2*N ; i ++){
            String res = bst.search(new Integer(i));
            if( i < N )
                assert res == Integer.toString(i);
            else
                assert res == null;
        }*/

        //测试二分搜索树的中序遍历
//        bst.inOrder();
        bst.levelOrder();
    }
}
