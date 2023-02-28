package ea.barshai;

public record Juice(Extruded extruded) {
  void makeBeverage(int volume){
      extruded.pourJuice(volume);
  }
}
