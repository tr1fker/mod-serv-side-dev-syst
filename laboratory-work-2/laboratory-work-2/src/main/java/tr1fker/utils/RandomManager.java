package tr1fker.utils;

import java.util.Random;

public class RandomManager {
    private static final Random rand = new Random();

    public static int getRandomInt(int range){
        return rand.nextInt(range);
    }
}
