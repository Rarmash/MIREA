package task1;

import java.util.Arrays;

public abstract class AbstractQueue implements Queue {
    protected int size;

    public void enqueue(Object element) {
        assert element != null;
        enqueueImpl(element);
        size++;
    }

    protected abstract void enqueueImpl(Object element);

    public Object dequeue() {
        assert size > 0;
        Object result = element();
        dequeueImpl();
        size--;
        return result;
    }

    protected abstract void dequeueImpl();

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        clearImpl();
        size = 0;
    }

    protected abstract void clearImpl();

    public void push(Object element) {
        assert element != null;
        pushImpl(element);
        size++;
    }

    protected abstract void pushImpl(Object element);

    public Object remove() {
        assert size > 0;
        Object result = peek();
        removeImpl();
        size--;
        return result;
    }

    protected abstract void removeImpl();

    public Queue removeNth(int k) {
        return oneByOneNth(k, true, true);
    }

    public Queue getNth(int k) {
        return oneByOneNth(k, false, true);
    }

    public void dropNth(int k) {
        oneByOneNth(k, true, false);
    }

    private Queue oneByOneNth(int k, boolean deleteNth, boolean copyNth) {
        Queue result = create();
        int i = 1;
        int sz = size;
        while (i <= sz) {
            if (i % k != 0 || !deleteNth) {
                enqueue(element());
            }
            if (i % k == 0 && copyNth) {
                result.enqueue(element());
            }
            dequeue();
            i++;
        }
        return result;
    }

    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        while (i < size) {
            result[i] = element();
            i++;
            enqueue(element());
            dequeue();
        }
        return result;
    }

    public String toStr() {
        return Arrays.toString(toArray());
    }

    protected abstract Queue create();
}
