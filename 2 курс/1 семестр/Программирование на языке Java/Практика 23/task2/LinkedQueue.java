package task2;

import java.util.LinkedList;

public class LinkedQueue extends AbstractQueue {

    private LinkedList<Object> q;


    public LinkedQueue(){
        q = new LinkedList<>();
        head = tail = 0;
    }

    @Override
    public Object dequeue() {
        if(isEmpty()) throw new IndexOutOfBoundsException("Queue is empty!");
        return  q.remove(head);
    }

    @Override
    public Object element() {
        if(isEmpty()) throw new IndexOutOfBoundsException("Queue is empty!");
        return  q.get(head);     }

    @Override
    public void enqueue(Object o) {
        q.add(o);
        tail++;
    }

    @Override
    public boolean isEmpty() {
        return tail == head;
    }

    @Override
    public boolean clear() {
        boolean r = ! isEmpty();
        q.clear();
        head = tail = 0;
        return r;
    }
}
