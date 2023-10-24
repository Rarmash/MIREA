import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Task5 {
    public static void main(String[] args) {
        final int N = 100000; // Размер коллекций
        final int M = 10000; // Количество операций

        List<Integer> arrayList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            arrayList.add(random.nextInt(N));
        }

        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            linkedList.add(random.nextInt(N));
        }

        long startTime = System.nanoTime();
        for (int i = 0; i < M; i++) {
            int index = random.nextInt(N);
            arrayList.add(index, random.nextInt(N));
        }
        long endTime = System.nanoTime();
        long arrayListInsertionTime = endTime - startTime;
        System.out.println("Время вставки в ArrayList: " + arrayListInsertionTime + " наносекунд");

        startTime = System.nanoTime();
        for (int i = 0; i < M; i++) {
            int index = random.nextInt(N);
            linkedList.add(index, random.nextInt(N));
        }
        endTime = System.nanoTime();
        long linkedListInsertionTime = endTime - startTime;
        System.out.println("Время вставки в LinkedList: " + linkedListInsertionTime + " наносекунд");

        startTime = System.nanoTime();
        for (int i = 0; i < M; i++) {
            int index = random.nextInt(N);
            arrayList.remove(index);
        }
        endTime = System.nanoTime();
        long arrayListDeletionTime = endTime - startTime;
        System.out.println("Время удаления из ArrayList: " + arrayListDeletionTime + " наносекунд");

        startTime = System.nanoTime();
        for (int i = 0; i < M; i++) {
            int index = random.nextInt(N);
            linkedList.remove(index);
        }
        endTime = System.nanoTime();
        long linkedListDeletionTime = endTime - startTime;
        System.out.println("Время удаления из LinkedList: " + linkedListDeletionTime + " наносекунд");

        startTime = System.nanoTime();
        for (int i = 0; i < M; i++) {
            int target = random.nextInt(N);
            arrayList.contains(target);
        }
        endTime = System.nanoTime();
        long arrayListSearchTime = endTime - startTime;
        System.out.println("Время поиска по образцу в ArrayList: " + arrayListSearchTime + " наносекунд");

        startTime = System.nanoTime();
        for (int i = 0; i < M; i++) {
            int target = random.nextInt(N);
            linkedList.contains(target);
        }
        endTime = System.nanoTime();
        long linkedListSearchTime = endTime - startTime;
        System.out.println("Время поиска по образцу в LinkedList: " + linkedListSearchTime + " наносекунд");
    }
}