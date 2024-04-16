import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map map = new Map();
        System.out.println("AStar Map");
        System.out.println("-------------------\n");
        System.out.println(map.toString() + "\n");

        char goAgain;

        do {
            System.out.println("Choose Starting Position");
            System.out.println("------------------------");
            System.out.print("Row: ");
            int startRow = scanner.nextInt();
            System.out.print("Column: ");
            int startColumn = scanner.nextInt();

            validateAndSetPosition(map, scanner, startRow, startColumn, "s");

            System.out.println("\nChoose Destination Position");
            System.out.println("---------------------------");
            System.out.print("Row: ");
            int goalRow = scanner.nextInt();
            System.out.print("Column: ");
            int goalColumn = scanner.nextInt();

            validateAndSetPosition(map, scanner, goalRow, goalColumn, "g");

            System.out.println("\n\n" + map.toString());

            map.generatePath(startRow, startColumn, goalRow, goalColumn);
            System.out.println("Path to goal " + map.displayPath());
            System.out.println("\nSolution Path");
            System.out.println("-------------");
            map.updateMap();

            System.out.println("\n" + map.pathToString());

            System.out.print("\nWould you like to try again?\n");
            System.out.print("Enter Y or N: ");
            goAgain = scanner.next().charAt(0);

            map.resetNodes();
            map.resetPath();
            map.setElement(startRow, startColumn, "-");
            map.setElement(goalRow, goalColumn, "-");

            System.out.print("\n");

        } while (goAgain == 'Y' || goAgain == 'y');

        System.out.println("Thanks, try again soon...");
    }

    private static void validateAndSetPosition(Map map, Scanner scanner, int row, int column, String symbol) {
        while (map.getType(row, column).equals(Map.UNPATHABLE)) {
            System.out.println("\nError! Selected position is blocked\n");
            System.out.print("Row: ");
            row = scanner.nextInt();
            System.out.print("Column: ");
            column = scanner.nextInt();
            map.setElement(row, column, symbol);
        }
        map.setElement(row, column, symbol);
    }
}
