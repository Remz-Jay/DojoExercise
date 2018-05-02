package candy.crush.api;

import candy.crush.Simulation;

public interface ApiAction {

    String getDescription();
    void doAction(String[] args);

    void setSimulation(Simulation s);
}