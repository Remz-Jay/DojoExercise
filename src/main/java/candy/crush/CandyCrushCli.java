package candy.crush;

import java.util.Arrays;
import java.util.Scanner;

public class CandyCrushCli {
    private final Simulation simulation;

    public CandyCrushCli(Simulation simulation) {
        this.simulation = simulation;
    }

    public static void main(String[] args) {
        Simulation simulation = createSimulation();

        CandyCrushCli main = new CandyCrushCli(simulation);
        main.printHeader();
        main.scanAndHandleInputUntilQuitIsGiven();
        main.printFooter();
    }

    private static void printHeader() {
        System.out.println(
                "   ____                _     _       _                 _   _      \n" +
                        "  / ___|_ __ _   _ ___| |__ ( ) ___ ( )_ __ ___   __ _| |_(_) ___ \n" +
                        " | |   | '__| | | / __| '_ \\|/ / _ \\|/| '_ ` _ \\ / _` | __| |/ __|\n" +
                        " | |___| |  | |_| \\__ \\ | | | | (_) | | | | | | | (_| | |_| | (__ \n" +
                        "  \\____|_|   \\__,_|___/_| |_|  \\___/  |_| |_| |_|\\__,_|\\__|_|\\___|");
        System.out.println("\t- Bringing you the best flavored candy since 2017! -");
    }

    private void scanAndHandleInputUntilQuitIsGiven() {
        Scanner scanner = new Scanner(System.in);
        inputScannerLoop:

        while (true) {
            try {
                System.out.print("<Command>:");
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
                System.out.println("Unable to perform action, caught Exception [" + e + "]");
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
            default:
                showUndefinedCommandInformation(command, commandArgs);
                break;
        }
    }


    private void addWildLife(Integer amountOfWildlifeToAdd) {
        simulation.generateWildLife(amountOfWildlifeToAdd);
    }


    private void printFooter() {

        System.out.println("Thank you for using the Crush'o'matic! Have a nice day!");
        System.out.println(
                "        ___      .-\"\"-.      ___\n" +
                        "        \\  \"-.  /      \\  .-\"  /\n" +
                        "         > -=.\\/        \\/.=- <\n" +
                        "         > -='/\\        /\\'=- <\n" +
                        "        /__.-'  \\      /  '-.__\\\n" +
                        "   jgs           '-..-'");


    }

    private void showUndefinedCommandInformation(String command, String[] commandArgs) {
        System.out.println("Undefined command [" + command + "] with arguments [" + Arrays.toString(commandArgs) + "]");
    }

    private static Simulation createSimulation() {
        Simulation simulation = new Simulation();
        return simulation;
    }


    private static void showHelp() {
        System.out.println("addwildlife [numberOfWildLife]       - adds wildlife to the simulation");
        System.out.println("help                                 - shows this information");
        System.out.println("quit                                 - ends this process");
    }
}
