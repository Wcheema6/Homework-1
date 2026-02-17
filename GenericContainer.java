import java.util.Vector;

public class GenericContainer<T> {
    private final Vector<T> items;

    public GenericContainer() {
        this.items = new Vector<>();
    }

    public void add(T item) {
        items.add(item);
    }

    public T get(int index) {
        return items.get(index);
    }

    public boolean remove(T item) {
        return items.remove(item);
    }

    public int size() {
        return items.size();
    }

    public Vector<T> getAll() {
        return new Vector<>(items);
    }

    public void clear() {
        items.clear();
    }

    public boolean contains(T item) {
        return items.contains(item);
    }

    public void addAll(Vector<T> other) {
        if (other == null) return;
        items.addAll(other);
    }
}
