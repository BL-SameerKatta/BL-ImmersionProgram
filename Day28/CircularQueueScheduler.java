/**
 * Implements a Circular Queue using an array, ideal for a Round-Robin CPU Scheduler.
 */
public class CircularQueueScheduler {
    private int[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public CircularQueueScheduler(int capacity) {
        this.capacity = capacity;
        this.queue = new int[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public boolean enqueue(int processId) {
        if (isFull()) return false;
        
        rear = (rear + 1) % capacity;
        queue[rear] = processId;
        size++;
        return true;
    }

    public int dequeue() {
        if (isEmpty()) return -1;
        
        int processId = queue[front];
        front = (front + 1) % capacity;
        size--;
        return processId;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
