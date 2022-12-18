package maze.util;

import java.util.*;

public class RandomizedSet<E>{
    private int size = 0;
    private final List<E> nums = new ArrayList<>();
    private final Map<E, Integer> indexMap = new HashMap<>();

    private final Random rand = new Random();

    public boolean add(E val) {
        if (indexMap.containsKey(val)) {
            return false;
        } else {
            nums.add(val);
            indexMap.put(val, size++);
            return true;
        }
    }

    public boolean remove(E val) {
        if (!indexMap.containsKey(val)) {
            return false;
        } else {
            int index = indexMap.get(val);
            E lastVal = nums.get(size - 1);
            nums.set(index, lastVal);
            nums.remove(--size);
            indexMap.put(lastVal, index);
            indexMap.remove(val);
            return true;
        }
    }

    public E popRandom() {
        int randomIndex = rand.nextInt(size);
        E val = nums.get(randomIndex);
        remove(val);
        return val;
    }
}
