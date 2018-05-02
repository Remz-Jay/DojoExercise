package candy.crush;

import candy.crush.domain.beings.Being;
import candy.crush.domain.beings.Wildlife;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {
    Simulation simulation;

    @BeforeEach
    void setup() {
        simulation = new Simulation();
    }
    @Test
    void generateWildLife() {
        int amountOfWildlifeToAdd = 10;
        simulation.generateWildLife(amountOfWildlifeToAdd);
        assertEquals(amountOfWildlifeToAdd, simulation.getBeings().size());
    }

    @Test
    void addBeing() {
        Wildlife wildlife = new Wildlife("first", 42);
        simulation.addBeing(wildlife);
        assertEquals(1, simulation.getBeings().size());
        assertEquals(42, simulation.getBeings().iterator().next().getSize());
    }

    @Test
    @DisplayName("Captured 'Wildlife' will be removed from the 'Simulation' so that they cannot be captured again")
    @Tag("Constraints")
    void capturedAnimals_willBeRemovedFromSimulation() {
       this.addBeing();

    }

    @Test
    void getRandomBeingRemovesWildlife() {
        simulation.generateWildLife(10);
        ArrayList<Being> beings = simulation.getBeings();
        simulation.getRandomBeing();
        assertEquals(9, beings.size());
    }

    @Test
    void getRandomBeingWithoutBeings() {
        assertNull(simulation.getRandomBeing());
    }

    @Test
    void getRandom() {
        simulation.generateWildLife(10);
        assertNotNull(simulation.getRandomBeing());
    }

}