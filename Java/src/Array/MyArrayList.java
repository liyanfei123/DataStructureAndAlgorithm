package Array;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Description:
 *
 * @date:2022/03/12 17:09
 * @author: lyf
 */
public class MyArrayList<T> implements Iterable<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private int size;  // 数组大小
    private T[] items; // 数组

    public int getSize() {
        return size;
    }

    public MyArrayList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    private void doClear() {
        size = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public T get(int idx) {
        if (idx < 0 || idx >= getSize()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return items[idx];
    }

    public T set(int idx, T newVal) {
        if (idx < 0 || idx >= getSize()) {
            throw  new ArrayIndexOutOfBoundsException();
        }
        T old = items[idx];
        items[idx] = newVal;
        return old;
    }

    public void ensureCapacity(int newCapacity) {
        if (newCapacity < size) {
            return;
        }
        T[] old = items;
        items = (T[]) new Object[newCapacity];
        for (int i = 0; i < getSize(); i++) {
            items[i] = old[i];
        }
    }

    /**
     * 列表末尾添加元素
     * @param x
     * @return
     */
    public boolean add(T x) {
        add(getSize(), x);
        return true;
    }

    /**
     * 向列表中指定位置添加元素
     * @param idx
     * @param x
     */
    public void add(int idx, T x) {
        if (items.length == getSize()) {
            ensureCapacity(getSize()*2+1);
        }
        for (int i = size; i > idx; i--) {
            items[i] = items[i-1];  // idx后面的元素朝后挪
        }
        items[idx] = x;
    }

    public T remove(int idx) {
        T removeItem = items[idx];
        for (int i = idx; i < getSize()-1; i++) {
            items[i] = items[i+1]; // idx后面的元素朝前挪
        }
        size--;
        return removeItem;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {

        private int current = 0;  // 表示要被查看的下一个元素

        @Override
        public boolean hasNext() {
            return current < MyArrayList.this.getSize();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return items[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }


}
