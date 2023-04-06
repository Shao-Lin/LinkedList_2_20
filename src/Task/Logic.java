package Task;


public class Logic {
    public static void main(String[] args) throws Exception {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();

        list.addLast(3);
        list.addLast(4);
        list.addLast(4);
        list.addLast(6);
        list.addLast(11);
        list.addLast(436);
        list.addLast(2);


        list.removingNonSimpleElements();
        for (int value : list) {
            System.out.print(value + " ");
        }
    }
}