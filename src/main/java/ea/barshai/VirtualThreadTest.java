package ea.barshai;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

// 3
public class VirtualThreadTest {

  public static void main(String[] args) throws InterruptedException {
    VirtualThreadTest vtt = new VirtualThreadTest();
    vtt.operatingSystemThreads();
  }

  @Benchmark
  @BenchmarkMode(Mode.SampleTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  @Fork(value = 1)
  @Warmup(iterations = 0)
  @Measurement(iterations = 1, batchSize = 1)
  public void operatingSystemThreads() throws InterruptedException {
    final AtomicInteger atomicInteger = new AtomicInteger();

    Runnable runnable = () -> {
      try {
        Thread.sleep(Duration.ofSeconds(1));
      } catch(Exception e) {
        System.out.println(e);
      }
      System.out.println("Work Done - " + atomicInteger.incrementAndGet());
    };

    Instant start = Instant.now();

    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      for(int i = 0; i < 10_000; i++) {
        executor.submit(runnable);
      }
    }

    Instant finish = Instant.now();
    long timeElapsed = Duration.between(start, finish).toMillis();
    System.out.println("Total elapsed time : " + timeElapsed);
  }
}