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

        link.reverseTable();
        link.print();
    }
}
