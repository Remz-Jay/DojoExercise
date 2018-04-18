package candy.crush.domain.items;

import candy.crush.domain.beings.Being;

import java.util.Optional;

public class Ball {
    private Being containedBeing;

    public Optional<Being> getContainedBeing() {
        return Optional.ofNullable(containedBeing);
    }

    public void setContainedBeing(Being containedBeing) {
        this.containedBeing = containedBeing;
    }

    public boolean capture(Being being) {
        if (containedBeing == null) {
            this.setContainedBeing(being);
            return true;
        } else {
            return false;
        }
    }

    // TODO: What if we catch nothing?
    // TODO: Retrieving items.. we can only do that once. Is getContainedBeing valid in that case?
    // TODO: Does the retrieve function return the item and/or set the ball to empty?
}
