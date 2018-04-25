package candy.crush.domain.beings;

import candy.crush.domain.items.Ball;
import candy.crush.domain.items.Candy;
import candy.crush.domain.items.CrushOMatic;

import java.util.ArrayList;
import java.util.List;

public class Child extends Being {
    private Ball ball = null;

    public boolean catchAnimal(Being animal) {
        if (animal == this)
            throw new CannotCatchSelfException();
        return canCatchAnimal() && ball.capture(animal);
    }

    public Ball retrieveBall() {
        Ball b = this.ball;
        this.ball = null;
        return b;
    }

    public boolean giveBall(Ball b) {
        if (ball == null) {
            ball = b;
            return true;
        }
        return false;
    }

    public Child(String name, int size) {
        super(name, size);
    }

    private List<Candy> candies = new ArrayList<>();

    public boolean canCatchAnimal() {
        return hasBall() && ball.isEmpty();
    }

    public boolean hasBall() {
        return ball != null;
    }

    public Ball getBall() {
        return this.ball;
    }

    public List<Candy> getCandies() {
        return candies;
    }

    public boolean exchangeCatchForCandy(CrushOMatic theMachine) {
        this.ball = theMachine.crush(this.retrieveBall());
        if (this.ball.isEmpty()) {
            candies.addAll(theMachine.retrieveCandy());
            return true;
        }
        return false;
    }

    public class CannotCatchSelfException extends RuntimeException {

    }
}
