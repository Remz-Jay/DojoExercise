package candy.crush.domain.beings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WildlifeTest {
    Wildlife beast;

    @Test
    public void aWildlifeBeingHasANameAndSize() {
        String name = "Testname";
        int size = 100;
        beast = new Wildlife(name, size);
        assertEquals(size, beast.getSize());
        assertEquals(name, beast.toString());
    }
}