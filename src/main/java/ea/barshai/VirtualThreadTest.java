package ea.barshai;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
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
    final List<Callable<String>> tasks =  IntStream.range(0, 10).mapToObj(i -> (
        Callable<String>) () -> {
      System.out.printf("I'm in %s%n", i);
      return String.format("I try %sth time thread.", i);
    }).toList();


    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      List<Future<String>> f = executor.invokeAll(tasks);
      System.out.println(">>> after invokeAll");
      awaitTerminationAfterShutdown(executor);
      f.stream().filter(Future::isDone).forEach(r -> {
        try {
          System.out.println(r.get());
        } catch (InterruptedException | ExecutionException e) {
          throw new RuntimeException(e);
        }
      });
    }
  }

  public void awaitTerminationAfterShutdown(ExecutorService threadPool) {
    threadPool.shutdown();
    try {
      if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
        threadPool.shutdownNow();
      }
    } catch (InterruptedException ex) {
      threadPool.shutdownNow();
      Thread.currentThread().interrupt();
    }
  }
}