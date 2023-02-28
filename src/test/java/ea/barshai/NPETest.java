package ea.barshai;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NPETest {

  @Test
  void npe() {
    var p = new Person(null, (byte) 20, Mood.HAPPY);
    Assertions.assertThrows(NullPointerException.class, () -> {
      System.out.println(p.name().isBlank());
    });
  }
}