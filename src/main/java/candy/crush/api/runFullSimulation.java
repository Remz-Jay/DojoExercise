package candy.crush.api;

import candy.crush.CandyCrushCli;
import candy.crush.Simulation;
import candy.crush.domain.beings.Being;
import candy.crush.domain.beings.Child;
import candy.crush.domain.items.Ball;
import candy.crush.domain.items.Candy;

import static candy.crush.CandyCrushCli.print;

public class runFullSimulation implements ApiAction {
    private Simulation simulation;

    @Override
    public String getDescription() {
        return "runs a full simulation where a child catches an animal and exchanges it for candy.";
    }

    @Override
    public void doAction(String[] args) {
        print("Welcome to the CrushOMatic 9000 DEMO!", CandyCrushCli.ANSI.RED);
        print("First, let's add some animals to the kingdom.", CandyCrushCli.ANSI.YELLOW);
        this.simulation.generateWildLife(10);
        print("Let's add a Child to catch all those animals..", CandyCrushCli.ANSI.YELLOW);
        Child child = this.simulation.addChild();
        print("Give " + child.toString() +" a Ball..", CandyCrushCli.ANSI.YELLOW);
        Ball ball = new Ball();
        child.giveBall(ball);

        while(animalsRemain()) {
            catchAndExchangeFor(child);
        }

        print("Oh my. We've caught all animals, and now they're extinct. :\\", CandyCrushCli.ANSI.BLACK);
    }

    private void catchAndExchangeFor(Child child) {
        print("Let the kid catch an animal", CandyCrushCli.ANSI.YELLOW);
        Being animal = simulation.getRandomBeing();
        child.catchAnimal(animal);
        print("Exchange the ball for candy", CandyCrushCli.ANSI.YELLOW);
        child.exchangeCatchForCandy(simulation.getCrushOMatic());
        print("Show off how much candy we got!", CandyCrushCli.ANSI.YELLOW);
        print(child.toString() + " collected " + child.getCandies().stream().mapToInt(Candy::getAmountOfSugar).sum() + " sugar worth of candy!", CandyCrushCli.ANSI.BLUE);
    }

    private boolean animalsRemain() {
        return simulation.getBeings().size() > 0;
    }

    @Override
    public void setSimulation(Simulation s) {
        this.simulation = s;
    }
}
