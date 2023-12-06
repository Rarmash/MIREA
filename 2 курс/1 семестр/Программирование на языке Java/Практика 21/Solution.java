import java.util.*;

public class Solution<T, K, V> {
    private final T genericField;
    private final K keyField;
    private final V valueField;

    public Solution(T genericField, K keyField, V valueField) {
        this.genericField = genericField;
        this.keyField = keyField;
        this.valueField = valueField;
    }

    @SafeVarargs
    public final List<T> newArrayList(T... elements) {
        List<T> list = new ArrayList<>();
        Collections.addAll(list, elements);
        return list;
    }

    @SafeVarargs
    public final Set<T> newHashSet(T... elements) {
        return new HashSet<>(Arrays.asList(elements));
    }

    public Map<K, V> newHashMap(Object... keyValuePairs) {
        if (keyValuePairs.length % 2 != 0) {
            throw new IllegalArgumentException("The number of arguments must be even.");
        }

        Map<K, V> map = new HashMap<>();
        for (int i = 0; i < keyValuePairs.length; i += 2) {
            K key = (K) keyValuePairs[i];
            V value = (V) keyValuePairs[i + 1];
            map.put(key, value);
        }
        return map;
    }

    public static void main(String[] args) {
        Solution<String, Integer, Double> solution = new Solution<>("Hello", 42, 3.14);

        List<String> stringList = solution.newArrayList("one", "two", "three");
        Set<String> stringSet = solution.newHashSet("one", "two", "three");

        System.out.println(stringList);
        System.out.println(stringSet);
    }
}
