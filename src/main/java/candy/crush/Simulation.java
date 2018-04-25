package candy.crush;

import candy.crush.domain.beings.Being;
import candy.crush.domain.beings.Child;
import candy.crush.domain.beings.DrMonkey;
import candy.crush.domain.beings.Wildlife;

import java.util.*;

public class Simulation {

    public static final int MAX_SIZE = 512;

    public Set<Being> getBeings() {
        return beings;
    }

    private final Set<Being> beings;
    private final Set<Child> children;
    private final DrMonkey theDoctor;

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
        children = new HashSet<>();
        theDoctor = new DrMonkey("Dr. Monkey", 1);
        random = new Random();
    }


    public void generateWildLife(int nrOfWildLife) {
        System.out.println("[Simulation] Generating [" + nrOfWildLife + "] wildlife...");
        for (int i = 0; i < nrOfWildLife; i++) {
            addBeing(new Wildlife(wildLifeNames[random.nextInt(wildLifeNames.length)], random.nextInt(MAX_SIZE)));
        }
    }

    public void addBeing(Being being) {
        System.out.println("[Simulation] A Being[" + being + "] will be added to the simulation");
        beings.add(being);
    }
    public void addChild(Child child) {
        System.out.println("[Simulation] A Child[" + child + "] will be added to the simulation");
        children.add(child);
    }

}
