public class Entry {
    private int key;
    private int value;

    // Entry constructor
    public Entry(int key, int value) {
        this.key = key;
        this.value = value;
    }

    // getters
    public int getKey() {
        return this.key;
    }
    public int getValue() {
        return this.value;
    }

    // to string
    @Override
    public String toString() {
        return "[" + this.key + ", " + this.value + "]";
    }
}
