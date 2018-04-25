package candy.crush.domain.items;

import candy.crush.domain.beings.Being;
import candy.crush.domain.beings.Wildlife;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CrushOMatic {
    private static CrushOMatic instance = new CrushOMatic();
    private CrushOMatic() {
        this.readyToPickUp = new ArrayList<>();
    }
    public static CrushOMatic getInstance() {
        return instance;
    }
    private List<Candy> readyToPickUp;

    public Ball crush(Ball ball) {
        Optional<Being> ballContents = ball.retrieveBeing();
        if (ballContents.isPresent()){
            Being animal = ballContents.get();
            if (animal instanceof Wildlife) {
                Candy candy = new Candy(animal.getSize());
                readyToPickUp.add(candy);
            } else {
                // cannot crush non-Wildlife.
            }
        }
        return ball;
    }

    public List<Candy> retrieveCandy() {
        List<Candy> temp = readyToPickUp;
        readyToPickUp = new ArrayList<Candy>();
        return temp;
    }
}
