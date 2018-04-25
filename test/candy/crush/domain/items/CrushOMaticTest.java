package candy.crush.domain.items;

import candy.crush.domain.beings.Being;
import candy.crush.domain.beings.Wildlife;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CrushOMaticTest {

    private CrushOMatic theMachine;

    private List<Ball> createListOfFilledBallsOfSize(int size) {
        List<Ball> filledBalls = new ArrayList<>();
        for (int i = 1; i <= size; i++){
            Ball ball = new Ball();
            Wildlife animal = new Wildlife("animal" + i, i);
            ball.capture(animal);
            filledBalls.add(ball);
        }
        return filledBalls;
    }

    @BeforeEach
    void setup() {
        theMachine = CrushOMatic.getInstance();
    }

    @Test
    void whenAFilledBallIsCrushed_equivalentCandyShouldBeReturned() {

        List<Ball> filledBalls = createListOfFilledBallsOfSize(1);

        Ball ball = theMachine.crush(filledBalls.get(0));
        Optional<Being> result = ball.retrieveBeing();
        assertFalse(result.isPresent());

        List<Candy> prizes = theMachine.retrieveCandy();
        assertEquals(1, prizes.size());
        assertEquals(1, prizes.iterator().next().getAmountOfSugar());
    }

    @Test
    void whenMultipleBallsAreCrushed_theTotalAmountOfCandyShouldMatchTheCombinedInputSizes() {
        List<Ball> filledBalls = createListOfFilledBallsOfSize(2);

        filledBalls.forEach(b -> {
            Ball returnedBall = theMachine.crush(b);
            Optional<Being> result = returnedBall.retrieveBeing();
            assertFalse(result.isPresent());
        });

        List<Candy> prizes = theMachine.retrieveCandy();
        assertEquals(2, prizes.size());
        Iterator<Candy> candyIterator = prizes.iterator();
        assertEquals(3, candyIterator.next().getAmountOfSugar() + candyIterator.next().getAmountOfSugar());
    }
}