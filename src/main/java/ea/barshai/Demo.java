package ea.barshai;


import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import jdk.incubator.concurrent.StructuredTaskScope;

// 5
public class Demo {

  public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
    Demo demo = new Demo();
    demo.run();
  }

  void run() throws IOException, ExecutionException, InterruptedException {
    Callable<String> task1 = () -> fetch("https://jokerconf.com", 5);
    Callable<String> task2 = () -> fetch("https://jokerconf.com/en", 20);
    Callable<String> task3 = () -> fetch("https://jokerconf.com/il", 0);
/*
     try (ExecutorService executor = Executors.newCachedThreadPool()) {

      //String first = executor.invokeAny(List.of(task1, task2, task3));
      //System.out.println(first);
      //System.exit(1);

      // Если запустим ряд задач параллельно и одна из них свалится с ошибкой, мы всё равно должны дождаться выполнения оставшихся чтобы
      // продолжить работу основного потока
      executor.invokeAll(List.of(task1, task2, task3))
          .stream()
          .map(f -> {
            try {
              return f.get().length();
            } catch (InterruptedException | ExecutionException e) {
              throw new RuntimeException(e);
            }
          })
          .forEach(System.out::println);
    }
*/
    try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
      Future<String> f1 = scope.fork(task1);
      Future<String> f2 = scope.fork(task2);
      Future<String> f3 = scope.fork(task3);
      scope.join();  // Join all subtasks
      scope.throwIfFailed(e -> new RuntimeException(e));

      //The subtasks have completed by now so process the result
      System.out.println(f1.resultNow());
      System.out.println(f2.resultNow());
      System.out.println(f3.resultNow());
    }
  }

  String fetch(String url, int x) throws IOException, InterruptedException {
    if (x == 0) {
      throw new RuntimeException("Everybody gone");
    }
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
