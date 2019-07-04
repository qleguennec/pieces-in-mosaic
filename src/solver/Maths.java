package solver;

import structures.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class Maths {
   public static Set<Pair> findFactors(int a) {
       return IntStream.range(1, (int) Math.ceil(Math.sqrt(a)))
              .filter(x -> a % x == 0)
              .mapToObj(x -> Arrays.asList(new Pair(x, a / x), new Pair(a / x, x)))
               .flatMap(List::stream)
               .collect(Collectors.toSet());
   }
}
