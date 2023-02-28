package ea.barshai;

public record Person(String name, byte age, Mood mood) implements Sensible {
  public void drink(Juice juice, int volume) {
    juice.makeBeverage(volume);
  }

  // record pattern
  void printGreeting(Object o) {
    if (o instanceof Person(var a, var b, var c)) {
      System.out.println("Hello ".concat(a).concat("!"));
    } else {
      System.out.println("Hello stranger");
    }
  }
}
