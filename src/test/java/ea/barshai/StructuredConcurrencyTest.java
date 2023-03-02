package ea.barshai;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import jdk.incubator.concurrent.StructuredTaskScope;
import org.junit.jupiter.api.Test;

// 6
class StructuredConcurrencyTest {

  @Test
  void structuredConcurrency() throws InterruptedException, ExecutionException {
    Callable<String> task = () -> "I’m trying really hard to get this done on time.";
    try (var scope = new StructuredTaskScope<Object>()) {
      var result = scope.fork(task);
      scope.join();
      System.out.println(result.get());
    }
  }

}