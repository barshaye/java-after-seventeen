package ea.barshai;

import java.util.ArrayList;
import java.util.List;

public class Party {

  List<Person> guests = new ArrayList<>();

  public void inviteFriend(Person person) {
    guests.add(person);
  }

  public void givePeopleDrinks(Juice juice, int volume) {
    guests.stream().forEachOrdered(guest -> guest.drink(juice, volume));
  }

}