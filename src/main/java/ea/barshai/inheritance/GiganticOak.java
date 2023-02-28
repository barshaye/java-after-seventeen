package ea.barshai.inheritance;

public class GiganticOak extends Oak {
  {
    System.out.println("GiganticOak - initialization block");
  }

  static {
    System.out.println("GiganticOak - static initialization block");
  }

  public GiganticOak() {
    System.out.println("Oak constructor");
  }

  @Override
  public void grow() {
    System.out.println("GiganticOak");
  }
}
