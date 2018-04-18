package candy.crush;

import candy.crush.domain.beings.Being;
import candy.crush.domain.beings.Wildlife;

import java.util.*;
import java.util.logging.Logger;

public class Simulation {

    public static final int MAX_SIZE = 512;

    private final Set<Being> beings;
    private final Random random;

    private final String[] wildLifeNames = {
            "Gazely",
            "Kangareon",
            "Crocash",
            "Alligee",
            "Stunsel",
            "Hypewhale",
            "Silverorb",
            "Goldoyote",
            "Butterflux",
            "Dragonightmare"};

    public Simulation() {
        beings = new HashSet<>();
        random = new Random();
    }


    public void generateWildLife(int nrOfWildLife) {
        System.out.println("[Simulation] Generating [" + nrOfWildLife + "] wildlife...");
        for (int i = 0; i < nrOfWildLife; i++) {
            addBeing(new Wildlife(wildLifeNames[random.nextInt(wildLifeNames.length)], random.nextInt(MAX_SIZE)));
        }
    }

    public void addBeing(Being being) {
        System.out.println("[Simulation] A [" + being + "] will be added to the simulation");
        beings.add(being);
    }

}
