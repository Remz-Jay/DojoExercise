package candy.crush;

import candy.crush.domain.beings.Being;
import candy.crush.domain.beings.Child;
import candy.crush.domain.beings.Wildlife;
import candy.crush.domain.items.CrushOMatic;

import java.util.*;

public class Simulation {

    private static final int MAX_SIZE = 512;
    private final ArrayList<Being> beings;
    private final CrushOMatic theMachine;
    private final Set<Child> children;
    private final Random random;
    private static final String[] wildLifeNames = {
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
    private static final String[] childNames = {
            "Kwik",
            "Kwek",
            "Kwak",
            "Lizzy",
            "Juultje",
            "Babetje",
    };



    public Simulation() {
        beings = new ArrayList<>();
        children = new HashSet<>();
        random = new Random();
        theMachine = CrushOMatic.getInstance();
    }

    public ArrayList<Being> getBeings() {
        return beings;
    }

    public Being getRandomBeing() {
        if (beings.isEmpty()) {
            return null;
        }

        Being being = beings.get(random.nextInt(beings.size()));
        beings.remove(being);
        return being;
    }

    public Set<Child> getChildren() {
        return children;
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

    public Child addChild() {
        Child c = new Child(childNames[random.nextInt(childNames.length)],1);
        System.out.println("[Simulation] A Child[" + c + "] will be added to the simulation");
        children.add(c);
        return c;
    }

    public CrushOMatic getCrushOMatic() {
        return this.theMachine;
    }
}
