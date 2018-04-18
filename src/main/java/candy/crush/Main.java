package candy.crush;

import jp.uphy.javafx.console.ConsoleApplication;

public class Main extends ConsoleApplication {

    @Override
    protected void invokeMain(final String[] args) {
        CandyCrushCli.main(args);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
