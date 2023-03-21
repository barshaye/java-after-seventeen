package ea.barshai;

import java.util.Arrays;
import java.util.stream.Collectors;
import jdk.incubator.vector.IntVector;

public class VectorAPITester {

  int[] v1 = new int[]{18,  258, 1,  0,     18, 21,  22,  523};
  int[] v2 = new int[]{852, 81,  81, 0,     18, 821, 522, 23};
  int[] result = new int[v1.length];

  public static void main(String[] args) {
    new VectorAPITester().usualWay();
    new VectorAPITester().vectorAPIWay();
  }

  public void usualWay() {
    for (int index = 0; index < v1.length; index++) {
      result[index] = v1[index] + v2[index];
    }

    printResult();
  }

  // Remember that a vector has an overall size that is limited.
  // If this limit is 256 bits, this code can only work for arrays of 8 int values.
  public void vectorAPIWay() {
    var species = IntVector.SPECIES_64;

    var V1 = IntVector.fromArray(species, v1, 0);
    var V2 = IntVector.fromArray(species, v2, 0);
    // executed on an SIMD machine,
    // adds all components of these two vectors at once, in the same CPU cycle in parallel
    var RESULT = V1.add(V2);
    RESULT.intoArray(result, 0);

    printResult();
  }

  private void printResult() {
    System.out.println(
        Arrays.stream(result)
            .mapToObj(Integer::toString)
            .collect(Collectors.joining(", ")));
  }
}
