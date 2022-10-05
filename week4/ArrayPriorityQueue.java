package week4;

import java.util.ArrayList;


public class ArrayPriorityQueue<E extends Comparable<E>> {

    private E[] data; // data.length is a constant.
    private int front = 0;
    private int size = 0;

    static int CAPACITY = 10;

    private int maxEIdx = -1;
    private E maxE = null;
    private int invalidCount = 0;

    public ArrayPriorityQueue() {
        this(CAPACITY);
    }

    public ArrayPriorityQueue(int capacity) {
        data = (E[]) new Comparable[capacity];
    }

    public int size() {
        return size;
    }

    public int activeSize(){
        return size - invalidCount;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean isActiveEmpty(){
        return (activeSize()==0);
    }

    public ArrayList<E> getActiviedBuffer(){
        ArrayList<E> activedBuffer = new ArrayList<E>();
        for (int i = front; i < front + size; i++) {
            E curE = data[i % data.length];

            if (curE != null) //it is the situation that the process has been done
                activedBuffer.add(curE);
        }
        return activedBuffer;
    }

    public E max() {
        if (isEmpty())
            return null;

        maxEIdx = -1;
        maxE = null;

        for (int i = front; i < front + size; i++) {
            E curE = data[i % data.length];

            if (curE == null) //it is the situation that the process has been done
                continue;

            if (maxE == null) {
                maxE = curE;  // first element
                maxEIdx = i;
            } else {
                if (curE.compareTo(maxE) > 0) {
                    maxE = curE;
                    maxEIdx = i;
                }
            }
        }
        return maxE;
    }

    /**
     * if the current process is done.Just set it to null.
     */
    public void invalidMaxE(){
        data[maxEIdx] = null;
        invalidCount++;
    }

    public void enqueue(E e) throws IllegalStateException {
        if (size == data.length)
            throw new IllegalStateException("Queue is full");
        int avail = (front + size) % data.length; // data.length is a constant.
        data[avail] = e;
        size++;
    }

    public E dequeue() {
        if (isEmpty())
            return null;
        E answer = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        return answer;
    }
}