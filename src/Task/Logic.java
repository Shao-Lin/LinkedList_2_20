package Task;

import static Task.SimpleLinkedList.sort;

public class Logic {
    public static void main(String[] args) throws Exception {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();

        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.addLast(3);
        list.addLast(100);

        sort(list);

        for (Object value : list) {
            System.out.print(value + " ");
        }
    }
}