public class Task1<T, V, K> {
    private T t;
    private V v;
    private K k;

    public Task1(T t, V v, K k) {
        this.t = t;
        this.v = v;
        this.k = k;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public V getV() {
        return v;
    }

    public void setV(V v) {
        this.v = v;
    }

    public K getK() {
        return k;
    }

    public void setK(K k) {
        this.k = k;
    }

    public void display() {
        System.out.println("T: " + t + ", V: " + v + ", K: " + k);
    }

    public static void main(String[] args) {
        Task1<Integer, String, Double> myObject = new Task1<>(42, "Hello, World!", 3.14);
        myObject.display();
    }
}
