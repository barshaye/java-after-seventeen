package ea.barshai;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

public class VirtualThreadTest_2 {


  public static void main(String[] args) {
    new VirtualThreadTest_2().virtualThreadsInModernWay();
  }

  @Benchmark
  @BenchmarkMode(Mode.SampleTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  @Fork(value = 1)
  @Warmup(iterations = 0)
  @Measurement(iterations = 1, batchSize = 1)
  public void virtualThreadsInModernWay() {
    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      IntStream.range(0, 10_000).forEach(i -> {
        executor.submit(() -> {
          System.out.printf("I try %sth time virtual thread %n", i);
          Thread.sleep(Duration.ofMillis(1));
          return i;
        });
      });
    }
  }
}