package ea.barshai.inheritance;

import java.util.List;

public class InheritanceTest {

  public static void main(String[] args) {
    List<Tree> list = List.of(new Oak(), new GiganticOak());
    list.forEach(Tree::grow);
  }
}
