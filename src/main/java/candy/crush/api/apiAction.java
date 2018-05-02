package candy.crush.api;

import candy.crush.CandyCrushCli;
import candy.crush.Simulation;

public abstract class apiAction {
    protected Simulation simulation;
    protected CandyCrushCli cli;

    public abstract void register();
    public abstract String getDescription();
    public abstract boolean doAction(String[] args);

    public void setSimulation(Simulation s) {
        this.simulation = s;
    }

    public void setCli(CandyCrushCli c) {
        this.cli = c;
    }
}