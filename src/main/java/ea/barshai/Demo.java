package ea.barshai;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import jdk.incubator.concurrent.StructuredTaskScope;

public class Demo {

  public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
    Demo demo = new Demo();
    demo.run();
  }

  void run() throws IOException, ExecutionException, InterruptedException {
    try(ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()){
      Callable<String> task1 = () -> fetch("https://jokerconf.com", 5);
      Callable<String> task2 = () -> fetch("https://jokerconf.com/en", 20);

      String first = executor.invokeAny(List.of(task1, task2));
      System.out.println(first);

/*
      executor.invokeAll(List.of(task1, task2))
          .stream()
          .map(f -> {
            try {
              return f.get().length();
            } catch (InterruptedException | ExecutionException e) {
              throw new RuntimeException(e);
            }
          })
          .forEach(System.out::println);
*/
    }
  }

  String fetch(String url, int x) throws IOException, InterruptedException {
    Thread.sleep(1000 * x);
    return String.format("Downloaded %s", x);
  }

  private CompletableFuture<String> fetchAsync(String url, int x) throws InterruptedException {
    CompletableFuture<String> completableFuture = new CompletableFuture<>();

    Executors.newCachedThreadPool()
        .submit(() -> {
          Thread.sleep(1000 * x);
          completableFuture.complete(String.format("Downloaded %s", x));
          return null;
        });

    return completableFuture;
  }

}
