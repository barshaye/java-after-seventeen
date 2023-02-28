package ea.barshai;

import java.util.random.RandomGenerator;
import org.junit.jupiter.api.Test;

class InstanceOfMatchingTest {

  private static final RandomGenerator generator = RandomGenerator.getDefault();

  @Test
  void instanceofMatching() {
    var o = randomObject();
    if (o instanceof Orange orange) {
      //((Orange) o).pourJuice();
      orange.pourJuice(100);
    }
  }

  private static Object randomObject() {
    int idx = generator.nextInt(0, 6);
    System.out.println(idx);
    return switch (idx) {
      case 0,1 -> "Banana";
      case 2 -> new GoldenApple();
      case 3 -> new Apple();
      case 4 -> new Banana();
      default -> new Orange();
    };
  }
}