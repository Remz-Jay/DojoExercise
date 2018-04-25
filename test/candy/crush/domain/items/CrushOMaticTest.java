package candy.crush.domain.items;

import candy.crush.domain.beings.Being;
import candy.crush.domain.beings.Child;
import candy.crush.domain.beings.DrMonkey;
import candy.crush.domain.beings.Wildlife;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class CrushOMaticTest {

    private CrushOMatic theMachine;

    private List<Ball> createListOfFilledBallsOfSize(int size) {
        List<Ball> filledBalls = new ArrayList<>();
        IntStream.range(0, size).parallel().forEach(i -> {
            Ball ball = new Ball();
            Wildlife animal = new Wildlife("animal" + i, i+1);
            ball.capture(animal);
            filledBalls.add(ball);
        });
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
    @Test
    @DisplayName("The 'CrushOMatic' cannot crush objects of type 'Child'")
    @Tag("Constraints")
    void cannotCrushChildren() {
        Ball ball = new Ball();
        Being poorKid = new Child("help me!", 1);
        ball.capture(poorKid);
        assertThrows(CrushOMatic.CannotCrushException.class, () -> theMachine.crush(ball));
    }

    @Test
    @DisplayName("The 'CrushOMatic' cannot crush objects of type 'DrMonkey'")
    @Tag("Constraints")
    void cannotCrushDrMonkey() {
        Ball ball = new Ball();
        Being crazyDoctor = new DrMonkey("I brought this on myself", 1);
        ball.capture(crazyDoctor);
        assertThrows(CrushOMatic.CannotCrushException.class, () -> theMachine.crush(ball));
    }

    @Test
    @DisplayName("The 'CrushOMatic' cannot crush itself")
    @Tag("Constraints")
    void cannotCrushItself() {
        assertThrows(CrushOMatic.CannotCrushException.class, () ->theMachine.crushObject(theMachine));
    }
}