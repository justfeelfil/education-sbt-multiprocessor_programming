package counter;

import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentCounter implements Counter { //многопоточный concurrent счетчик, не используя synchronized и Locks
    private AtomicLong value = new AtomicLong(0);

    @Override
    public void increment() {
        value.incrementAndGet();
    }

    @Override
    public long getValue() {
        return value.get();
    }
}
