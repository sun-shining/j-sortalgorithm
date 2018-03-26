package doublelink;

import com.sun.tools.javac.util.Assert;

import java.util.ConcurrentModificationException;
import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * 我理解双向链表或者单项链表的任何操作，其实考察的都是受到影响的节点的前、后两个位置如何安置。考虑清楚了，问题就解决了
 * 当然，涉及到head和tail需要另外考虑。
 *
 * @param <T>
 */
public class DoubleLink<T> implements Iterable<T> {

    public class Node {
        Node prev;//当前节点的前一个节点
        T data;//当前节点存放的数据
        Node next;

    }

    private Node head;//最左边的那个节点就是head
    private Node tail;//最右边的那个节点就是tail
    private int modCount;//作为迭代时的标志，迭代时如果发生对原链表中数据就行修改操作时，抛并发修改异常

    /**
     * 向链表尾部添加数据
     *
     * @param data
     * @return
     */
    public void add(T data) {
        Node newNode = new Node();
        newNode.data = data;

        if (head == null) {//如果没有头，肯定也没有尾；第一个节点加入时，头尾则都是新节点
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            //**
            tail.next = newNode;
            tail = newNode;
        }
        modCount++;
    }

    /**
     * 链表中是否包含data
     *
     * @param data
     * @return
     */
    public boolean contains(T data) {
        Node node = find(data);

        return node != null;
    }

    /**
     * 更新旧值变成新值
     *
     * @param oldVal
     * @param newVal
     */
    public void update(T oldVal, T newVal) {
        Node node = find(oldVal);
        node.data = newVal;
        modCount++;
    }

    /**
     * 移除data
     *
     * @param data
     */
    public void remove(T data) {
        assert size() > 0;
        //1找到数据在的节点
        Node node = find(data);
        //2找到了继续
        if (node != null) {
            //3如果节点前后节点都是空的，证明
            if (node.prev == null && node.next == null) {
                head = null;
                tail = null;
            } else if (node.prev == null) {//如果node是头
                //把node的下一个节点的前置置空；把node的下一节点变成头
                node.next.prev = null;
                head = node.next;
            } else if (node.next == null) {
                node.prev.next = null;
                tail = node.prev;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            modCount++;//即使删除时扔要+1，就表示这个值就是一个标志。
        }


    }

    /**
     * 向链表头部添加数据
     *
     * @param data
     */
    public void addFromHead(T data) {
        Node node = new Node();
        node.data = data;

        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.prev = null;
            node.next = head;
            head.prev = node;
            head = node;
        }
        modCount++;
    }

    /**
     * 从链表中获取指定索引节点的值
     *
     * @param index
     * @return
     */
    public T get(int index) {
        assert (index < size()) && (index >= 0);

        T data = null;
        Node temp = head;
        if (temp != null) {
            if (index == 0) {
                data = temp.data;
            } else {
                for (int i = 0; i < index; i++) {
                    data = temp.next.data;
                }
            }
        }
        return data;

    }

    /**
     * 从链表中查找一个元素
     *
     * @param data
     * @return
     */
    public Node find(T data) {
        Node temp = head;
        while (head != null) {
            if (temp.data == data && temp.data.hashCode() == data.hashCode()) {
                return temp;
            } else {
                temp = temp.next;
            }
        }
        return temp;
    }

    /**
     * 链表中节点个数
     *
     * @return
     */
    public int size() {
        Node temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node temp = head;
            int flag = modCount;

            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public T next() {
                T data = temp.data;
                temp = temp.next;
                if (flag != modCount) {
                    throw new ConcurrentModificationException("迭代过程不能修改");
                }
                return data;
            }

            @Override
            public void remove() {

            }
        };
    }

    /*--------------自定义堆栈,增加一个push,和poll尾部获取并移除--------------*/
    public void push(T data) {
        add(data);
    }

    public T poll() {
        T data = null;
        Node temp = tail;
        if (temp != null) {
            data = temp.data;
            if (temp.prev == null) {//前面没有节点，说明这个栈里只有一个节点，弹出一个就空了，需要处理首尾节点
                head = null;
                tail = null;
            } else {//否则等于移除最后一个节点，那需要做的就是处理尾巴前一个节点，并重新定义尾巴
                tail.prev.next = null;
                tail = tail.prev;
            }
        } else {
            throw new EmptyStackException();
        }
        return data;
    }

    @Override
    public String toString() {
        StringBuilder ms = new StringBuilder("[");
        Node temp = head;
        while (temp != null) {
            if (temp.next == null) {
                ms.append(temp.data);
            } else {
                ms.append(temp.data + ",");
            }
            temp = temp.next;
        }
        return ms + "]";
    }
}
