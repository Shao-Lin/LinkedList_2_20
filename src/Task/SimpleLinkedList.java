package Task;

import java.util.Iterator;

public class SimpleLinkedList<T> implements Iterable<T> {

    public static class SimpleLinkedListException extends Exception {
        public SimpleLinkedListException(String message) {
            super(message);
        }
    }

    private class SimpleLinkedListNode<T> {
        public T value;
        public SimpleLinkedListNode<T> next;

        public SimpleLinkedListNode(T value, SimpleLinkedListNode<T> next) {
            this.value = value;
            this.next = next;
        }

        public SimpleLinkedListNode(T value) {
            this(value, null);
        }
    }

    private SimpleLinkedListNode<T> head = null;
    private SimpleLinkedListNode<T> tail = null;
    private int count = 0;


    public void addFirst(T value) {
        head = new SimpleLinkedListNode<>(value, head);
        if (count == 0) {
            tail = head;
        }
        count++;
    }

    public void addLast(T value) {
        SimpleLinkedListNode<T> temp = new SimpleLinkedListNode<>(value);
        if (count == 0) {
            head = tail = temp;
        } else {
            tail.next = temp;
            tail = temp;
        }
        count++;
    }

    private void checkEmpty() throws SimpleLinkedListException {
        if (count == 0) {
            throw new SimpleLinkedListException("Empty list");
        }
    }

    private SimpleLinkedListNode<T> getNode(int index) {
        int i = 0;
        for (SimpleLinkedListNode<T> curr = head; curr != null; curr = curr.next, i++) {
            if (i == index) {
                return curr;
            }
        }
        return null;
    }

    public T removeFirst() throws SimpleLinkedListException {
        checkEmpty();

        T value = head.value;
        head = head.next;
        if (count == 1) {
            tail = null;
        }
        count--;
        return value;
    }

    public T removeLast() throws SimpleLinkedListException {
        return remove(count - 1);
    }

    public T remove(int index) throws SimpleLinkedListException {
        checkEmpty();
        if (index < 0 || index >= count) {
            throw new SimpleLinkedListException("Incorrect index");
        }

        T value;
        if (index == 0) {
            value = head.value;
            head = head.next;
        } else {
            SimpleLinkedListNode<T> prev = getNode(index - 1);
            SimpleLinkedListNode<T> curr = prev.next;
            value = curr.value;
            prev.next = curr.next;
            if (index == count - 1) {
                tail = prev;
            }
        }
        count--;
        return value;
    }

    public void insert(int index, T value) throws SimpleLinkedListException {
        if (index < 0 || index > count) {
            throw new SimpleLinkedListException("Incorrect index");
        }
        if (index == 0) {
            addFirst(value);
        } else {
            SimpleLinkedListNode<T> prev = getNode(index - 1);
            prev.next = new SimpleLinkedListNode<>(value, prev.next);
            if (index == count) {
                tail = prev.next;
            }
        }
        count++;
    }

    public int size() {
        return count;
    }

    public T getFirst() throws SimpleLinkedListException {
        checkEmpty();

        return head.value;
    }

    public T getLast() throws SimpleLinkedListException {
        checkEmpty();

        return tail.value;
    }

    public T get(int index) throws SimpleLinkedListException {
        if (index < 0 || index >= count) {
            throw new SimpleLinkedListException("Incorrect index");
        }
        return getNode(index).value;
    }
    public SimpleLinkedList <String> toLinkedList(String[] string){
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        for (String s : string){
            list.addLast(s);
        }
        return list;
    }
    public static SimpleLinkedList<Integer> IntegerArrayToLinkedList(int[] array){
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        for (int s : array){
            list.addLast(s);
        }
        return list;
    }
    public static String[] toStringArray(SimpleLinkedList<String> list) throws SimpleLinkedListException{
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i < list.size();i++){
            sb.append(i > 0 ? ", " : "").append(list.get(i));
        }
        return sb.toString().split(", ");
    }
    public static String[] IntegerToStringArray(SimpleLinkedList<Integer> list) throws SimpleLinkedListException{
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i < list.size();i++){
            sb.append(i > 0 ? ", " : "").append(list.get(i));
        }
        return sb.toString().split(", ");
    }

    public static boolean isPrime(int a) {
            for (int i = 2; i <= Math.sqrt(a); i ++) {
                if (a % i == 0) {
                    return false;
                }
        }
        return true ;
    }

    public void removingNonSimpleElements () throws SimpleLinkedList.SimpleLinkedListException {
        if (this.size() == 1){
            for (T value : this) {
                System.out.print(value + " ");
            }
           return;
        }
        for (int i = 0; i < this.size(); i++) {
            if (isPrime((Integer) this.get(i))) {
                if (i == 0) {
                    if (!isPrime((Integer) this.get(i + 1)))
                        this.remove(i + 1);
                } else if (i == this.size() - 1) {
                    if (!isPrime((Integer) this.get(i - 1)))
                        this.remove(i - 1);
                } else {
                    if ((!isPrime((Integer) this.get(i - 1)))){
                        this.remove(i - 1);
                        i--;
                    }
                    if ((!isPrime((Integer) this.get(i + 1))))
                        this.remove(i + 1);
                }
            }
        }
    }


    @Override
    public Iterator<T> iterator() {
        class SimpleLinkedListIterator implements Iterator<T> {
            SimpleLinkedListNode<T> curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T value = curr.value;
                curr = curr.next;
                return value;
            }
        }

        return new SimpleLinkedListIterator();
    }
}
