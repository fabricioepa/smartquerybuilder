package com.fabway.smartquerybuilder;

/**
 * Non tread-safe buffer.
 */
public class SmartBuffer {
	StringBuilder buffer;

	public SmartBuffer(SmartBuffer copy) {
		buffer = new StringBuilder(copy.buffer.toString());
	}

	public SmartBuffer() {
		buffer = new StringBuilder();
	}

	public SmartBuffer(int capacity) {
		buffer = new StringBuilder(capacity);
	}

	public SmartBuffer(StringBuilder sb) {
		buffer = sb;
	}

	public void add(String str) {
		buffer.append(str);
	}

	public void add(StringBuilder str) {
		buffer.append(str);
	}

	public void add(SmartBuffer other) {
		buffer.append(other.get());
	}

	public void addStart(String str) {
		insert(0, str);
	}

	public void addEnd(String str) {
		insert(buffer.length(), str);
	}

	public void insert(int offset, String str) {
		buffer.insert(offset, str);
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
	 * Returns the buffer.
	 * 
	 * @return the buffer.
	 */
	public StringBuilder get() {
		return buffer;
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

	@Override
	public String toString() {
		return buffer.toString();
	}
}