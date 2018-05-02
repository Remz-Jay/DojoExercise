package candy.crush.api;

import candy.crush.CandyCrushCli;
import candy.crush.Simulation;

public class quit implements ApiAction {

    @Override
    public String getDescription() {
        return "quits the simulation. bye!";
    }

    @Override
    public void doAction(String[] args) {
        CandyCrushCli.printFooter();
        System.exit(0);
    }

    @Override
    public void setSimulation(Simulation s) {
    }
}
