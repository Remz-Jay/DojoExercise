package candy.crush.api;

import candy.crush.CandyCrushCli;
import candy.crush.Simulation;

public class quit implements ApiAction {
    Simulation simulation;

    public quit() {
        this.register();
    }
    @Override
    public void register() {

    }

    @Override
    public String getDescription() {
        return "quits the simulation. bye!";
    }

    @Override
    public boolean doAction(String[] args) {
        CandyCrushCli.printFooter();
        System.exit(0);
        return true;
    }

    @Override
    public void setSimulation(Simulation s) {
        this.simulation = s;
    }
}
