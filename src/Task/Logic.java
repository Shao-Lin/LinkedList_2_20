package Task;

public class Logic {
    public static void main(String[] args) throws Exception {
SimpleLinkedList<Integer> list = new SimpleLinkedList<>();

list.addLast(4);
list.addLast(2);
list.addLast(9);
list.addLast(127);
list.addLast(886);
list.addLast(886);
list.addLast(3);
list.addLast(886);

        for (int i = 0; i < list.size(); i++) {
            if (SearchSimple(list.get(i))) {
                if (i == 0) {
                    if (SearchSimple(list.get(i + 1)) == false)
                        list.remove(i + 1);
                } else if (i == list.size() - 1) {
                    if (SearchSimple(list.get(i - 1)) == false)
                        list.remove(i - 1);
                } else {
                    if ((SearchSimple(list.get(i - 1)) == false) & (SearchSimple(list.get(i + 1))))
                        list.remove(i - 1);
                    else if ((SearchSimple(list.get(i + 1)) == false) & (SearchSimple(list.get(i - 1))))
                        list.remove(i + 1);
                    else if ((SearchSimple(list.get(i + 1)) == false) & (SearchSimple(list.get(i - 1))) == false) {
                        list.remove(i + 1);
                        list.remove(i - 1);
                        i--;
                    }
                }

            }
        }
        for (int value2 : list) {
            System.out.print(value2 + " ");
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
        else {
            return false;
        }
    }
}