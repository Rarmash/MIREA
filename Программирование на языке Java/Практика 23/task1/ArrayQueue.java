package task1;

public class ArrayQueue extends AbstractQueue {
    private int start = 0;
    private Object[] elements = new Object[2];

    protected void enqueueImpl(Object element) {
        ensureCapacity(size + 1);
        elements[(start + size) % elements.length] = element;
    }

    private void ensureCapacity(int capacity) {
        if (capacity > elements.length) {
            sort(capacity * 2);
        }
    }

    private void sort(int capacity) {
        Object[] elementsNew = new Object[capacity];
        int end = (start + size) % elements.length;
        if (start < end) {
            System.arraycopy(elements, start,
                    elementsNew, 0, size);
        } else {
            System.arraycopy(elements, start,
                    elementsNew, 0, elements.length - start);
            System.arraycopy(elements, 0,
                    elementsNew, elements.length - start, end);
        }
        elements = elementsNew;
        start = 0;
    }

    public Object element() {
        return elements[start];
    }

    protected void dequeueImpl() {
        elements[start] = null;
        start = (start + 1) % elements.length;
    }

    protected void clearImpl() {
        elements = new Object[2];
        start = 0;
    }

    protected void pushImpl(Object element) {
        ensureCapacity(size + 1);
        start = (start - 1 + elements.length) % elements.length;
        elements[start] = element;
    }

    public Object peek() {
        int end = (start + size - 1 + elements.length) % elements.length;
        return elements[end];
    }

    protected void removeImpl() {
        int end = (start + size - 1) % elements.length;
        elements[end] = null;
    }

    protected ArrayQueue create() {
        return new ArrayQueue();
    }
}
