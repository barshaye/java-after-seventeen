package ea.barshai;

import java.util.random.RandomGenerator;
import org.junit.jupiter.api.Test;

class SwitchMatchingTest {

  private static final RandomGenerator generator = RandomGenerator.getDefault();

  @Test
  void switchMatching() {
    var mood = randomMood();
    var action = switch (mood) {
      case SAD, HAPPY -> """
            Hilarious party
            with your best friends""";
      case INDIFFERENT -> "Rest";
      case PUZZLED -> "Go for a walk";
      case ANGRY -> "Ready to scream, but holding back";
      case null -> "Read a book";
    };

    System.out.println(action);
  }

  private static Mood randomMood() {
     int idx = generator.nextInt(0, Mood.values().length);
     return Mood.values()[idx];
  }
}