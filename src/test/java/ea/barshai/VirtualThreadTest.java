package ea.barshai;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

class VirtualThreadTest {

  @Test
  void virtualThread() {
    Runnable r = new Runnable() {
      @Override
      public void run() {
        System.out.println("I'm working, working, working... ");
      }
    };
    Thread t = Thread.startVirtualThread(r);
  }

  @Test
  void virtualThreadsInModernWay() {
    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      IntStream.range(0, 10_000).forEach(i -> {
        executor.submit(() -> {
          Thread.sleep(Duration.ofSeconds(1));
          return i;
        });
      });
    }  // executor.close() is called implicitly, and waits
  }

  @Test
  void operatingSystemThreads() {
    try (var executor = Executors.newCachedThreadPool()) {
      IntStream.range(0, 10_000).forEach(i -> {
        executor.submit(() -> {
          Thread.sleep(Duration.ofSeconds(1));
          return i;
        });
      });
    }  // executor.close() is called implicitly, and waits
  }

  @Test
  void intStream() {
    List<Integer> l = IntStream
        .range(0, 200)
        .boxed()
        .toList();
    String s = l.stream().map(i-> Integer.toString(i)).collect(Collectors.joining(System.lineSeparator()));
    System.out.println(s);
  }

  @Test
  void virtualThreadsInUsualWay() throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(100);

    final List<Callable<String>> tasks =
        IntStream
            .range(0, 200)
            .mapToObj(i -> (Callable<String>) () -> "Employee number " + i + " does his job."
            )
            .collect(Collectors.toList());

    List<Future<String>> futures = executorService.invokeAll(tasks);

    futures.forEach(future -> {
      try {
        System.out.println(future.get());
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    });
    executorService.close();
  }

}