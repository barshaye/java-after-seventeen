package ea.barshai;

public final class Orange extends Fruit implements Extruded {

  public void pourJuice(int volume) {
    final var s = "Here is your glass of orange juice in %s mils".formatted(volume);
    System.out.println(s);
  }

}
