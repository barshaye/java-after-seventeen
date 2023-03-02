package ea.barshai;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

// 4 - how to create Virtual Thread
public class VirtualThreadCreationTest {

  public static void main(String[] args) throws InterruptedException {
    Runnable runnable = () -> System.out.println("Inside Runnable");

    // It is worth noting that Thread.ofVirtual().start(runnable) is equivalent to Thread.startVirtualThread(runnable).
    Thread virtualThread = Thread.ofVirtual().start(runnable);

    // or we can use the Thread.Builder reference to create and start multiple threads.
    Thread.Builder builder1 = Thread.ofVirtual().name("JVM-Thread");
    Thread t1 = builder1.start(runnable);
    Thread t2 = builder1.start(runnable);

    //A similar API Thread.ofPlatform() exists for creating platform threads as well.
    Thread.Builder builder2 = Thread.ofPlatform().name("Platform-Thread");
    Thread t3 = builder2.start(runnable);
    Thread t4 = builder2.start(runnable);

    // Using Executors.newVirtualThreadPerTaskExecutor()
    //This method creates one new virtual thread per task. The number of threads created by the Executor is unbounded.
    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      IntStream.range(0, 10_000).forEach(i -> {
        executor.submit(() -> {
          Thread.sleep(Duration.ofSeconds(1));
          return i;
        });
      });
    }
  }
}