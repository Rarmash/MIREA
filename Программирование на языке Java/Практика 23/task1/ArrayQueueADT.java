package task1;

/*

Model:
    [a1, a2, ...an]
    n -- размер очереди

Inv:
    n >= 0
    forall i = 1..n: a[i] != null

 */

import java.util.Arrays;

public class ArrayQueueADT {
    private int size = 0;
    private int start = 0;
    private Object[] elements = new Object[2];

    /*
    Pred: element != null && queue != null
    Post: n == n'+1 && a[n] = element && forall i = 1..n': a[i] == a'[i]
     */
    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert element != null;
        ensureCapacity(queue, queue.size + 1);
        int end = (queue.start + queue.size) % queue.elements.length;
        queue.elements[end] = element;
        queue.size++;
    }

    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {
        if (capacity > queue.elements.length) {
            sort(queue, capacity * 2);
        }
    }

    private static void sort(ArrayQueueADT queue, int capacity) {
        Object[] elementsNew = new Object[capacity];
        int end = (queue.start + queue.size) % queue.elements.length;
        if (queue.start < end) {
            System.arraycopy(queue.elements, queue.start,
                    elementsNew, 0, queue.size);
        } else {
            System.arraycopy(queue.elements, queue.start,
                    elementsNew, 0, queue.elements.length - queue.start);
            System.arraycopy(queue.elements, 0,
                    elementsNew, queue.elements.length - queue.start, end);
        }
        queue.elements = elementsNew;
        queue.start = 0;
    }

    /*
    Pred: true && queue != null
    Post: n == n' && forall i = 1..n: a[i] == a'[i] && R = (Array) queue
     */
    public static Object[] toArray(ArrayQueueADT queue) {
        sort(queue, queue.elements.length);
        Object[] elementsNew = new Object[queue.size];
        System.arraycopy(queue.elements, 0, elementsNew, 0, queue.size);
        return elementsNew;
    }

    /*
    Pred: true && queue != null
    Post: n == n' && forall i = 1..n: a[i] == a'[i] && R = (String) queue
     */
    public static String toStr(ArrayQueueADT queue) {
        return Arrays.toString(toArray(queue));
    }

    /*
    Pred: true && queue != null
    Post: n == n' && forall i = 1..n': a[i] == a'[i] && R == a[1]
     */
    public static Object element(ArrayQueueADT queue) {
        assert queue.size > 0;
        return queue.elements[queue.start];
    }

    /*
    Pred: n > 0 && queue != null
    Post: n == n'-1 && forall i = 1..n: a[i] == a'[i + 1] && R == a[1]
     */
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue.size > 0;
        Object result = queue.elements[queue.start];
        queue.elements[queue.start] = null;
        queue.start = (queue.start + 1) % queue.elements.length;
        queue.size--;
        return result;
    }

    /*
    Pred: true && queue != null
    Post: n == n' && forall i = 1..n': a[i] == a'[i] && R == n
     */
    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }

    /*
    Pred: true && queue != null
    Post: n == n' && forall i = 1..n': a[i] == a'[i] && R == (n > 0)
     */
    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }

    /*
    Pred: true && queue != null
    Post: n == 0
     */
    public static void clear(ArrayQueueADT queue) {
        queue.elements = new Object[2];
        queue.start = 0;
        queue.size = 0;
    }

    /*
    Pred: element != null && queue != null
    Post: n == n'+1 && a[1] = element && forall i = 2..n': a[i] == a'[i - 1]
     */
    public static void push(ArrayQueueADT queue, Object element) {
        assert element != null;
        ensureCapacity(queue, queue.size + 1);
        queue.start = (queue.start - 1 + queue.elements.length) % queue.elements.length;
        queue.elements[queue.start] = element;
        queue.size++;
    }

    /*
    Pred: true && queue != null
    Post: n == n' && forall i = 1..n': a[i] == a'[i] && R == a[n]
     */
    public static Object peek(ArrayQueueADT queue) {
        assert queue.size > 0;
        int end = (queue.start + queue.size - 1 + queue.elements.length) % queue.elements.length;
        return queue.elements[end];
    }

    /*
    Pred: n > 0 && queue != null
    Post: n == n'-1 && forall i = 1..n: a[i] == a'[i] && R == a[n]
     */
    public static Object remove(ArrayQueueADT queue) {
        assert queue.size > 0;
        int end = (queue.start + queue.size - 1) % queue.elements.length;
        Object result = queue.elements[end];
        queue.elements[end] = null;
        queue.size--;
        return result;
    }
}
