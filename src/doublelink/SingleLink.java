package doublelink;

public class SingleLink<T> {

    public class Node<T> {
        T data ;
        Node next;

        public Node(){};

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

    }

    private Node head = new Node();

    public boolean add(T data) {
        Node node = new Node(data);

        if (head.next == null) {
            head.next = node;
            return true;
        }

        Node tmp = head.next;

        while (tmp.next != null) {
            tmp = tmp.next;
        }

        tmp.next = node;
        return true;
    }

    /**
     * 删除已知节点
     * @param node
     * @return
     */
    public boolean deleteExitNode(Node node) {
        Node next = node.next;
        Node next1 = node.next.next;

        node.data = next.data;
        node.next = next1;
        return true;
    }

    /**
     * 利用pre，cur，ori三个变量，不记得就自己画图，找边界和停止的条件
     */
    public void reverseTable() {
        Node cur = head.next;

        if (cur == null) return;

        Node pre = null;
        while (true) {
            Node ori = cur.next;
            cur.next = pre;
            if (ori == null) {
                head.next = cur;
                return;
            }
            pre = cur;
            cur = ori;
        }
    }

    //递归啊，好难啊
    public Node reverseWithRecusive() {
        return recusive(head);
    }

    private Node recusive(Node current) {
        // 如果当前链表只有一个节点，直接返回即可
        if (current == null || current.next == null) return current;
        // 找到当前节点的下个节点
        Node nextNode = current.next;
        // 将当前节点的next引用置为null，递归后重新处理
        current.next = null;
        Node reverseRest = recusive(nextNode);
        // 反转链表的实质不就是当前节点被它的后记节点指向
        nextNode.next = current;
        return reverseRest;
    }

    public int length(Node head){

        if(head.next==null){
            return 0;
        }

        int i=1;
        Node temp=head.next;
        while((temp=temp.next)!=null){
            i++;
        }

        return i;
    }


    public void print() {
        Node node = head.next;
        while (node != null) {
            System.out.print(node.data);
            node = node.next;
            if (node != null) {
                System.out.print("->");
            } else {
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        SingleLink<Integer> link = new SingleLink<Integer>();
        link.add(1);
        link.add(2);
        link.add(3);
        link.add(4);
        link.print();

        SingleLink.Node newNode = link.reverseWithRecusive();
        System.err.println(newNode.data);
        System.err.println(newNode.next.data);
        System.err.println(newNode.next.next.data);
        System.err.println(newNode.next.next.next.data);

    }
}
