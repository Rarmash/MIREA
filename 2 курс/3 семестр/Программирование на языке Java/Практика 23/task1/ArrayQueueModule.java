package task1;

import java.util.Arrays;
public class ArrayQueueModule {
    private static int size = 0;
    private static int start = 0;
    private static Object[] elements = new Object[2];

    // Добавленный метод
    public static ArrayQueueModule getInstance()
    {
        return new ArrayQueueModule();
    }

    /*
    Pred: element != null
    Post: n == n'+1 && a[n] = element && forall i = 1..n': a[i] == a'[i]
     */
    public static void enqueue(Object element) {
        assert element != null;
        ensureCapacity(size + 1);
        elements[(start + size) % elements.length] = element;
        size++;
    }

    private static void ensureCapacity(int capacity) {
        if (capacity > elements.length) {
            sort(capacity * 2);
        }
    }

    private static void sort(int capacity) {
        Object[] elementsNew = new Object[capacity];
        if (start + size <= elements.length) {
            System.arraycopy(elements, start,
                    elementsNew, 0, size);
        } else {
            System.arraycopy(elements, start,
                    elementsNew, 0, elements.length - start);
            System.arraycopy(elements, 0,
                    elementsNew, elements.length - start, (start + size) % elements.length);
        }
        elements = elementsNew;
        start = 0;
    }

    /*
    Pred: true
    Post: n == n' && forall i = 1..n: a[i] == a'[i] && R = (Array) queue
     */
    public static Object[] toArray() {
        sort(elements.length);
        Object[] elementsNew = new Object[size];
        System.arraycopy(elements, 0, elementsNew, 0, size);
        return elementsNew;
    }

    /*
    Pred: true
    Post: n == n' && forall i = 1..n: a[i] == a'[i] && R = (String) queue
     */
    public static String toStr() {
        return Arrays.toString(toArray());
    }

    /*
    Pred: true
    Post: n == n' && forall i = 1..n': a[i] == a'[i] && R == a[1]
     */
    public static Object element() {
        assert size > 0;
        return elements[start];
    }

    /*
    Pred: n > 0
    Post: n == n'-1 && forall i = 1..n: a[i] == a'[i + 1] && R == a[1]
     */
    public static Object dequeue() {
        assert size > 0;
        Object result = elements[start];
        elements[start] = null;
        start = (start + 1) % elements.length;
        size--;
        return result;
    }

    /*
    Pred: true
    Post: n == n' && forall i = 1..n': a[i] == a'[i] && R == n
     */
    public static int size() {
        return size;
    }

    /*
    Pred: true
    Post: n == n' && forall i = 1..n': a[i] == a'[i] && R == (n > 0)
     */
    public static boolean isEmpty() {
        return size == 0;
    }

    /*
    Pred: true
    Post: n == 0
     */
    public static void clear() {
        elements = new Object[2];
        start = 0;
        size = 0;
    }

    /*
    Pred: element != null
    Post: n == n'+1 && a[1] = element && forall i = 2..n': a[i] == a'[i - 1]
     */
    public static void push(Object element) {
        assert element != null;
        ensureCapacity(size + 1);
        start = (start - 1 + elements.length) % elements.length;
        elements[start] = element;
        size++;
    }

    /*
    Pred: true
    Post: n == n' && forall i = 1..n': a[i] == a'[i] && R == a[n]
     */
    public static Object peek() {
        assert size > 0;
        return elements[(start + size - 1 + elements.length) % elements.length];
    }

    /*
    Pred: n > 0
    Post: n == n'-1 && forall i = 1..n: a[i] == a'[i] && R == a[n]
     */
    public static Object remove() {
        assert size > 0;
        int end = (start + size - 1) % elements.length;
        Object result = elements[end];
        elements[end] = null;
        size--;
        return result;
    }
}
