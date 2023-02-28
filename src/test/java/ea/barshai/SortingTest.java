package ea.barshai;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

class SortingTest {
  private static class Branch{
    String name;
  }
  private static class Account{
    Branch branch;

  }
  @Test
  void sorting() {
    Branch b1 = new Branch();
    b1.name = "Первый филиал";
    Account a1 = new Account();
    a1.branch = b1;

    Branch b2 = new Branch();
    b2.name = "Второй филиал";
    Account a2 = new Account();
    a2.branch = b2;


    Account a3 = new Account();

    List<Account> list = List.of(a1, a2, a3);

    List<Account> sortedList = list.stream()
        .sorted((o1, o2) -> {
          boolean firstIsEmpty = (o1.branch == null || StringUtils.isBlank(o1.branch.name));
          boolean secondIsEmpty = (o2.branch == null || StringUtils.isBlank(o2.branch.name));
          if (firstIsEmpty && secondIsEmpty) {
            return 0;
          } else if (secondIsEmpty) {
            return -1;
          } else if (firstIsEmpty) {
            return 1;
          } else {
            return o1.branch.name.compareTo(o2.branch.name);
          }})
        .toList();
    for (Account a: sortedList) {
      System.out.println(null == a.branch ? "null" : a.branch.name);
    }
  }
}