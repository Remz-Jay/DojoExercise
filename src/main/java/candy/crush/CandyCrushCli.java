package candy.crush;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.*;

public class CandyCrushCli {

    private enum ANSI {
        RESET("\u001B[0m"),
        BLACK("\u001B[30m"),
        RED("\u001B[31m"),
        GREEN("\u001B[32m"),
        YELLOW("\u001B[33m"),
        BLUE("\u001B[34m"),
        PURPLE("\u001B[35m"),
        CYAN("\u001B[36m"),
        WHITE("\u001B[37m");
        private final String colorCode;

        ANSI(String colorCode) {
            this.colorCode = colorCode;
        }

        private String colorCode() {
            return colorCode;
        }

        @Override
        public String toString() {
            return colorCode();
        }
    }

    private static void print(String toPrint) {
        print(toPrint, ANSI.WHITE);
    }

    private static void print(String toPrint, ANSI color) {
        out.println(color + toPrint + ANSI.RESET);
    }

    private final Simulation simulation;

    private CandyCrushCli(Simulation simulation) {
        this.simulation = simulation;
    }

    public static void main(String[] args) {
        Simulation simulation = createSimulation();

        CandyCrushCli main = new CandyCrushCli(simulation);
        printHeader();
        main.scanAndHandleInputUntilQuitIsGiven();
        main.printFooter();
    }

    private static void printHeader() {
        print(
                "   ____                _     _       _                 _   _      \n" +
                        "  / ___|_ __ _   _ ___| |__ ( ) ___ ( )_ __ ___   __ _| |_(_) ___ \n" +
                        " | |   | '__| | | / __| '_ \\|/ / _ \\|/| '_ ` _ \\ / _` | __| |/ __|\n" +
                        " | |___| |  | |_| \\__ \\ | | | | (_) | | | | | | | (_| | |_| | (__ \n" +
                        "  \\____|_|   \\__,_|___/_| |_|  \\___/  |_| |_| |_|\\__,_|\\__|_|\\___|", ANSI.GREEN);
        print("\t- Bringing you the best flavored candy since 2017! -", ANSI.BLUE);
    }

    private void scanAndHandleInputUntilQuitIsGiven() {
        Scanner scanner = new Scanner(in);
        inputScannerLoop:

        while (true) {
            try {
                out.print("<Command>:");
                String input = scanner.nextLine();
                String[] splitInput = input.split("\\s+");
                String command = splitInput[0];
                String[] commandArgs = Arrays.copyOfRange(splitInput, 1, splitInput.length);
                switch (command) {
                    case "quit":
                        break inputScannerLoop;
                    default:
                        handleCommand(command, commandArgs);
                        break;

                }
            } catch (Exception e) {
                print("Unable to perform action, caught Exception [" + e + "]");
            }
        }
    }

    private void handleCommand(String command, String[] commandArgs) {
        switch (command) {
            case "help":
                showHelp();
                break;
            case "awl":
            case "addwildlife":
                Integer amountOfWildlifeToAdd;
                if (commandArgs[0] != null) {
                    amountOfWildlifeToAdd = Integer.parseInt(commandArgs[0]);
                } else {
                    amountOfWildlifeToAdd = 10;
                }
                addWildLife(amountOfWildlifeToAdd);
                break;
            case "ac":
            case "addchild":
                addChild();
                break;
            case "fs":
            case "fullsimulation":
                runFullSimulation();
                break;
            default:
                showUndefinedCommandInformation(command, commandArgs);
                break;
        }
    }


    private void addWildLife(Integer amountOfWildlifeToAdd) {
        simulation.generateWildLife(amountOfWildlifeToAdd);
    }

    private void addChild() {
        simulation.addChild();
    }

    private void runFullSimulation() {
        print("Welcome to the CrushOMatic 9000 DEMO!", ANSI.RED);
        print("First, let's add some animals to the kingdom.", ANSI.YELLOW);
        this.addWildLife(10);
        print("Let's add a Child to catch all those animals..");
        this.addChild();
        print("Give the kid a Ball..");
    }

    private void printFooter() {

        print("Thank you for using the Crush'o'matic! Have a nice day!");
        print("        ___      .-\"\"-.      ___", ANSI.BLUE);
        print("        \\  \"-.  /      \\  .-\"  /", ANSI.GREEN);
        print("         > -=.\\/        \\/.=- <", ANSI.YELLOW);
        print("         > -='/\\        /\\'=- <", ANSI.RED);
        print("        /__.-'  \\      /  '-.__\\", ANSI.CYAN);
        print("   jgs           '-..-'", ANSI.PURPLE);
    }

    private void showUndefinedCommandInformation(String command, String[] commandArgs) {
        print("Undefined command [" + command + "] with arguments [" + Arrays.toString(commandArgs) + "]");
    }

    private static Simulation createSimulation() {
        Simulation simulation = new Simulation();
        return simulation;
    }


    private static void showHelp() {
        print("addwildlife [numberOfWildLife]       - adds wildlife to the simulation");
        print("addchild                             - adds a single child to the simulation");
        print("fulsimulation                        - run a full simulation");
        print("help                                 - shows this information");
        print("quit                                 - ends this process");
    }
}
