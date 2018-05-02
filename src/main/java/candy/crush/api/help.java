package candy.crush.api;

import candy.crush.Simulation;

import java.util.ServiceLoader;

import static candy.crush.CandyCrushCli.print;

public class help implements ApiAction {

    @Override
    public String getDescription() {
        return "prints information about available simulation actions";
    }

    @Override
    public void doAction(String[] args) {
        ServiceLoader<ApiAction> load = ServiceLoader.load(ApiAction.class);
        load.forEach(service -> print(String.format("%1$-20s\t\t\t%2$-100s", service.getClass().getSimpleName(), service.getDescription())));
    }

    @Override
    public void setSimulation(Simulation s) {
    }
}
