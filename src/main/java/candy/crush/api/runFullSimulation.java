package candy.crush.api;

import candy.crush.CandyCrushCli;
import candy.crush.domain.beings.Being;
import candy.crush.domain.beings.Child;
import candy.crush.domain.beings.Wildlife;
import candy.crush.domain.items.Ball;

import java.util.Set;

import static candy.crush.CandyCrushCli.print;

public class runFullSimulation extends apiAction {
    @Override
    public void register() {

    }

    @Override
    public String getDescription() {
        return "runs a full simulation where a child catches an animal and exchanges it for candy.";
    }

    @Override
    public boolean doAction(String[] args) {
        print("Welcome to the CrushOMatic 9000 DEMO!", CandyCrushCli.ANSI.RED);
        print("First, let's add some animals to the kingdom.", CandyCrushCli.ANSI.YELLOW);
        this.simulation.generateWildLife(10);
        print("Let's add a Child to catch all those animals..");
        this.simulation.addChild();
        print("Give the kid a Ball..");
        Ball ball = new Ball();
        Child child = simulation.getChildren().iterator().next();
        child.giveBall(ball);
        print("Let the kid catch an animal");
        Being animal = simulation.getRandomBeing();
        child.catchAnimal(animal);
        return true;
    }
}
