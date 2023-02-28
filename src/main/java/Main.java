import ea.barshai.Juice;
import ea.barshai.Mood;
import ea.barshai.Orange;
import ea.barshai.Party;
import ea.barshai.Person;
import java.util.random.RandomGenerator;

public class Main {

  public static void main(String[] args) {
    final RandomGenerator randomGenerator = RandomGenerator.getDefault();
    //Person kate = new Person("Kate", (byte)randomGenerator.nextInt(21, 100), Mood.INDIFFERENT );
    //Person shurka = new Person("Alexandra", (byte)randomGenerator.nextInt(21, 100), Mood.HAPPY );
    //Person witch = new Person("Julia", (byte)randomGenerator.nextInt(21, 100), Mood.ANGRY );

    Party party = new Party();
    party.inviteFriend(new Person("Kate", (byte)randomGenerator.nextInt(21, 100), Mood.INDIFFERENT ));
    party.inviteFriend(new Person("Alexandra", (byte)randomGenerator.nextInt(21, 100), Mood.HAPPY ));
    party.inviteFriend(new Person("Julia", (byte)randomGenerator.nextInt(21, 100), Mood.ANGRY ));

    party.givePeopleDrinks(new Juice(new Orange()), 200);
  }
}