package candy.crush;

import candy.crush.api.ApiAction;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.*;

public class CandyCrushCli {

    public enum ANSI {
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

    private final Simulation simulation;

    private CandyCrushCli(Simulation simulation) {
        this.simulation = simulation;
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        CandyCrushCli main = new CandyCrushCli(simulation);

        printHeader();
        main.scanAndHandleInput();
    }


    private void scanAndHandleInput() {
        Scanner scanner = new Scanner(in);
        while (true) {
            try {
                out.print("<Command>:");
                String input = scanner.nextLine();
                String[] splitInput = input.split("\\s+");
                String command = splitInput[0];
                String[] commandArgs = Arrays.copyOfRange(splitInput, 1, splitInput.length);
                if (!handleCommand(command, commandArgs))
                    break;
            } catch (Exception e) {
                print("Unable to perform action, caught Exception [" + e + "]");
            }
        }
    }

    private boolean handleCommand(String command, String[] commandArgs) {
        try {
            ApiAction o = (ApiAction) Class.forName("candy.crush.api." + command).newInstance();
            o.setSimulation(this.simulation);
            o.doAction(commandArgs);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException i) {
            showUndefinedCommandInformation(command, commandArgs);
        }
        return true;
    }
    public static void print(String toPrint) {
        print(toPrint, ANSI.WHITE);
    }

    public static void print(String toPrint, ANSI color) {
        out.println(color + toPrint + ANSI.RESET);
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

    public static void printFooter() {

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
}
