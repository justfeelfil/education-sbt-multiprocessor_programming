package counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCounter implements Counter { //многопоточный concurrent счетчик, используя производные java.util.concurrent.Lock
    private Lock lock = new ReentrantLock();
    private volatile long value = 0;

    @Override
    public void increment() {
        lock.lock();
        value++;
        lock.unlock();
    }

    @Override
    public long getValue() {
        lock.lock();
        long result = value;
        lock.unlock();
        return result;
    }
}
