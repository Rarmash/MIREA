import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Task5 {
    public static void main(String[] args) {
        final int N = 100000; // Размер коллекций
        int index, target;

        // Заполнение
        List<Integer> arrayList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            arrayList.add(random.nextInt(N));
        }

        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            linkedList.add(random.nextInt(N));
        }

        // Добавление в ArrayList
        long startTime = System.nanoTime();
        arrayList.add(random.nextInt(N));
        long endTime = System.nanoTime();
        long arrayListAdditionTime = endTime - startTime;
        System.out.println("Время добавления в ArrayList: " + arrayListAdditionTime + " наносекунд");

        // Добавление в LinkedList
        startTime = System.nanoTime();
        linkedList.add(random.nextInt(N));
        endTime = System.nanoTime();
        long linkedListAdditionTime = endTime - startTime;
        System.out.println("Время добавления в LinkedList: " + linkedListAdditionTime + " наносекунд");

        // Вставка в ArrayList
        startTime = System.nanoTime();
        index = random.nextInt(N);
        arrayList.add(index, random.nextInt(N));
        endTime = System.nanoTime();
        long arrayListInsertionTime = endTime - startTime;
        System.out.println("Время вставки в ArrayList: " + arrayListInsertionTime + " наносекунд");

        // Вставка в LinkedList
        startTime = System.nanoTime();
        index = random.nextInt(N);
        linkedList.add(index, random.nextInt(N));
        endTime = System.nanoTime();
        long linkedListInsertionTime = endTime - startTime;
        System.out.println("Время вставки в LinkedList: " + linkedListInsertionTime + " наносекунд");

        // Удаление из ArrayList
        startTime = System.nanoTime();
        index = random.nextInt(N);
        arrayList.remove(index);
        endTime = System.nanoTime();
        long arrayListDeletionTime = endTime - startTime;
        System.out.println("Время удаления из ArrayList: " + arrayListDeletionTime + " наносекунд");

        // Удаление из LinkedList
        startTime = System.nanoTime();
        index = random.nextInt(N);
        linkedList.remove(index);
        endTime = System.nanoTime();
        long linkedListDeletionTime = endTime - startTime;
        System.out.println("Время удаления из LinkedList: " + linkedListDeletionTime + " наносекунд");

        // Поиск по образцу в ArrayList
        startTime = System.nanoTime();
        target = random.nextInt(N);
        arrayList.contains(target);
        endTime = System.nanoTime();
        long arrayListSearchTime = endTime - startTime;
        System.out.println("Время поиска по образцу в ArrayList: " + arrayListSearchTime + " наносекунд");

        // Поиск по образцу в LinkedList
        startTime = System.nanoTime();
        target = random.nextInt(N);
        linkedList.contains(target);
        endTime = System.nanoTime();
        long linkedListSearchTime = endTime - startTime;
        System.out.println("Время поиска по образцу в LinkedList: " + linkedListSearchTime + " наносекунд");
    }
}