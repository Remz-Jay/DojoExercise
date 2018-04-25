package candy.crush.domain.items;

import candy.crush.domain.beings.Being;

import java.util.Optional;

public class Ball {
    private Being containedBeing;

    public String peek(){
        return containedBeing.toString();
    }
    public Optional<Being> retrieveBeing() {
        Being beingToBeReturned = containedBeing;
        this.containedBeing = null;
        return Optional.ofNullable(beingToBeReturned);
    }

    public void setContainedBeing(Being containedBeing) {
        this.containedBeing = containedBeing;
    }

    public boolean capture(Being being) {
        if (containedBeing == null && being != null) {
            this.setContainedBeing(being);
            return true;
        } else {
            return false;
        }
    }
    public boolean isEmpty() {
        return containedBeing == null;
    }
}
