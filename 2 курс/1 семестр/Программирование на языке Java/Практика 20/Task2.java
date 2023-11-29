public class Task2<T, V, K> {
    private final T t;
    private final V v;
    private final K k;

    public Task2(T t, V v, K k) {
        this.t = t;
        this.v = v;
        this.k = k;
    }

    public T getT() {
        return t;
    }

    public V getV() {
        return v;
    }

    public K getK() {
        return k;
    }

    public void printClassNames() {
        System.out.println("Type of T: " + t.getClass().getName());
        System.out.println("Type of V: " + v.getClass().getName());
        System.out.println("Type of K: " + k.getClass().getName());
    }

    public static void main(String[] args) {
        Task2<Integer, String, Double> myObject = new Task2<>(42, "Hello, World!", 3.14);
        myObject.printClassNames();
    }
}
