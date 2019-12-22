package jmh;

import counter.ConcurrentCounter;
import counter.Counter;
import counter.LockCounter;
import counter.MutexCounter;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Measurement(iterations = 10)
@Warmup(iterations = 1)
@BenchmarkMode(Mode.Throughput)
public class BenchmarkTests {
    private final int THREAD_COUNT = 4;
    private final int READ_THREAD_COUNT = THREAD_COUNT;
    private final int WRITE_THREAD_COUNT = THREAD_COUNT;

    Counter mutexCounter = new MutexCounter();
    Counter lockCounter = new LockCounter();
    Counter concurrentCounter = new ConcurrentCounter();

    @Benchmark
    @Group("mutexCounter")
    @GroupThreads(WRITE_THREAD_COUNT)
    public void testMutexCounterIncrement() {
        mutexCounter.increment();
    }

    @Benchmark
    @Group("mutexCounter")
    @GroupThreads(READ_THREAD_COUNT)
    public void testMutexCounterGetValue() {
        mutexCounter.getValue();
    }

    @Benchmark
    @Group("lockCounter")
    @GroupThreads(WRITE_THREAD_COUNT)
    public void testLockCounterIncrement() {
        lockCounter.increment();
    }

    @Benchmark
    @Group("lockCounter")
    @GroupThreads(READ_THREAD_COUNT)
    public void testLockCounterGetValue() {
        lockCounter.getValue();
    }

    @Benchmark
    @Group("concurrentCounter")
    @GroupThreads(WRITE_THREAD_COUNT)
    public void testConcurrentCounterIncrement() {
        concurrentCounter.increment();
    }

    @Benchmark
    @Group("concurrentCounter")
    @GroupThreads(READ_THREAD_COUNT)
    public void testConcurrentCounterGetValue() {
        concurrentCounter.getValue();
    }
}
