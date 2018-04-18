package candy.crush;

import candy.crush.domain.beings.Wildlife;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}