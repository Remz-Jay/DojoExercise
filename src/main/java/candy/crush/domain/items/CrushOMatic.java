package candy.crush.domain.items;

import candy.crush.domain.beings.Being;
import candy.crush.domain.beings.Wildlife;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CrushOMatic {
    private List<Candy> readyToPickUp;
    private final static CrushOMatic instance = new CrushOMatic();

    public static CrushOMatic getInstance() {
        return instance;
    }

    public Ball crush(Ball ball) {
        Being ballContents = extractBall(ball);
        if (isAnAnimal(ballContents)) {
            crushObject(ballContents);
        } else {
            throw new CannotCrushException();
        }
        return ball;
    }

    public List<Candy> retrieveCandy() {
        List<Candy> temp = readyToPickUp;
        readyToPickUp = new ArrayList<>();
        return temp;
    }

    public class CannotCrushException extends RuntimeException {

    }
    private CrushOMatic() {
        this.readyToPickUp = new ArrayList<>();
    }
    private Being extractBall(Ball ball) {
        Optional<Being> ballContents = ball.retrieveBeing();
        return ballContents.orElse(null);
    }

    protected void crushObject(Object itemToBeCrushed) {
        if(!(itemToBeCrushed instanceof Being))
            throw new CannotCrushException();
        Candy candy = new Candy(((Being)itemToBeCrushed).getSize());
        readyToPickUp.add(candy);
    }

    private boolean isAnAnimal(Being animal) {
        return animal instanceof Wildlife;
    }
}

