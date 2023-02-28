package ea.barshai.inheritance;

public class Oak implements Tree {

  {
    System.out.println("Oak - initialization block");
  }

  static {
    System.out.println("Oak - static initialization block");
  }

  public Oak() {
    System.out.println("Oak constructor");
  }

  @Override
  public void grow() {
    System.out.println("Oak");
  }
}
