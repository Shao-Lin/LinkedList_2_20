package Task;

public class Logic {
    public static void main(String[] args) throws Exception {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();

        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.addLast(3);
        list.addLast(100);

        Sort(list);

        for (Object value : list) {
            System.out.print(value + " ");
        }
    }

    public static boolean SearchSimple(int a) {
        int checkResult = 0;
        if ((a == 2) | (a == 3) | (a == 5) | (a == 7))
            return true;
        if (((a % 2 == 0) | (a <= 0) | (a % 10 == 5) | (a == 1)))
            return false;
        else {
            for (int i = 3; i <= Math.sqrt(a); i += 2) {
                if (a % i == 0) {
                    checkResult++;
                    break;
                }
            }
        }
        if (checkResult == 0)
            return true;
        else
            return false;
    }

    public static SimpleLinkedList<Integer> Sort(SimpleLinkedList list) throws SimpleLinkedList.SimpleLinkedListException {
        if (list.size() == 1)
            return list;
        for (int i = 0; i < list.size(); i++) {
            if (SearchSimple((Integer) list.get(i))) {
                if (i == 0) {
                    if (SearchSimple((Integer) list.get(i + 1)) == false)
                        list.remove(i + 1);
                } else if (i == list.size() - 1) {
                    if (SearchSimple((Integer) list.get(i - 1)) == false)
                        list.remove(i - 1);
                } else {
                    if ((SearchSimple((Integer) list.get(i - 1)) == false) & (SearchSimple((Integer) list.get(i + 1))))
                        list.remove(i - 1);
                    else if ((SearchSimple((Integer) list.get(i + 1)) == false) & (SearchSimple((Integer) list.get(i - 1))))
                        list.remove(i + 1);
                    else if ((SearchSimple((Integer) list.get(i + 1)) == false) & (SearchSimple((Integer) list.get(i - 1))) == false) {
                        list.remove(i + 1);
                        list.remove(i - 1);
                        i--;
                    }
                }
            }
        }
        return list;
    }
}