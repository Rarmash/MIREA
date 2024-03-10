package task1;
/*
Model:
    [a1, a2, ...an]
    n -- размер очереди

Inv:
    n >= 0
    forall i = 1..n: a[i] != null
*/

public interface Queue {

    /*
    Pred: element != null
    Post: n == n'+1 && a[n] = element && forall i = 1..n': a[i] == a'[i]
     */
    void enqueue(Object element);

    /*
    Pred: true
    Post: n == n' && forall i = 1..n': a[i] == a'[i] && R == a[1]
     */
    Object element();

    /*
    Pred: n > 0
    Post: n == n'-1 && forall i = 1..n: a[i] == a'[i + 1] && R == a[1]
     */
    Object dequeue();

    /*
    Pred: true
    Post: n == n' && forall i = 1..n': a[i] == a'[i] && R == n
     */
    int size();

    /*
    Pred: true
    Post: n == n' && forall i = 1..n': a[i] == a'[i] && R == (n > 0)
     */
    boolean isEmpty();

    /*
    Pred: true
    Post: n == 0
     */
    void clear();

    /*
    Pred: element != null
    Post: n == n'+1 && a[1] = element && forall i = 2..n': a[i] == a'[i - 1]
     */
    void push(Object element);

    /*
    Pred: true
    Post: n == n' && forall i = 1..n': a[i] == a'[i] && R == a[n]
     */
    Object peek();

    /*
    Pred: n > 0
    Post: n == n'-1 && forall i = 1..n: a[i] == a'[i] && R == a[n]
     */
    Object remove();

    /*
    Pred: k > 0
    Post: n == n' && forall i = 1..n': a[i] == a'[i] &&
          R = queue && forall i = 1..n/k: queue[i] == a'[i * k]
     */
    Queue getNth(int k);

    /*
    Pred: k > 0
    Post: n == n' - n/k && forall i = 1..n: a[i] == a'[i + (i - 1)/(k - 1)] &&
          R = queue && forall i = 1..n/k: queue[i] == a'[i * k]
     */
    Queue removeNth(int k);

    /*
    Pred: k > 0
    Post: n == n' - n/k && forall i = 1..n: a[i] == a'[i + (i - 1)/(k - 1)]
     */
    void dropNth(int k);

    /*
    Pred: true
    Post: n == n' && forall i = 1..n: a[i] == a'[i] && R = (Array) queue
     */
    Object[] toArray();

    /*
    Pred: true
    Post: n == n' && forall i = 1..n: a[i] == a'[i] && R = (String) queue
     */
    String toStr();
}