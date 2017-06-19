package com.fabway.smartquerybuilder;

/**
 * Non tread-safe buffer.
 */
public class TextBuffer {
    private StringBuilder buffer;

    public TextBuffer(TextBuffer copy) {
        buffer = new StringBuilder(copy.buffer.toString());
    }

    public TextBuffer() {
        buffer = new StringBuilder();
    }

    public TextBuffer(int capacity) {
        buffer = new StringBuilder(capacity);
    }

    public void add(String str) {
        buffer.append(str);
    }

    public void add(TextBuffer other) {
        buffer.append(other.getBuffer());
    }

    public void addStart(String str) {
        insert(0, str);
    }

    public void insert(int offset, String str) {
        buffer.insert(offset, str);
    }

    /**
     * Returns the current capacity.
     * 
     * @return the capacity
     */
    public int capacity() {
        return buffer.capacity();
    }

    /**
     * True if the buffer is empty.
     * 
     * @return true if empty
     */
    public boolean isEmpty() {
        return buffer.length() == 0;
    }

    /**
     * Clears the buffer.
     */
    public void clear() {
        buffer.delete(0, buffer.length());
    }

    /**
     * Returns the current buffer length.
     * 
     * @return the length
     */
    public int length() {
        return this.buffer.length();
    }

    private Appendable getBuffer() {
        return buffer;
    }

    @Override
    public String toString() {
        return buffer.toString();
    }
}