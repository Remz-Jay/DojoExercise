package candy.crush.domain.beings;

import candy.crush.domain.items.Ball;
import candy.crush.domain.items.Candy;
import candy.crush.domain.items.CrushOMatic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ChildTest {
    private Child theChild;
    private CrushOMatic theMachine;

    private void giveThatKidABall() {
        Ball b = new Ball();
        theChild.giveBall(b);
    }

    @BeforeEach
    public void setup() {
        theChild = new Child("Test Subject", 1);
        theMachine = CrushOMatic.getInstance();
    }

    @Test
    public void aNewChild_hasNoBall() {
        assertFalse(theChild.hasBall());
    }

    @Test
    public void aNewChild_cannotCatchAnimals() {
        Wildlife a = new Wildlife("Catch me if you can", 1);
        assertFalse(theChild.canCatchAnimal());
        assertFalse(theChild.catchAnimal(a));
    }

    @Test
    public void aChild_canGetABall() {
        Ball b = new Ball();
        theChild.giveBall(b);
        assertTrue(theChild.hasBall());
        assertEquals(b, theChild.getBall());
    }

    @Test
    public void aChildWithBall_canCatchAnimals() {
        giveThatKidABall();
        Wildlife a = new Wildlife("Catch me if you can", 1);
        assertTrue(theChild.catchAnimal(a));
        Optional<Being> b = theChild.getBall().retrieveBeing();
        assertTrue(b.isPresent());
        assertEquals(a, b.get());
    }

    @Test
    @DisplayName("A 'Child' cannot capture itself")
    @Tag("Constraints")
    public void aChildWithBall_cannotCatchItself() {
        giveThatKidABall();
        assertThrows(Child.CannotCatchSelfException.class, () -> theChild.catchAnimal(theChild));
    }

    @Test
    public void aChildWithAFullBall_canExchangeTheCatchForCandy() {
        giveThatKidABall();
        Wildlife a = new Wildlife("Catch me if you can", 5);
        theChild.catchAnimal(a);
        theChild.exchangeCatchForCandy(theMachine);
        assertEquals(a.getSize(), theChild.getCandies().stream().mapToInt(Candy::getAmountOfSugar).sum());
    }
}