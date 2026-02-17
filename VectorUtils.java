import java.util.Vector;

public class VectorUtils {

    public interface Predicate<T> {
        boolean test(T item);
    }

    // Method 1: Generic Swap
    public static <T> void swap(Vector<T> vec, int index1, int index2) {
        if (vec == null) throw new IllegalArgumentException("Vector cannot be null");
        if (index1 < 0 || index1 >= vec.size() || index2 < 0 || index2 >= vec.size()) {
            throw new IndexOutOfBoundsException("Invalid index for swap");
        }
        T temp = vec.get(index1);
        vec.set(index1, vec.get(index2));
        vec.set(index2, temp);
    }

    // Method 2: Generic Find Maximum
    public static <T extends Comparable<T>> T findMax(Vector<T> vec) {
        if (vec == null) throw new IllegalArgumentException("Vector cannot be null");
        if (vec.isEmpty()) return null;

        T max = vec.get(0);
        for (int i = 1; i < vec.size(); i++) {
            T cur = vec.get(i);
            if (cur.compareTo(max) > 0) max = cur;
        }
        return max;
    }

    // Method 3: Generic Count
    public static <T> int countMatches(Vector<T> vec, T target) {
        if (vec == null) throw new IllegalArgumentException("Vector cannot be null");
        int count = 0;
        for (int i = 0; i < vec.size(); i++) {
            T cur = vec.get(i);
            if (target == null) {
                if (cur == null) count++;
            } else {
                if (target.equals(cur)) count++;
            }
        }
        return count;
    }

    // Method 4: Generic Filter
    public static <T> Vector<T> filter(Vector<T> vec, Predicate<T> condition) {
        if (vec == null) throw new IllegalArgumentException("Vector cannot be null");
        if (condition == null) throw new IllegalArgumentException("Predicate cannot be null");

        Vector<T> result = new Vector<>();
        for (int i = 0; i < vec.size(); i++) {
            T item = vec.get(i);
            if (condition.test(item)) result.add(item);
        }
        return result;
    }

    // Part 4.3: Bounded Generics for Numbers
    public static <T extends Number> double sumNumbers(Vector<T> numbers) {
        if (numbers == null) throw new IllegalArgumentException("Vector cannot be null");
        double sum = 0.0;
        for (int i = 0; i < numbers.size(); i++) {
            T n = numbers.get(i);
            if (n != null) sum += n.doubleValue();
        }
        return sum;
    }

    public static <T extends Number> double averageNumbers(Vector<T> numbers) {
        if (numbers == null) throw new IllegalArgumentException("Vector cannot be null");
        if (numbers.isEmpty()) return 0.0;
        return sumNumbers(numbers) / numbers.size();
    }
}
