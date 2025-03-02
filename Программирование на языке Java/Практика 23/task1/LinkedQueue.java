package task1;
public class LinkedQueue extends AbstractQueue {
    private Node start;
    private Node end;

    private class Node {
        private Node next;
        private Node prev;
        private Object value;
        public Node(Node next, Node prev, Object value) {
            this.next = next;
            this.prev = prev;
            this.value = value;
            if (prev != null) {
                prev.next = this;
            }
            if (next != null) {
                next.prev = this;
            }
        }
    }

    protected void enqueueImpl(Object element) {
        end = new Node(null, end, element);
        if (size == 0) {
            start = end;
        }
    }

    public Object element() {
        return start.value;
    }

    protected void dequeueImpl() {
        start = start.next;
        if (start == null) {
            end = null;
        }
    }

    protected void clearImpl() {
        start = null;
        end = null;
    }

    protected void pushImpl(Object element) {
        start = new Node(start, null, element);
        if (size == 0) {
            end = start;
        }
    }

    public Object peek() {
        return end.value;
    }

    protected void removeImpl() {
        end = end.prev;
        if (end == null) {
            start = null;
        }
    }

    protected LinkedQueue create() {
        return new LinkedQueue();
    }
}
