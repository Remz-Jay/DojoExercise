package candy.crush.api;

import candy.crush.Simulation;

public class addWildlife implements ApiAction {
    private Simulation simulation;

    @Override
    public String getDescription() {
        return "adds wildlife to the simulation";
    }

    @Override
    public void doAction(String[] args) {
        int howMany = 10;
        if(args.length > 0) {
            howMany = Integer.parseInt(args[0]);
        }
        this.simulation.generateWildLife(howMany);
    }

    @Override
    public void setSimulation(Simulation s) {
        this.simulation = s;
    }
}
