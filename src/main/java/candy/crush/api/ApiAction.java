package candy.crush.api;

import candy.crush.Simulation;

public interface ApiAction {

    public void register();
    public String getDescription();
    public boolean doAction(String[] args);

    public void setSimulation(Simulation s);
}