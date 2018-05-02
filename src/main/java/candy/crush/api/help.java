package candy.crush.api;

import candy.crush.Simulation;

import java.util.ServiceLoader;

import static candy.crush.CandyCrushCli.print;

public class help implements ApiAction {
    Simulation simulation;

    public help() {
        this.register();
    }
    @Override
    public void register() {

    }

    @Override
    public String getDescription() {
        return "prints information about available simulation actions";
    }

    @Override
    public boolean doAction(String[] args) {
        ServiceLoader<ApiAction> load = ServiceLoader.load(ApiAction.class);
        load.forEach(service -> print(String.format("%1$-20s\t\t\t%2$-100s", service.getClass().getSimpleName(), service.getDescription())));
        return true;
    }

    @Override
    public void setSimulation(Simulation s) {
        this.simulation = s;
    }
}
