package ea.barshai;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
// 2.
public class UsualThreadCountDownLatchTest {
public static final int SENSORS_COUNT = 10_000;

  public static void main(String[] args) throws InterruptedException {
    UsualThreadCountDownLatchTest vtt = new UsualThreadCountDownLatchTest();
    vtt.operatingSystemThreads();
  }

  public void operatingSystemThreads() throws InterruptedException {
    List<String> results = new CopyOnWriteArrayList<>();
    CountDownLatch latch = new CountDownLatch(SENSORS_COUNT);
    try (var executor = Executors.newCachedThreadPool()) {
      IntStream.range(0, SENSORS_COUNT).forEach(i -> {
        executor.submit(() -> {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
          System.out.printf("I'm in %s%n", i);
          results.add(String.format("I try %sth time thread.", i));
          latch.countDown();
        });
      });
    }
    System.out.println("Все задачи зарядили, можем заняться чем-то полезным, например, съесть что-нибудь");
    latch.await();  // Если тут встать брякалкой, то ничто не мешает выполняться таскам
    System.out.println("Съели всё, что могли съесть, пора вернуться к делам");
    results.forEach(System.out::println);
  }
}