package candy.crush.domain.items;

import candy.crush.domain.beings.Being;
import candy.crush.domain.beings.Wildlife;

import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BallTest {
    Ball ball;
    @BeforeEach
    public void setup() {
        ball = new Ball();
    }

    @Test
    public void newBallIsEmpty() {
        Optional<Being> result = ball.retrieveBeing();
        assertFalse(result.isPresent());
    }

    @Test
    public void filledBallContainsBeing() {
        Being being = new Being("", 0);
        ball.setContainedBeing(being);

        Optional<Being> result = ball.retrieveBeing();
        assertTrue(result.isPresent());
        assertEquals(being, result.get());
    }

    @Test
    public void filledBallContainsWildlife() {
        Wildlife wildlife = new Wildlife("", 0);

        assertTrue(ball.capture(wildlife));
        assertEquals(Optional.of(wildlife), ball.retrieveBeing());
    }
    @Test
    public void cannotCaptureMoreThanOneBeing() {
        Wildlife wildlife = new Wildlife("first", 0);
        assertTrue(ball.capture(wildlife));
        assertEquals(wildlife.toString(), ball.peek());

        Wildlife secondBeing = new Wildlife("second", 0);
        assertFalse(ball.capture(secondBeing));
        assertEquals(Optional.of(wildlife), ball.retrieveBeing());
    }

    @Test
    public void cannotCatchNothing() {
        assertFalse(ball.capture(null));
        Optional<Being> result = ball.retrieveBeing();
        assertFalse(result.isPresent());
    }
}