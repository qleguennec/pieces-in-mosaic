package solver;

import structures.Pair;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

abstract class Math {
    static List<Pair<Integer, Integer>> findFactors(int a) {
        return IntStream.range(1, (int) java.lang.Math.ceil(java.lang.Math.sqrt(a) + 1))
                .filter(x -> a % x == 0)
                .mapToObj(x -> new Pair<Integer, Integer>(x, a / x))
                .collect(Collectors.toList());
    }
}
