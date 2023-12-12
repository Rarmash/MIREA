package task1;
import task1.ArrayQueueModule;
public class QueueTest {
    public static void main(String[] args) {
        ArrayQueueModule aqm = ArrayQueueModule.getInstance();
        aqm.enqueue("Text1");
        aqm.enqueue("Text2");
        aqm.enqueue("Text3");
        System.out.println(aqm.dequeue());

        ArrayQueueADT adt = new ArrayQueueADT();

        System.out.println(adt.dequeue(adt));

        ArrayQueue aq = new ArrayQueue();
        System.out.println(aq.dequeue());    }
}
