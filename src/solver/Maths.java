package solver;

import structures.Pair;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class Maths {
    public static Set<Pair> findFactors(int a) {
        if (a == 1) {
            return Collections.singleton(new Pair(1, 1));
        }

        return IntStream.range(1, (int) Math.ceil(Math.sqrt(a) + 1))
                .filter(x -> a % x == 0)
                .mapToObj(x -> new Pair(x, a / x))
                .collect(Collectors.toSet());
    }
}
