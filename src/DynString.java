public class DynString {
    private char[] buffer;

    // Default constructor: Create an empty DynString
    public DynString() {
        this(0);
    }

    // Constructor for a specific size, initializes to empty characters
    public DynString(int length) {
        if (length < 0) throw new IllegalArgumentException("Size must be non-negative");
        buffer = new char[length];
    }

    // Constructor that copies values from a character array
    public DynString(char[] source) {
        if (source == null) throw new IllegalArgumentException("Source array cannot be null");
        buffer = java.util.Arrays.copyOf(source, source.length);
    }

    // Constructor that initializes from a String
    public DynString(String source) {
        if (source == null) throw new IllegalArgumentException("Source string cannot be null");
        buffer = source.toCharArray();
    }

    // Constructor to create a DynString filled with a specific character
    public DynString(int length, char fill) {
        if (length < 0) throw new IllegalArgumentException("Size must be non-negative");
        buffer = new char[length];
        java.util.Arrays.fill(buffer, fill);
    }

    // Return the current size of the dynamic string
    public int size() {
        return buffer.length;
    }

    // Check if the DynString is empty
    public boolean empty() {
        return size() == 0;
    }

    // Resize the internal buffer
    public void resize(int newSize) {
        buffer = java.util.Arrays.copyOf(buffer, newSize);
    }

    // Clear the DynString
    public void clear() {
        buffer = new char[0];
    }

    // Get the character at a specific index
    public char get(int index) {
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException("Index out of range: " + index);
        return buffer[index];
    }

    // Set the character at a specific index
    public void set(int index, char value) {
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException("Index out of range: " + index);
        buffer[index] = value;
    }

    // Check equality with char array, String, or another DynString
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof char[]) {
            return java.util.Arrays.equals(buffer, (char[]) obj);
        } else if (obj instanceof String) {
            return toString().equals(obj);
        } else if (obj instanceof DynString) {
            return java.util.Arrays.equals(buffer, ((DynString) obj).buffer);
        }
        return false;
    }

    // Extract a substring
    public DynString substr(int start, int end) {
        if (start < 0 || end > size() || start >= end) throw new IllegalArgumentException("Invalid substring range");
        return new DynString(java.util.Arrays.copyOfRange(buffer, start, end));
    }

    public DynString substr(int start) {
        return substr(start, size());
    }

    // Concatenate with another DynString or String
    public DynString concat(Object other) {
        if (other == null) throw new IllegalArgumentException("Cannot concatenate null");
        String otherString = other.toString();
        char[] newBuffer = new char[size() + otherString.length()];
        System.arraycopy(buffer, 0, newBuffer, 0, size());
        System.arraycopy(otherString.toCharArray(), 0, newBuffer, size(), otherString.length());
        return new DynString(newBuffer);
    }

    // Convert the dynamic string to a standard Java String
    @Override
    public String toString() {
        return new String(buffer);
    }
}

