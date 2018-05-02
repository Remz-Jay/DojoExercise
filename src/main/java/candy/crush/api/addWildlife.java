package candy.crush.api;

import candy.crush.Simulation;

import java.util.Arrays;

public class addWildlife extends apiAction {

    public addWildlife() {
        this.register();
    }
    @Override
    public void register() {

    }

    @Override
    public String getDescription() {
        return "adds wildlife to the simulation";
    }

    @Override
    public boolean doAction(String[] args) {
        int howMany = 10;
        if(args.length > 0) {
            howMany = Integer.parseInt(args[0]);
        }
        this.simulation.generateWildLife(howMany);
        return true;
    }
}
