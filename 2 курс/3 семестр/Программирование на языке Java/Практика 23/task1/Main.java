package task1;
public class Main {
    public static void main(String[] args) {
        AbstractQueue queue1 = new LinkedQueue();
        AbstractQueue queue2 = new ArrayQueue();
        for (int i = 0; i < 6; i++) {
            queue1.enqueue(i);
        }
        for (int i = 7; i < 11; i++) {
            queue2.enqueue("element " + i);
        }
        queue1.dropNth(1);
        Object r = queue2.dequeue();
        System.out.println(queue1.size());
        System.out.println(r);
    }
}
